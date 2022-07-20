package com.wowment.mongo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;
import com.wowment.filter.Lookup;
import com.wowment.filter.SearchRequest;
import com.wowment.mongo.repository.utils.QueryUtils;
import com.wowment.service.core.GenericSearchRepository;


@Repository
public class GenericSearchRepositoryImpl implements GenericSearchRepository {

	@Autowired  MongoTemplate template;

	@Override
	public List<?> search(SearchRequest request,String key, String value, Class<?> clazz) {

		Query query = new Query();
		Criteria criteria = Criteria.where(key).is(value);
		if(request != null) {
			
			if(request.getSearch() != null && request.getSearch().size()>0) {
				
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if(request.getPageSize()>0) {
				
				PageRequest page =PageRequest.of(request.getCurrentPage(), request.getPageSize(), Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);	
			}
		}

		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	@Override
	public long searchCount(SearchRequest request,String key, String value, Class<?> clazz) {

		Query query = new Query();
		Criteria criteria = Criteria.where(key).is(value);
		if(request != null) {
			if(request.getSearch() != null && request.getSearch().size()>0) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if(request.getPageSize()>0) {
				PageRequest page =PageRequest.of(request.getCurrentPage(), request.getPageSize(), Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);	
			}	
		}
		query.addCriteria(criteria);

		return template.count(query, clazz);

	}

	@Override
	public List<?> search(SearchRequest request, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if(request != null) {
			if(request.getSearch() != null && request.getSearch().size()>0) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if(request.getPageSize()>0) {
				PageRequest page =PageRequest.of(request.getCurrentPage(), request.getPageSize(), Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);	
			}
		}
		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	@Override
	public long searchCount(SearchRequest request, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if(request != null) {
			if(request.getSearch() != null && request.getSearch().size()>0) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if(request.getPageSize()>0) {
				PageRequest page =PageRequest.of(request.getCurrentPage(), request.getPageSize(), Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);	
			}	
		}
		query.addCriteria(criteria);

		return template.count(query, clazz);
	}
	
	@Override
	public List<?> searchIn(SearchRequest request,String key, List<String> value, Class<?> clazz) {

		Query query = new Query();
		Criteria criteria = Criteria.where(key).in(value);
		if(request != null) {
			
			if(request.getSearch() != null && request.getSearch().size()>0) {
				
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if(request.getPageSize()>0) {
				
			PageRequest page =PageRequest.of(request.getCurrentPage(), request.getPageSize(), Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);	
			}
		}

		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	@Override
	public long searchCountIn(SearchRequest request,String key, List<String> value, Class<?> clazz) {

		Query query = new Query();
		Criteria criteria = Criteria.where(key).in(value);
		if(request != null) {
			if(request.getSearch() != null && request.getSearch().size()>0) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if(request.getPageSize()>0) {
				PageRequest page =PageRequest.of(request.getCurrentPage(), request.getPageSize(), Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);	
			}	
		}
		query.addCriteria(criteria);

		return template.count(query, clazz);

	}
	
	@Override
	public List<?> in(List<String> ids,String key, Class<?> clazz) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).in(ids));
		return template.find(query, clazz);
	}
	
	@Override
	public List<?> lookup(Lookup lookup) {
		LookupOperation lookupOperation = LookupOperation.newLookup()
				.from(lookup.getFrom())
				.localField(lookup.getLocalField())
				.foreignField(lookup.getForeignField())
				.as(lookup.getAs());

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where(lookup.getSearchKey()).in(lookup.getSearchValues())) , lookupOperation);
		return  template.aggregate(aggregation, lookup.getInputTypeClazz(), lookup.getOutputTypeClazz()).getMappedResults();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public UpdateResult update(String searchKey,List<String> searchValue,String replaceKey, String replaceValue, Class<?> clazz) {
		UpdateResult result = template.updateMulti(new Query(Criteria.where(searchKey).in(searchValue)), new Update().set(replaceKey, replaceValue), clazz);
		return result.unacknowledged();
	}

	/*@Override
	public Integer update(String string, List<String> orderNumbers, String string2, String stage, String string3,
			String stage2, Class<OrderItemDetails> class1) {
		WriteResult result = template.updateMulti(new Query(Criteria.where(string).in(orderNumbers)), new Update().set(string2, stage),class1);
		return result.getN();
	}*/
}
