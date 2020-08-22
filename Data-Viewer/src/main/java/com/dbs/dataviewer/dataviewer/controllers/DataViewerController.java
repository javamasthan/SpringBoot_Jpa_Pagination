package com.dbs.dataviewer.dataviewer.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.dataviewer.dataviewer.entity.CustomerEntity;
import com.dbs.dataviewer.dataviewer.excetion.CustomErrorMessage;
import com.dbs.dataviewer.dataviewer.excetion.DataBaseException;
import com.dbs.dataviewer.dataviewer.excetion.FieldValidationException;
import com.dbs.dataviewer.dataviewer.service.DataViewerService;

@RestController
public class DataViewerController {
	private static final Logger log = LoggerFactory.getLogger(DataViewerController.class);
	
	@Autowired
	private DataViewerService dataViewerService;
	
	@GetMapping(value="/customerinfo")
    public ResponseEntity<Map<String, Object>> getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(required=false) String sortBy,
                        @RequestParam(required=false) String filterBy
                        ) throws FieldValidationException,DataBaseException,Exception
    
    {
		log.info("[************getAllEmployees controller metnod begins...****************]");
		
        Page<CustomerEntity> list = dataViewerService.getAllCustomersInfo(pageNo, pageSize, sortBy,filterBy);
        if (list.isEmpty()) {
        	CustomErrorMessage errorMsg = new CustomErrorMessage(HttpStatus.BAD_REQUEST,
    				"Sorry!!! No data found with Search Criteria !!!");
    		throw new FieldValidationException(errorMsg);
          }
        List<CustomerEntity> filterByList=null;
			if (filterBy != null && !filterBy.isEmpty()) {
				filterByList=this.getFilterBySearchData(list, filterBy);
			} 
          Map<String, Object> response = new HashMap<>();
          response.put("searchData", filterByList!=null &&filterByList.size()>0?filterByList:list.getContent());
          response.put("currentPage", list.getNumber());
          response.put("totalItems", list.getTotalElements());
          response.put("totalPages", list.getTotalPages());
          
          log.info("[************getAllEmployees controller metnod end...****************]");
          return new ResponseEntity<>(response, HttpStatus.OK);
    
    }
	
	List<CustomerEntity> getFilterBySearchData(Page<CustomerEntity> list,String filterBy ){
		List<CustomerEntity> filterByList = new ArrayList<>();
		for (CustomerEntity cus : list.getContent()) {
		if (cus.getFirstName().toLowerCase().contains(filterBy.toLowerCase())) {
			filterByList.add(cus);
		} if (cus.getLastName().toLowerCase().contains(filterBy.toLowerCase())) {
			filterByList.add(cus);
		}
		if (cus.getEmail().toLowerCase().contains(filterBy.toLowerCase())) {
			filterByList.add(cus);
		}
		if (cus.getMobile().toLowerCase().contains(filterBy.toLowerCase())) {
			filterByList.add(cus);
		}
		boolean flag=true;
		try {  
		    Double.parseDouble(filterBy);  
		  } catch(NumberFormatException e){  
			  flag= false;  
		  }  
		if (flag && cus.getId()==Long.valueOf(filterBy)) {
			filterByList.add(cus);
		}
		}
		
		return filterByList;

	}
}
