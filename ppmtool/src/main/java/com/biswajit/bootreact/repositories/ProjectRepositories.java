package com.biswajit.bootreact.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biswajit.bootreact.domain.Project;

@Repository
public interface ProjectRepositories extends CrudRepository<Project, Long>{

	@Override
	Iterable<Project> findAllById(Iterable<Long> iterable);

}
