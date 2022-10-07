/*
 * @Author: TheRealMilesLee grandtheftauto710@outlook.com
 * @Date: 2022-06-30 11:53:40
 * @LastEditors: TheRealMilesLee grandtheftauto710@outlook.com
 * @LastEditTime: 2022-10-06 21:48:11
 * @FilePath: /SpringBoot-Sample/complete/src/main/java/com/example/springboot/HelloController.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
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
