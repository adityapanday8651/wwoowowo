package com.wowment.mongo.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowment.exception.ServiceException;
import com.wowment.filter.SearchRequest;
import com.wowment.mongo.Organization;
import com.wowment.mongo.User;
import com.wowment.mongo.manager.OrganizationManager;
import com.wowment.mongo.manager.UserManager;
import com.wowment.mongo.repository.OrganizationRepository;
import com.wowment.mongo.repository.UserRepository;
import com.wowment.service.core.GenericSearchRepository;

@Service("OrganizationManager")
public class OrganizationManagerImpl implements OrganizationManager {

	@Autowired OrganizationRepository repo;
	@Autowired GenericSearchRepository repository;
	@Override
	public void save(Organization bean) throws ServiceException {
		repo.save(bean);
	}
	@Override
	public void save(List<Organization> beans) throws ServiceException {
		repo.saveAll(beans);
	}
	@Override
	public boolean update(Organization bean) throws ServiceException {
		repo.save(bean);
		return true;
	}
	@Override
	public long getCount() throws ServiceException {
		return repo.count();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> search(SearchRequest request) {
		return (List<Organization>) repository.search(request, Organization.class );
	}
}
