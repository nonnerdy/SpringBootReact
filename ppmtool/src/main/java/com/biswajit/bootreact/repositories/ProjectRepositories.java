package com.biswajit.bootreact.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biswajit.bootreact.domain.Project;

@Repository
public interface ProjectRepositories extends CrudRepository<Project, Long>{

	Project findByProjectIdentifier(String projectIdentifier);

	@Override
	Iterable<Project> findAll();
	
	
	
	

}
