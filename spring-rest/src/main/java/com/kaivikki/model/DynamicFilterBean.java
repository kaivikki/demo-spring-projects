package com.kaivikki.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonFilter("FilterBeanField2Filter")
public class DynamicFilterBean {
	private String field1;
	private String field2;
	private String field3;
}
