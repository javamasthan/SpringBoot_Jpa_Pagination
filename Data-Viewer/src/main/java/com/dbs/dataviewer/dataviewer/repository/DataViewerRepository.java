package com.dbs.dataviewer.dataviewer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbs.dataviewer.dataviewer.entity.CustomerEntity;

@Repository("dataViewerRepository")
public interface DataViewerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {
	
	  Page<CustomerEntity> findByFirstNameContaining(String firstName, Pageable pageable);
	  Page<CustomerEntity> findByLastNameContaining(String lastName, Pageable pageable);
}
