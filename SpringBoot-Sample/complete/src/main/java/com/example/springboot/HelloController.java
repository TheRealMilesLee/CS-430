/**
 * It's a Spring Boot controller that responds to HTTP requests at the root path ("/")
 */
package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * It's a Spring Boot controller that responds to HTTP requests at the root path ("/")
 */
@RestController
public class HelloController
{
	@GetMapping("/")
	public String index()
	{
		return "Greetings from Spring Boot!";
	}
}
