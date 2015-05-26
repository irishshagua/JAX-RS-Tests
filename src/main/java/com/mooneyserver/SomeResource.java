package com.mooneyserver;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("stuff")
public class SomeResource {

	@GET
	public Response getAllTheStuffs(@Valid @BeanParam QueryParamBasedFilter filter) {
		System.out.println(" **** Received Query Parameters: " + filter);
		return Response.ok().build();
	}
}
