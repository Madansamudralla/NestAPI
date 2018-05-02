package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.bean.TrainingBean;
import com.infostretch.nest.providers.TrainingEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestTrainingSteps {

	public Response response;
	public JsonArray jsonArrayResult;
	public JsonObject jsonObjectResult;
	public JSONObject jsonObject, jsonObject1;
	public int index;
	TrainingBean trainingBean = new TrainingBean();

	@QAFTestStep(description = "user can login as a manager")
	public String userCanLoginAsAManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user can get the course list for training module")
	public void userCanGetTheCourseListForTrainingModule() {

		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject.put("token", "078a0cceb927ab4f5fc8d7d8cfe91f04");
		jsonObject.put("search", jsonObject1);
		jsonObject1.put("title", "");
		jsonObject1.put("date_from", trainingBean.getDate_from());
		jsonObject1.put("date_to", trainingBean.getDate_to());
		jsonObject.put("sort", "");
		jsonObject.put("order", "");
		ClientUtils.getWebResource(TrainingEndPoints.GET_COURSE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "training_admin");
		CommonUtils.validateParameterInJsonObject(
				jsonArrayResult.get(0).getAsJsonObject(), "trn_course_id");
		CommonUtils.validateParameterInJsonObject(
				jsonArrayResult.get(0).getAsJsonObject(), "title");
		Reporter.log(trainingBean.getDate_from());
	}

	@QAFTestStep(description = "user can get the statistics for training module")
	public void userCanGetTheStatisticsForTrainingModule() {

		ClientUtils.getWebResource(TrainingEndPoints.GET_STATISTICS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();
		CommonUtils.validateParameterInJsonObject(
				jsonArrayResult.get(0).getAsJsonObject(), "emp_number");
		CommonUtils.validateParameterInJsonObject(
				jsonArrayResult.get(0).getAsJsonObject(), "emp_firstname");
		CommonUtils.validateParameterInJsonObject(
				jsonArrayResult.get(0).getAsJsonObject(), "total_registrations");
	}

	@QAFTestStep(description = "user can get the training type dd for training module")
	public void userCanGetTheTrainingTypeDdForTrainingModule() {

		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINING_TYPE_DD)
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

	@QAFTestStep(description = "user can get the individual emp statistics for training module")
	public void userCanGetTheIndividualEmpStatisticsForTrainingModule() {

		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject.put("token", "8e3956c3b7c32709cdc8c40a0a93c103");
		jsonObject.put("statistics", jsonObject1);
		jsonObject1.put("emp_number", trainingBean.getEmp_number());
		jsonObject1.put("type", trainingBean.getType());
		jsonObject1.put("fromDate", trainingBean.getFromDate());
		jsonObject1.put("toDate", trainingBean.getToDate());
		ClientUtils.getWebResource(TrainingEndPoints.GET_INDIVIDUAL_EMP_STATISTICS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response).get("empdetails")
				.getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "department");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "employee_id");
		Reporter.log(trainingBean.getEmp_number());
	}

	@QAFTestStep(description = "user can get the venue list for training module")
	public void userCanGetTheVenueListForTrainingModule() {

		ClientUtils.getWebResource(TrainingEndPoints.GET_VENUE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidateResultObject(response).get("details")
				.getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "trn_venue_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "title");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "description");
		}
	}

	@QAFTestStep(description = "user can added and edited venue list for training module")
	public void userCanAddedAndEditedVenueListForTrainingModule() {

		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject.put("token", "17583f751bbfe53a3496023f718f870e");
		jsonObject1.put("trn_venue_id", trainingBean.getTrn_venue_id());
		jsonObject1.put("title", trainingBean.getTitle());
		jsonObject1.put("description", trainingBean.getDescription());
		jsonObject1.put("status", trainingBean.getStatus());
		jsonObject.put("venue_details", jsonObject1);
		ClientUtils.getWebResource(TrainingEndPoints.ADD_EDIT_VENUE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "trn_venue_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can edit venue status for training module")
	public void userCanEditVenueStatusForTrainingModule() {

		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", "90b4d7050561eaf130b33b7fa597181d");
		jsonObject.put("trn_venue_id", trainingBean.getTrn_venue_id());
		jsonObject.put("status", "0");
		ClientUtils.getWebResource(TrainingEndPoints.EDIT_VENUE_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "trn_venue_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

}
