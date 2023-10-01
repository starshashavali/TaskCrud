package com.org.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.org.domain.Task;
import com.org.dto.TaskDTO;
import com.org.repo.TaskRepo;
import com.org.service.impl.ITaskServiceImpl;

@SpringBootTest
public class ITaskServiceImplTest {

	@InjectMocks
	private ITaskServiceImpl taskService;

	@Mock
	private TaskRepo taskRepo;

	@Test
	public void testSave() {
		TaskDTO dto = new TaskDTO(1L, "Java", "complete", false);
		Task task = new Task();
		BeanUtils.copyProperties(dto, task);

		when(taskRepo.save(task)).thenReturn(task);
		assertEquals("success", taskService.saveTask(dto));
	}

	@Test
	public void testGetTaskById() {
		Task task = new Task();
		task.setId(1L);
		task.setTitle("Bug fixing");
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task));

		TaskDTO result = taskService.getTaskById(1L);

		assertEquals(1L, result.getId().longValue());
		assertEquals("Bug fixing", result.getTitle());
	}

	@Test
	public void testDeleteTaskById() {
		when(taskRepo.findById(1L)).thenReturn(Optional.of(new Task()));
		assertEquals("deleted successfully...", taskService.deleteTaskById(1L));
		verify(taskRepo).deleteById(1L);
	}

	@Test
	public void testIsCompleted() {
		Long id = 1L;
		Task task = new Task();
		when(taskRepo.findById(id)).thenReturn(Optional.of(task));

	}

	@Test
	public void testIsCompletedSuccess() {
		Long taskId = 1L;
		Task task = new Task();
		task.setId(taskId);
		task.setDeleted(false);

		when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));

		boolean result = taskService.isCompleted(taskId);

		assertTrue(result);
		assertTrue(task.isCompleted());
		assertTrue(task.isDeleted());
		verify(taskRepo).save(task);
	}

	@Test // (expected = IdNotFoundException.class)
	public void testIsCompletedNotFound() {
		Long taskId = 1L;

		when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

	}

	@Test
	public void testIsCompletedAlreadyDeleted() {
		Long taskId = 1L;
		Task task = new Task();
		task.setId(taskId);
		task.setDeleted(true);
		when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));
		//assertEquals(("Task with ID " + taskId + " is already deleted."), taskService.isCompleted(taskId));
	}
}
