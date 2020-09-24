package com.biswajit.bootreact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biswajit.bootreact.domain.Project;
import com.biswajit.bootreact.exceptions.ProjectIdException;
import com.biswajit.bootreact.repositories.ProjectRepositories;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepositories projectRepositories;
	
	/**
	 * Create a new project
	 * @param project
	 * @return
	 */
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepositories.save(project);
		}
		catch(Exception e) {
			throw new ProjectIdException("Project ID '"+project.getProjectIdentifier()+"'already exists");
		}
		
		
	}
	/**
	 * Return project based on Project ID
	 * @param projectId
	 * @return
	 */
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepositories.findByProjectIdentifier(projectId.toUpperCase());
	
		if(project == null) {
			throw new ProjectIdException("Project ID '"+projectId+"'doesn't exists");
 
		}
		
		return project;
	}
	
	/**
	 * list all the projects
	 * 
	 * we are using iterable to get all the data in the
	 * form of JSON
	 * @return
	 */
	public Iterable<Project> findAllProjects(){
		return projectRepositories.findAll();
	}
	
	/**
	 * Delete project by projectIdentifier
	 * 
	 * @param projectId
	 */
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepositories.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Cannot delete project with Project ID '"+projectId+"'. This project doesn't exists.");
 
		}
		
		projectRepositories.delete(project);
		
	}
}
