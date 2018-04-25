package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.VisaEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestVisaSteps {
	JSONObject jsonObject, jsonObject1;
	Response response;
	JsonObject result, responseBody;
	JsonArray results;
	int index;

	@QAFTestStep(description = "user should get visa country list")
	public void UserShouldGetVisaCountryList() {

		ClientUtils.getWebResource(VisaEndPoints.VISA_COUNTRY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_country_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("country_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("created_date").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("created_by_emp_number").toString(), Matchers.notNullValue());
		}

	}
	@QAFTestStep(description = "user should get supervisor name")
	public void UserShouldGetSupervisorName() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_SUPERVISOR_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());

		}
	}

	@QAFTestStep(description = "user should get visa request list for employee")
	public void userShouldGetVisaRequestListForEmployee() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());

		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", "1-4-2018");
		jsonObject1.put("initiated_to", "30-4-2018");
		jsonObject1.put("status", "");
		jsonObject1.put("visa_status", "");
		jsonObject1.put("visa_country_id", "");
		jsonObject1.put("visa_type_id", "");
		jsonObject.put("sort", "");
		jsonObject.put("search", jsonObject1);
		jsonObject.put("order", "");
		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_EMPLOYEE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("user_type").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "isAdminAuthority");
		CommonUtils.validateParameterInJsonObject(result, "isApprovalAdmin");

	}

	@QAFTestStep(description = "user should get visa type for dd")
	public void UserShouldGetVisaTypeForDd() {

		ClientUtils.getWebResource(VisaEndPoints.VISA_TYPE_FOR_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get all employee list of expense")
	public void UserShouldGetAllEmployeeListOfExpense() {

		ClientUtils.getWebResource(VisaEndPoints.VISA_ALL_EMPLOYEE_LIST_EXPENSE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("display_name").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());

		}
	}
	@QAFTestStep(description = "user should get visa request list for approvingauth")
	public void userShouldGetVisaRequestListForApprovingauth() {

		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_APPROVING_AUTH)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("reporting_mngr").toString(), Matchers.notNullValue());

		}

	}

}
