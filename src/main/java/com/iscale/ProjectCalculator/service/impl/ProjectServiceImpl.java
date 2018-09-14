package com.iscale.ProjectCalculator.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iscale.ProjectCalculator.object.Project;
import com.iscale.ProjectCalculator.object.Task;
import com.iscale.ProjectCalculator.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Override
	public void createProject() {
		// TODO Auto-generated method stub

	}

	@Override
	public Project calculateProject(Project project) {
		
		//Check if start date exits, if it does, use it. otherwise start date = next day
		Date startDate = project.getStartDate();		
		Date tempEndDate = project.getStartDate();
		Calendar calendar = Calendar.getInstance();
		ArrayList<String> waitingList = new ArrayList<String>();	
		Integer waitingListSize;
		ArrayList<Date> dependencyDate = new ArrayList<Date>();
		Boolean hasNull = new Boolean(false);
		
		if (startDate == null) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			startDate = calendar.getTime();
			project.setStartDate(startDate);
			tempEndDate = startDate;
		}
		
		
		
		//Calculate all tasks w/o dependencies, Start date will be the base start date. Create new temporary list for calculated end dates
		Map<String, Task> taskList = project.getTaskList();
		
		for (Map.Entry<String, Task> entry : taskList.entrySet()) {
			Task task = entry.getValue();			
			ArrayList<String> dependencies = task.getDependencies();
			
			if(dependencies == null || dependencies.isEmpty()){
				task.setStartDate(startDate);
				
				//get Duration, calculate End Date
				calendar.setTime(startDate);
				calendar.add(Calendar.DATE, task.getDuration());
				
				task.setEndDate(calendar.getTime());
				tempEndDate = this.compateDates(tempEndDate, task.getEndDate());
			
			}else {
				//Calculate all tasks w/ dependencies, check dependencies end dates	
				//check if end dates have been calculated
				dependencyDate = new ArrayList<Date>();
				hasNull = false;
				for (String dep : dependencies) {
					
					//get dependency End Date
					Task parentTask = taskList.get(dep);
					
					if(parentTask.getEndDate() == null) {						
						hasNull = true;
						break;
					}else{
						dependencyDate.add(parentTask.getEndDate());
					}									
				}
				
				if(!hasNull) {
					Collections.sort(dependencyDate, Collections.reverseOrder());					
					Date endDate = dependencyDate.get(0);
					calendar.setTime(endDate);
					calendar.add(Calendar.DATE, 1);					
					task.setStartDate(calendar.getTime());
					calendar.add(Calendar.DATE, task.getDuration());
					task.setEndDate(calendar.getTime());
					tempEndDate = this.compateDates(tempEndDate, task.getEndDate());				
				}else {
					dependencyDate = new ArrayList<Date>();
					waitingList.add(entry.getKey());					
				}
			}
		}
		
		waitingListSize = waitingList.size();
		//loop through waiting list until is empty
		while(waitingListSize > 0){			
			for (String key : waitingList) {
				Task task = taskList.get(key);				
				ArrayList<String> dependencies = task.getDependencies();
				
				dependencyDate = new ArrayList<Date>();
				for (String dep : dependencies) {
					
					//get dependency End Date
					Task parentTask = taskList.get(dep);
					
					if(parentTask.getEndDate() == null) {
						hasNull = true;
						break;
					}else{
						dependencyDate.add(parentTask.getEndDate());
					}									
				}
				
				if(!hasNull) {
					Collections.sort(dependencyDate, Collections.reverseOrder());
					
					Date endDate = dependencyDate.get(0);
					calendar.setTime(endDate);
					calendar.add(Calendar.DATE, 1);					
					task.setStartDate(calendar.getTime());
					calendar.add(Calendar.DATE, task.getDuration());
					task.setEndDate(calendar.getTime());
					tempEndDate = this.compateDates(tempEndDate, task.getEndDate());
					waitingListSize--;								
					
				}
				
				hasNull = false;
			}
		}	
		
		project.setEndDate(tempEndDate);
		return project;
	
	}

	@Override
	public void getProject() {
		// TODO Auto-generated method stub
			
	}
	
	private Date compateDates(Date date1, Date date2) {
		if (date1.after(date2)) {
			return date1;
		}else {
			return date2;
		}
	}

}
