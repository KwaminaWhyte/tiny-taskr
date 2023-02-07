package com.freskotek.taskmgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class TaskmgntApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmgntApplication.class, args);
	}

	@GetMapping("/workspace/{space_id}")
	public String workspace(@PathVariable("space_id") String space_id) {
		System.out.println("workspace route...");
		return "forward:/";
	}

	@GetMapping("/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}




	// @GetMapping("/")
	// public String apiRoot() {
	// return "index.html";
	// }
}
