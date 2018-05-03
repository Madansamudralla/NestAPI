package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;
import com.qmetry.qaf.automation.ws.Response;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.providers.IFSEndpoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.util.Validator;
import com.sun.glass.ui.Application;

public class IFSsteps {
	JSONObject jsonObject;
	JsonObject result;
	JsonArray results;
	Response response;
	int index;
	@QAFTestStep(description = "user should get my feedback")
	public void userShouldGetMyFeedback() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(IFSEndpoints.GET_MY_FEEDBACK)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
			Validator.verifyThat(results.size(), Matchers.greaterThan(0));
			
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("efs_feedback_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("employee_id").toString(),
					Matchers.notNullValue());
		}

	}
}
