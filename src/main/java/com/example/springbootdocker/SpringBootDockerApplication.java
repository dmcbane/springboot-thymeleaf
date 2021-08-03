package com.example.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringBootDockerApplication {

	/* possible improvement - https://zetcode.com/springboot/applicationreadyevent/ */

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerApplication.class, args);
		System.out.println("SpringApplication.run complete – going to openHomePage");
		openHomePage();
	}

	private static void openHomePage() {
		String url = "http://localhost:8080";

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();

			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
			Runtime runtime = Runtime.getRuntime();
			try {
				switch (ostype) {
					case Windows:
						runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
						break;
					case MacOS:
						runtime.exec("open " + url);
						break;
					case Linux:
						runtime.exec("xdg-open " + url);
						break;
					case Other:
						runtime.exec(url);
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@EventListener(ContextRefreshedEvent.class)
	public void ContextRefreshedEventExecute() {
		System.out.println("Context Event Listener is getting executed");
	}
	@EventListener(ApplicationReadyEvent.class)
	public void EventListenerExecute() {
		System.out.println("Application Ready Event was successfully Started.");
	}
	@EventListener(ApplicationFailedEvent.class)
	public void EventListenerExecuteFailed() {
		System.out.println("Application Event Listener has Failed.");
	}
}
