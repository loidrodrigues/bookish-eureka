package br.com.loidpadre.segundo.controller;

import br.com.loidpadre.segundo.model.Task;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.loidpadre.segundo.dto.TaskRequestDto;
import br.com.loidpadre.segundo.dto.TaskResponseDto;
import br.com.loidpadre.segundo.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDto>> buscarTask() {
        List<TaskResponseDto> tasks = taskService.getTask();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto request) {
        TaskResponseDto resposta = taskService.saveTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getOnTask(@PathVariable Long id) {
        try {
            TaskResponseDto task = taskService.getOnTask(id);
            return ResponseEntity.ok(task);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> upDateTask(@PathVariable Long id, @RequestBody TaskRequestDto request) {
        try {
            TaskResponseDto response = taskService.upDateTask(id, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
