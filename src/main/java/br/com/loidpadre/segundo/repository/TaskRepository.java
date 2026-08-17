package br.com.loidpadre.segundo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.loidpadre.segundo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
