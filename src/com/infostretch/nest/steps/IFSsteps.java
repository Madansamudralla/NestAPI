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
	JSONObject jsonObject, jsonObject1;
	JsonObject jsonObjectResult;
	JsonArray jsonArrayResult;
	Response response;
	int index;
	@QAFTestStep(description = "user should get my feedback")
	public void userShouldGetMyFeedback() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(IFSEndpoints.GET_MY_FEEDBACK)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("efs_feedback_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("employee_id").toString(), Matchers.notNullValue());
		}
	}
	@QAFTestStep(description = "user should get IFS Category List")
	public void userShouldGetIFSCategoryList() {
		ClientUtils.getWebResource(IFSEndpoints.GET_IFS_CATEGORY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();

	}

	@QAFTestStep(description = "user should post IFS feedback")
	public void userShouldPostIfsFeedback() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());

		jsonObject1 = new JSONObject();
		jsonObject1.put("category", "0");
		jsonObject1.put("description", "good friend");
		jsonObject1.put("category_other", "Friend");
		jsonObject.put("feedbackdata", jsonObject1);
		ClientUtils.getWebResource(IFSEndpoints.POST_IFS_FEEDBACK)
				.type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"message");

	}

}
