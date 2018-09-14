package com.iscale.ProjectCalculator.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscale.ProjectCalculator.model.ProjectForm;

@Component
@Scope("session")
public class Project {

	private String id;
	private String description;
	private Integer duration;
	private Date startDate;
	private Date endDate;
	
	
	private Map<String, Task> taskList;	
	
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Map<String, Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(Map<String, Task> taskList) {
		this.taskList = taskList;
	}
	
	public Project(ProjectForm projForm) {
		this.setDescription(projForm.getProjectDescription());
		this.setStartDate(projForm.getProjectStartDate());
		this.setTaskList(this.convertListToMap(projForm.getTaskList()));
	}
	
	public Project() {
		
	}
	
	private Map<String, Task> convertListToMap(List tasklist) {
		
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Task> mainTask = new HashMap<String, Task>();
		
		for (Object o : tasklist) {
			Map omap = oMapper.convertValue(o, Map.class);			
			
			System.out.println(omap);
			System.out.println(o);
			
			Task task = new Task();
			
			task.setId(String.valueOf(omap.get("id")));
			task.setDescription(String.valueOf(omap.get("description")));
			task.setDuration(Integer.valueOf(omap.get("duration").toString()));			
			task.setDependencies(this.convertObjectsToString((ArrayList) omap.get("depends")));
			mainTask.put(task.getId(), task);
			
		}
	
		return mainTask;
	}
	

	private ArrayList<String> convertObjectsToString(ArrayList listobjs) {
		ArrayList<String> list = new ArrayList<String>();
		for (Object item : listobjs) {
			list.add((String.valueOf(item)));
		}
		
		return list;
	}
}
