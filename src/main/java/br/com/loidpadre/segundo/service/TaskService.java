package br.com.loidpadre.segundo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.loidpadre.segundo.dto.TaskResponseDto;
import br.com.loidpadre.segundo.model.Task;
import br.com.loidpadre.segundo.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDto> getTask() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map(
                user -> new TaskResponseDto(user.getId(), user.getTitle(), user.getDescription(), user.getCompleted()))
                .toList();
    }

    public TaskResponseDto saveTask(String title, String description) {
        Task task = new Task(title, description, false);
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(),
                savedTask.getCompleted());
    }

    public TaskResponseDto getOnTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa nao encontrada"));
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());
    }
}
