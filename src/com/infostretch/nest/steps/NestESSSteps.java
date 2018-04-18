package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestESSSteps {

	@QAFTestStep(description = "user should get-basic-details")
	public void userShouldGetBasicDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", "113");//ConfigurationManager.getBundle().getPropertyValue("emp_id");
		ClientUtils.getWebResource(ESSEndPoints.GET_BASIC_DETAILS).type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject();
		Validator.verifyThat(results, Matchers.notNullValue());
		
	}

	@QAFTestStep(description = "user should get-personal-details")
	public void userShouldGetPersonalDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", "113");//ConfigurationManager.getBundle().getPropertyValue("emp_id");
		ClientUtils.getWebResource(ESSEndPoints.GET_PERSONAL_DETAILS).type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		JsonObject responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results").getAsJsonObject();
		Validator.verifyThat(results, Matchers.notNullValue());
	}
	

}
