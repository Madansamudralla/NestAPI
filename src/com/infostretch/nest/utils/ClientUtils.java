package com.infostretch.nest.utils;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ws.Response;
import com.qmetry.qaf.automation.ws.rest.RestTestBase;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ClientUtils {

	public static Client getClient() {
		return new RestTestBase().getClient();
	}

	public static WebResource getWebResource(String path) {
		return getClient().resource(ConfigurationManager.getBundle().getString("env.baseurl")).path(path);
	}

	public static Response getResponse() {
		return new RestTestBase().getResponse();
	}

}
