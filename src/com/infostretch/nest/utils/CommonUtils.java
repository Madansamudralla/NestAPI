package com.infostretch.nest.utils;
import org.hamcrest.Matchers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class CommonUtils {

	public static boolean validateResposneParameter(Response response, String parameterName) {
		JsonObject responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results").getAsJsonObject();
		Validator.assertThat(results, Matchers.notNullValue());
		Validator.assertThat(results.get(parameterName).getAsString(), Matchers.notNullValue());
		return true;
	}
}
