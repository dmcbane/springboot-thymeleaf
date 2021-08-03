package com.example.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringBootDockerApplication {

	/* possible improvement - https://zetcode.com/springboot/applicationreadyevent/ */

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerApplication.class, args);
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
}
