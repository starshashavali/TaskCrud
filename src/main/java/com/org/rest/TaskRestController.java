package com.org.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.TaskDTO;
import com.org.service.ITaskService;

@RestController
@Validated
public class TaskRestController {

	@Autowired
	ITaskService service;

	@PostMapping("/save")
	public ResponseEntity<?> saveTask(@Valid @RequestBody TaskDTO dto) {
		String saveTask = service.saveTask(dto);
		return new ResponseEntity<>(saveTask, HttpStatus.CREATED);

	}

	@GetMapping("/getTask/{id}")
	public ResponseEntity<?> getTaskById(@Valid @PathVariable Long id) {
		TaskDTO taskById = service.getTaskById(id);
		return new ResponseEntity<>(taskById, HttpStatus.OK);
	}

	@DeleteMapping("/deleteTask/{id}")
	public ResponseEntity<?> deleteTaskById(@Valid @PathVariable Long id) {
		String deleteTaskById = service.deleteTaskById(id);
		return new ResponseEntity<>(deleteTaskById, HttpStatus.OK);

	}

	@GetMapping("/deleteTask/{id}")
	public ResponseEntity<?> softdeleteTaskById(@Valid @PathVariable Long id) {
		boolean completed = service.isCompleted(id);
		if (completed) {
			return new ResponseEntity<>("soft deleted...", HttpStatus.OK);
		}
		return new ResponseEntity<>("something went wrong...", HttpStatus.BAD_REQUEST);
	}
}
