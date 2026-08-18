package br.com.loidpadre.segundo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.loidpadre.segundo.dto.TaskRequestDto;
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

    public TaskResponseDto saveTask(TaskRequestDto request) {
        String title = request.title();
        String description = request.description();

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

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarefa nao encontrada");
        }
        taskRepository.deleteById(id);

    }

    public TaskResponseDto upDateTask(Long id, TaskRequestDto request) {
        String title = request.title();
        String description = request.description();
        Task taskDoBanco = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        taskDoBanco.setTitle(title);
        taskDoBanco.setDescription(description);
        taskRepository.save(taskDoBanco);
        return new TaskResponseDto(taskDoBanco.getId(), taskDoBanco.getTitle(), taskDoBanco.getDescription(),
                taskDoBanco.getCompleted());
    }
}
