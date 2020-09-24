package com.biswajit.bootreact.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biswajit.bootreact.domain.Project;
import com.biswajit.bootreact.services.MapValidationErrorService;
import com.biswajit.bootreact.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapValidationErrorService mapValidationService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		}
		projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProjectByIdentifier(projectId);
		
		return new ResponseEntity<String> ("Project with ID: '"+projectId+"' was deleted",HttpStatus.OK);
		
	}
}
