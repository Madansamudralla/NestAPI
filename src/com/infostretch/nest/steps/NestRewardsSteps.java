package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.providers.RewardsEndPoints;
import com.infostretch.nest.providers.TravelEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestRewardsSteps {
	
	JSONObject jsonObject, jsonObject1;
	JsonObject responseBody, jsonObjectResult;
	JSONArray jsonArray;
	JsonArray jsonArrayResult;
	int index;
	Response response;
	
	@QAFTestStep(description = "user can get the image list for R&R")
	public void userCanGetTheImageListForRR() {

		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("category_id", "1");
		ClientUtils.getWebResource(RewardsEndPoints.GET_IMAGE_LIST)
				.type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

}
