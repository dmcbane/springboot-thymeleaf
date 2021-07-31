# springboot-thymeleaf

~~Broken but may have useful info~~

The problem was that Thymeleaf was not rendering templates for @RestController annotated controllers, but does render for @Controller annotated controllers.  The correction was a simple as changing the annotaion (and the import).
