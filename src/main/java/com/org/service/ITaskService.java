package com.org.service;

import com.org.dto.TaskDTO;

public interface ITaskService {
	
	public String saveTask(TaskDTO dto);
	
	public TaskDTO getTaskById(Long id);
	
	public String deleteTaskById(Long id);
	
	public boolean isCompleted(Long id);

}
