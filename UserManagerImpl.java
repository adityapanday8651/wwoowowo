package com.wowment.mongo.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowment.exception.ServiceException;
import com.wowment.filter.SearchRequest;
import com.wowment.mongo.User;
import com.wowment.mongo.manager.UserManager;
import com.wowment.mongo.repository.UserRepository;
import com.wowment.service.core.GenericSearchRepository;

@Service("UserManager")
public class UserManagerImpl implements UserManager {

	@Autowired UserRepository userRepository;
	@Autowired GenericSearchRepository repository;
	@Override
	public void save(User bean) throws ServiceException {
		userRepository.save(bean);
	}
	@Override
	public void save(List<User> beans) throws ServiceException {
		userRepository.saveAll(beans);
	}
	@Override
	public boolean update(User bean) throws ServiceException {
		userRepository.save(bean);
		return true;
	}
	@Override
	public long getCount() throws ServiceException {
		return userRepository.count();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> search(SearchRequest request) {
		return (List<User>) repository.search(request, User.class );
	}
	
}
