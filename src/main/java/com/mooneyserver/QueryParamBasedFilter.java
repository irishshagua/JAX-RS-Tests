package com.mooneyserver;

import javax.ws.rs.QueryParam;

import lombok.Data;

@Data
public class QueryParamBasedFilter {

	@QueryParam("number-of-results")
	private Integer numResults;

	@QueryParam("filter")
	private String someOtherFilter;
	
	@Override
	public String toString() {
		return String.format("{numResults: %d, filter: %s}", numResults, someOtherFilter);
	}
}
