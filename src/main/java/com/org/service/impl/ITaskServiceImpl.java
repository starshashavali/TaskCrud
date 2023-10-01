package com.org.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.domain.Task;
import com.org.dto.TaskDTO;
import com.org.exception.IdNotFoundException;
import com.org.repo.TaskRepo;
import com.org.service.ITaskService;

@Service
public class ITaskServiceImpl implements ITaskService {

	@Autowired
	TaskRepo taskRepo;

	@Override
	public String saveTask(TaskDTO dto) {
		Task task = new Task();
		BeanUtils.copyProperties(dto, task);
		taskRepo.save(task);
		return "success";
	}

	@Override
	public TaskDTO getTaskById(Long id) {
		Optional<Task> findById = taskRepo.findById(id);
		if (findById.isPresent()) {
			Task task = findById.get();
			TaskDTO dto = new TaskDTO();
			BeanUtils.copyProperties(task, dto);
			return dto;
		}
		throw new IdNotFoundException("Id not found...");
	}

	@Override
	public String deleteTaskById(Long id) {
		Optional<Task> findById = taskRepo.findById(id);
		if(findById.isPresent()) {
		taskRepo.deleteById(id);
		return "deleted successfully...";
		}
		throw new IdNotFoundException("Id not found...");
	}

	@Override
	public boolean isCompleted(Long id) {
	    Optional<Task> findById = taskRepo.findById(id);

	    if (findById.isPresent()) {
	        Task task = findById.get();
	        if (!task.isDeleted()) {
	            task.setCompleted(true);
	            task.setDeleted(true);
	            taskRepo.save(task);
	            return true;
	        } else {
	            throw new IdNotFoundException("Task with ID " + id + " is already deleted.");
	        }
	    } else {
	        throw new IdNotFoundException("Task with ID " + id + " not found.");
	    }
	}

}