package com.iscale.ProjectCalculator.service;

import com.iscale.ProjectCalculator.object.Project;

public interface ProjectService {

	public void createProject();
	
	public Project calculateProject(Project project);
	
	public void getProject();
	
}
