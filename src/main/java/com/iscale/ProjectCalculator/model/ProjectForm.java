package com.iscale.ProjectCalculator.model;

import java.util.Date;
import java.util.List;

import com.iscale.ProjectCalculator.object.Project;

public class ProjectForm {
	
	private String ProjectDescription;	
	private Date ProjectStartDate;
	private List TaskList;

		
	public List getTaskList() {
		return TaskList;
	}
	public void setTaskList(List taskList) {
		this.TaskList = taskList;
	}
	public String getProjectDescription() {
		return ProjectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.ProjectDescription = projectDescription;
	}
	public Date getProjectStartDate() {
		return ProjectStartDate;
	}
	public void setProjectStartDate(Date ProjectStartDate) {
		this.ProjectStartDate = ProjectStartDate;
	}
	
	
	
	
}
