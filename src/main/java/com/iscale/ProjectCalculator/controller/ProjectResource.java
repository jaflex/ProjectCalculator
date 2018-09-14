package com.iscale.ProjectCalculator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iscale.ProjectCalculator.model.ProjectForm;
import com.iscale.ProjectCalculator.model.TaskForm;
import com.iscale.ProjectCalculator.object.Project;
import com.iscale.ProjectCalculator.object.Task;
import com.iscale.ProjectCalculator.service.ProjectService;
import com.iscale.ProjectCalculator.utils.ConverterTask;

@RestController
public class ProjectResource {

	@Autowired
	private ProjectService projectService;

	private Project project;

	@RequestMapping(value = "/calculate", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<TaskForm> createProject(@RequestBody ProjectForm projectForm) {
				
		project = new Project(projectForm);	
				
		project = projectService.calculateProject(project);	
		
		List<Task> taskList = new ArrayList<Task>(project.getTaskList().values());
		Collections.sort(taskList, new Comparator<Task>() {
			  public int compare(Task o1, Task o2) {
			      return o1.getStartDate().compareTo(o2.getStartDate());
			  }
		});
		
		ConverterTask taskConvert = new ConverterTask();
		List<TaskForm> taskListForm = taskConvert.convertToTaskForm(taskList);
		
		return taskListForm;
	}
	
	
	
	
	
	

	

}
