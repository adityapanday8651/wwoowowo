package com.wowment.mongo.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowment.exception.ServiceException;
import com.wowment.filter.SearchRequest;
import com.wowment.mongo.Program;
import com.wowment.mongo.manager.ProgramManager;
import com.wowment.mongo.repository.ProgramRepository;
import com.wowment.service.core.GenericSearchRepository;

@Service("ProgramManager")
public class ProgramManagerImpl implements ProgramManager {

	@Autowired ProgramRepository programRepository;
	@Autowired GenericSearchRepository repository;
	
	@Override
	public void save(Program bean) throws ServiceException {
		programRepository.save(bean);
	}
	
	@Override
	public void save(List<Program> beans) throws ServiceException {
		programRepository.saveAll(beans);
	}
	
	@Override
	public boolean update(Program bean) throws ServiceException {
		programRepository.save(bean);
		return true;
	}
	
	@Override
	public long getCount() throws ServiceException {
		return programRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Program> search(SearchRequest request) {
		return (List<Program>) repository.search(request, Program.class );
	}
}