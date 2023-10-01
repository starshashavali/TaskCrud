package com.org.domain;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;
	private boolean completed;
    private boolean isDeleted = false;
}
