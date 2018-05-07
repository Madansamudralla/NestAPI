package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.RewardsDataBean;
import com.infostretch.nest.providers.RewardsEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
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
	JsonArray jsonArrayResult, jsonArrayResult1;
	int index;
	Response response;
	String verifyResponse;
	RewardsDataBean rewardsDataBean = new RewardsDataBean();

	@QAFTestStep(description = "user can get the image list for R&R")
	public void userCanGetTheImageListForRR() {

		rewardsDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("category_id", rewardsDataBean.getCategory_id());
		ClientUtils.getWebResource(RewardsEndPoints.GET_IMAGE_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("rr_image_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("image").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user can get the list of bands for R&R")
	public void userCanGetTheListOfBandsForRR() {

		ClientUtils.getWebResource(RewardsEndPoints.GET_LIST_OF_BAND)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "job_title");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "job_level");
		}
	}

	@QAFTestStep(description = "user can get the list of groups for filter for R&R")
	public void userCanGetTheListOfGroupsForFilterForRR() {

		ClientUtils.getWebResource(RewardsEndPoints.GET_LIST_OF_GROUPS_FOR_FILTER)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "name");
		}
	}

	@QAFTestStep(description = "user can get the list of employee for R&R")
	public void userCanGetTheListOfEmployeeForRR() {

		ClientUtils.getWebResource(RewardsEndPoints.GET_LIST_OF_EMPLOYEE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "emp_number");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "name");

		}
	}

	@QAFTestStep(description = "user can get the list of my nomination for R&R")
	public void userCanGetTheListOfMyNominationForRR() {

		ClientUtils.getWebResource(RewardsEndPoints.GET_LIST_OF_MY_NOMINATIONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();
		jsonArrayResult1 = jsonArrayResult.get(0).getAsJsonObject().get("rrdetails")
				.getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult1.get(index).getAsJsonObject(), "message_summary_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult1.get(index).getAsJsonObject(), "message_category_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult1.get(index).getAsJsonObject(), "nominee_emp_number");

		}
	}

	@QAFTestStep(description = "user can get all employee list for expense for R&R")
	public void userCanGetAllEmployeeListForExpenseForRR() {

		ClientUtils.getWebResource(RewardsEndPoints.GET_ALL_EMPLOYEE_LIST_FOR_EXPENSE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "display_name");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "emp_number");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "emp_firstname");
		}
	}

	
	@QAFTestStep(description = "user can get the status list for R&R")
	public void userCanGetTheStatusListForRR() {
		
		ClientUtils.getWebResource(RewardsEndPoints.GET_STATUS_LIST)
			.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
			.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
			new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		Reporter.log("Result Body :- "+responseBody.get("response").getAsJsonObject().get("results").toString());
		verifyResponse = responseBody.get("response").getAsJsonObject().get("results").toString();
		
		if(verifyResponse.contains("Pending") && verifyResponse.contains("Approved") && verifyResponse.contains("Rejected"))
		{
			Reporter.log("---Verified---");
		}
				
	}

}
