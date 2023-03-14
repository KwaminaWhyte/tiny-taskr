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

	/*
	 * Defined this here to handle this route alone.
	 * Any other route with a parameter will be defined like this
	 */
	@GetMapping("/workspace/{space_id}")
	public String workspace(@PathVariable("space_id") String space_id) {
		return "forward:/";
	}

	@GetMapping("/notes/{note_id}")
	public String notes(@PathVariable("note_id") String note_id) {
		return "forward:/";
	}

	@GetMapping("/workspace/{space_id}/notes")
	public String workspaceNotes(@PathVariable("space_id") String space_id) {
		return "forward:/";
	}

	@GetMapping("/workspace/{space_id}/notes/{note_id}")
	public String workspaceNoteDetails(@PathVariable("space_id") String space_id,
			@PathVariable("note_id") String note_id) {
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
