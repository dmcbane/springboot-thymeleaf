# springboot-thymeleaf

~~Broken but may have useful info~~

The problem was that Thymeleaf does not render templates for @RestController annotated controllers, but does render for @Controller annotated controllers.  The correction was a simple as changing the annotation (and the import).
