package com.infostretch.nest.utils;
import org.hamcrest.Matchers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qmetry.qaf.automation.testng.dataprovider.QAFDataProvider;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class CommonUtils {

	/*
	 * Description : This will validate the response is null or not.
	 * Param : Response
	 * Return : True if response in not null
	 */

	@QAFDataProvider
	public static JsonObject getValidateResultObject(Response response) {
		
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject();
		Validator.assertThat(results, Matchers.notNullValue());
		return results;
	 }

	/*
	 * Description : Validates the passed parameter is null or not in response
	 * Param : Response , Parameter name
	 * Return :value of passed parameter
	 */
	public static String validateParameterInJsonObject(JsonObject object,
			String parameterName) {
		String ParameterValue = object.get(parameterName).getAsString();
		Validator.assertThat(ParameterValue, Matchers.notNullValue());
		return ParameterValue;
	}

	/*
	 * Description : This will validate the result array is null or not.
	 * Param : Response
	 * Return : JsonArray of result
	 */
	public static JsonArray getValidatedResultArray(Response response) {
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonArray results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonArray();
		Validator.assertThat(results, Matchers.notNullValue());
		return results;
	}
}
