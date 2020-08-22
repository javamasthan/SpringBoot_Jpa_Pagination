package com.dbs.dataviewer.dataviewer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dbs.dataviewer.dataviewer.entity.CustomerEntity;
import com.dbs.dataviewer.dataviewer.excetion.CustomErrorMessage;
import com.dbs.dataviewer.dataviewer.excetion.DataBaseException;
import com.dbs.dataviewer.dataviewer.repository.DataViewerRepository;

@Service("dataViewerService")
public class DataViewerServiceImpl implements DataViewerService {

	private static final Logger log = LoggerFactory.getLogger(DataViewerServiceImpl.class);
	@Autowired
	private DataViewerRepository dataViewerRepository;

	@Override
	public Page<CustomerEntity> getAllCustomersInfo(int page, int pageSize, String sortBy, String filterBy) {
		log.info("[************getAllCustomersInfo service  metnod begins...****************]");
		Page<CustomerEntity> customerList = null;
		Sort sort = null;
		if (sortBy != null && !sortBy.isEmpty()) {
			sort = Sort.by(sortBy);
		} else {
			sort = Sort.unsorted();
		}
		Pageable pages = PageRequest.of(page, pageSize, sort);
		try {
			
		if (filterBy != null && !filterBy.isEmpty()) {
			if(sortBy != null && !sortBy.isEmpty()) {
				customerList = dataViewerRepository.findByFirstNameContaining(filterBy, pages);	
			}else {
				pages = PageRequest.of(page, pageSize);
				customerList = dataViewerRepository.findByFirstNameContaining(filterBy, pages);
			}
			

		}
		customerList = dataViewerRepository.findAll(pages);
		log.info("[************getAllCustomersInfo service  metnod end...****************]");
		return customerList;
		}catch(Exception e) {
			log.error("[************getAllCustomersInfo service  metnod Exception...****************]");
			CustomErrorMessage errorMsg = new CustomErrorMessage(HttpStatus.NOT_FOUND,
					"Data base issue !!!");
			throw new DataBaseException(errorMsg);
		}
		
	}

}
