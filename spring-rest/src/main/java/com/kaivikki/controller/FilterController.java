package com.kaivikki.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kaivikki.model.DynamicFilterBean;
import com.kaivikki.model.StaticFilterBean;

@RestController
public class FilterController {

	@GetMapping("/testFilter")
	public StaticFilterBean testFilter() {
		return new StaticFilterBean("value1", "value2", "value3");
	}
	
	//Dynamic Filter Example to filter field 2 from API response.
	@GetMapping("/testFilterField2")
	public MappingJacksonValue filterField2() {
		DynamicFilterBean filterBean = new DynamicFilterBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterBeanField2Filter", filter );
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;	
	}
}
