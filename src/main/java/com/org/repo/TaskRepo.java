package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.Task;

public interface TaskRepo  extends JpaRepository<Task, Long>{

}
