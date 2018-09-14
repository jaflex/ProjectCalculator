package com.iscale.ProjectCalculator.model;

import java.util.List;

import com.iscale.ProjectCalculator.object.Task;
import com.iscale.ProjectCalculator.utils.DateUtil;

public class TaskForm {
	
	private String id;
	private String description;
	private String duration;
	private String startDate;
	private String endDate;
	private String depends;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
			
	public String getDepends() {
		return depends;
	}
	public void setDepends(String depends) {
		this.depends = depends;
	}
	public TaskForm() {	}
	
	public TaskForm(Task task) {
		
		DateUtil util = new DateUtil();		
		this.setId(task.getId());
		this.setDescription(task.getDescription());
		this.setDuration(String.valueOf(task.getDuration()));		
		this.setStartDate(util.formatDate(task.getStartDate()));
		this.setEndDate(util.formatDate(task.getEndDate()));
		
	}
	
	
	

}
