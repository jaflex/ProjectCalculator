package com.iscale.ProjectCalculator.utils;

import java.util.ArrayList;
import java.util.List;

import com.iscale.ProjectCalculator.model.TaskForm;
import com.iscale.ProjectCalculator.object.Task;

public class ConverterTask {

	
	public List<TaskForm> convertToTaskForm(List<Task> taskList){
		
		List<TaskForm> taskFormList = new ArrayList<TaskForm>();
		
		for (Task task : taskList) {
			TaskForm taskForm = new TaskForm(task);
			String deps = "";
			for (String dep : task.getDependencies()) {				
				for (Task task2 : taskList) {					
					if(task2.getId().equalsIgnoreCase(dep)){						
						deps = deps + task2.getDescription();
						deps = deps + " || ";				
					}
				}
			}		
			
			taskForm.setDepends(deps);
			taskFormList.add(taskForm);
		}
		
		return taskFormList;
		
	}
}
