package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.SurveyQuizEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestSurveyQuizSteps {
	JSONObject jsonObject, jsonObject1, jsonObject2;
	JsonObject jsonObjectResult, responseBody;
	JSONArray jsonArray;
	JsonArray jsonArrayResult;
	int index;
	Response response;

	@QAFTestStep(description = "user should get survey template list for employee")
	public void userShouldGetSurveyTemplateListForEmployee() {
		ClientUtils
				.getWebResource(SurveyQuizEndPoints.GET_SURVEY_TEMPLATE_LIST_FOR_EMPLOYEE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("survey_template_master_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("survey_name").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get employee details")
	public void userShouldGetEmployeeDetails() {
		ClientUtils.getWebResource(SurveyQuizEndPoints.GET_EMPLOYEE_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "department");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_firstname");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should view survey template response")
	public void userShouldViewSurveyTemplateResponse() {
		//JsonObject jsonObjectResult1 = new JsonObject();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("survey_template_master_id","4");
		jsonObject.put("details",jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(SurveyQuizEndPoints.VIEW_SURVEY_TEMPLATE_RESPONSE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
//		jsonObjectResult1=jsonObjectResult.get("templatedetail").getAsJsonObject();
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("templatedetail").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"survey_template_master_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,"survey_name");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get all employee details")
	public void userShouldGetAllEmployeeDetails() {
		ClientUtils.getWebResource(SurveyQuizEndPoints.GET_ALL_EMPLOYEE_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonArrayResult.toString());
	}

	@QAFTestStep(description = "user should get survey template list")
	public void userShouldGetSurveyTemplateList() {
		ClientUtils.getWebResource(SurveyQuizEndPoints.GET_SURVEY_TEMPLATE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("survey_template_master_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("survey_name").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get nest country list")
	public void userShouldGetNestCountryList() {
		ClientUtils.getWebResource(SurveyQuizEndPoints.GET_NEST_COUNTRY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("country_code").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("loc_name").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonArrayResult.toString());
	}

	@QAFTestStep(description = "user should add edit survey template")
	public void userShouldAddEditSurveyTemplate() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("survey_name", "testing");
		jsonObject1.put("type","1");
		jsonObject1.put("status","1");
		jsonObject1.put("survey_template_master_id","4");
		jsonObject.put("survey_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(SurveyQuizEndPoints.ADD_EDIT_SURVEY_TEMPLATE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"survey_template_master_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit survey template status")
	public void userShouldEditSurveyTemplateStatus() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("survey_template_master_id", "6");
		jsonObject.put("status", "0");
		ClientUtils.getWebResource(SurveyQuizEndPoints.EDIT_SURVEY_TEMPLATE_STATUS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"survey_template_master_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get survey question types")
	public void userShouldGetSurveyQuestionTypes() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("type", "1");
		ClientUtils.getWebResource(SurveyQuizEndPoints.GET_SURVEY_QUESTION_TYPES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("value").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonArrayResult.toString());
	}

	@QAFTestStep(description = "user should get survey template question detail")
	public void userShouldGetSurveyTemplateQuestionDetail() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("survey_template_questions_id", "6");
		jsonObject.put("survey_template_master_id", "6");
		ClientUtils
				.getWebResource(SurveyQuizEndPoints.GET_SURVEY_TEMPLATE_QUESTION_DETAIL)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("survey_template_master_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("survey_template_questions_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("question").toString(), Matchers.notNullValue());
		}

		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should add edit survey template question")
	public void userShouldAddEditSurveyTemplateQuestion() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("survey_template_questions_id", "6");
		jsonObject1.put("template_type", "1");
		jsonObject1.put("question", "Test");
		jsonObject1.put("survey_template_master_id", "6");
		jsonObject.put("details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(SurveyQuizEndPoints.ADD_EDIT_SURVEY_TEMPLATE_QUESTION)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"survey_template_questions_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get survey template question list")
	public void userShouldGetSurveyTemplateQuestionList() {
		JsonObject jsonObjectResult1 = new JsonObject();
		JsonObject jsonObjectResult2 = new JsonObject();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("survey_template_master_id", "6");
		ClientUtils.getWebResource(SurveyQuizEndPoints.GET_SURVEY_TEMPLATE_QUESTION_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonObjectResult1 = jsonObjectResult.get("permissionArr").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult1, "module_superadmin");
		jsonObjectResult2 = jsonObjectResult.get("details").getAsJsonObject()
				.get("templatedetail").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult2,
				"survey_template_master_id");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should save submit survey template question list")
	public void userShouldSaveSubmitSurveyTemplateQuestionList() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("survey_template_questions_id", "6");
		jsonObject2.put("order", "1");
		jsonObject1.put("0", jsonObject2);
		jsonObject.put("questions", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("survey_template_master_id", "6");
		jsonObject.put("survey_status", "1");
		ClientUtils
				.getWebResource(
						SurveyQuizEndPoints.SAVE_SUBMIT_SURVEY_TEMPLATE_QUESTION_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"survey_template_master_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}
}
