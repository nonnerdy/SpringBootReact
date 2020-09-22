package com.biswajit.bootreact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biswajit.bootreact.domain.Project;
import com.biswajit.bootreact.repositories.ProjectRepositories;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepositories projectRepositories;
	
	public Project saveOrUpdateProject(Project project) {
		return projectRepositories.save(project);
	}
}
