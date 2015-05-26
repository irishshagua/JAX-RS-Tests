package com.mooneyserver;

import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SomeResourceTest {

	@Deployment
	public static WebArchive createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(RestApplicationRoot.class, SomeResource.class,
						QueryParamBasedFilter.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@RunAsClient
	public void testGetAllTheStuffs(@ArquillianResource URL base)
			throws URISyntaxException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(base.toURI() + "rest/stuff");
		Response response = target.request().get();

		Assert.assertEquals(Response.Status.OK.getStatusCode(),
				response.getStatus());

	}
	
	@Test
	@RunAsClient
	public void testGetAllTheStuffsWithQueryParams(@ArquillianResource URL base)
			throws URISyntaxException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(base.toURI() + "rest/stuff?number-of-results=5");
		Response response = target.request().get();

		Assert.assertEquals(Response.Status.OK.getStatusCode(),
				response.getStatus());

	}
}
