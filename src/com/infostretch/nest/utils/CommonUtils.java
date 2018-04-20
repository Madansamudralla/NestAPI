package com.infostretch.nest.utils;
import org.hamcrest.Matchers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class CommonUtils {

	public static boolean validateResult(Response response) {
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject();
		Validator.assertThat(results, Matchers.notNullValue());
		return true;
	}

	public static String validateResultParameter(Response response,
			String parameterName) {
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject();
		Validator.assertThat(results, Matchers.notNullValue());
		String ParameterValue = results.get(parameterName).getAsString();
		Validator.assertThat(ParameterValue, Matchers.notNullValue());
		return ParameterValue;
	}
}
