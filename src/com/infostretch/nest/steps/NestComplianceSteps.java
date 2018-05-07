package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.providers.ComplianceEndPoints;
import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
import com.sun.jersey.api.client.ClientResponse;

public class NestComplianceSteps {
	JSONObject jsonObject, jsonObject1;
	JsonObject jsonObjectResult, responseBody;
	JSONArray jsonArray;
	JsonArray jsonArrayResult;
	int index;
	Response response;

	@QAFTestStep(description = "user should checkIfLogin.php?callback=angular.callbacks.")
	public void userShouldCheckIfLoginPhpCallbackAngularCallbacks() {
		ClientUtils.getWebResource(ComplianceEndPoints.GET_NEST_COUNTRY_LIST)
				.get(ClientResponse.class);
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get gender commitee list")
	public void userShouldGetGenderCommiteeList() {
		ClientUtils.getWebResource(ComplianceEndPoints.GET_GENDER_COMMITEE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("gender_commitee_master_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("mobile_number").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get gender sexual harassment policy")
	public void userShouldGetGenderSexualHarassmentPolicy() {
		ClientUtils
				.getWebResource(ComplianceEndPoints.GET_GENDER_SEXUAL_HARASSMENT_POLICY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "name");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "path");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get pdf document for quiz")
	public void userShouldGetPdfDocumentForQuiz() {
		ClientUtils.getWebResource(ComplianceEndPoints.GET_PDF_DOCUMENT_FOR_QUIZ)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("path").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("name").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonArrayResult.toString());
	}

}
