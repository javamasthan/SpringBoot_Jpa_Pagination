package com.dbs.dataviewer.dataviewer.service;

import org.springframework.data.domain.Page;

import com.dbs.dataviewer.dataviewer.entity.CustomerEntity;

public interface DataViewerService {
	
	Page<CustomerEntity> getAllCustomersInfo(int page,int pageSize,String sortyBy,String filterBy);

}
