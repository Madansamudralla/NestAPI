package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.bean.TrainingBean;
import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.providers.TrainingEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestTrainingSteps {
	JSONObject jsonObject, jsonObject2;
	JsonObject result, responseBody;
	JSONArray jsonArray;
	JsonArray results, object1Result;
	int index;
	Response response;
	TrainingBean trainingBean = new TrainingBean();

	@QAFTestStep(description="user should export training feedback report")
	public void userShouldExportTrainingFeedbackReport(){
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", trainingBean.getTrn_course_id());
		ClientUtils.getWebResource(TrainingEndPoints.EXPORT_TRAINING_FEEDBACK_REPORT)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Download_URL");

	}
	
	@QAFTestStep(description = "user should get trainer list dd")
	public void userShouldGetTrainerListDd() {
		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINER_LIST_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("city").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get active venue list dd")
	public void userShouldGetActiveVenueListDd() {
		ClientUtils.getWebResource(TrainingEndPoints.GET_ACTIVE_VENUE_LIST_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("trn_venue_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get active business unit")
	public void userShouldGetActiveBusinessUnit() {
		ClientUtils.getWebResource(TrainingEndPoints.GET_ACTIVE_BUSINESS_UNIT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("country").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get training members")
	public void userShouldGetTrainingMembers() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", trainingBean.getTrn_course_id());
		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINING_MEMBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject())
							.get("trn_course_registration_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("job_title").toString(), Matchers.notNullValue());
		}
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should add edit traning members")
	public void userShouldAddEditTraningMembers() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("trn_course_id", trainingBean.getTrn_course_id());
		jsonObject.put("training", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(TrainingEndPoints.GET_EDIT_TRAINING_MEMBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "trn_course_id");
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get emplist for training nomination dd")
	public void userShouldGetEmplistForTrainingNominationDd() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", trainingBean.getTrn_course_id());
		ClientUtils
				.getWebResource(TrainingEndPoints.GET_EMPLIST_FOR_TRAINING_NOMINATION_DD)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
	}
}
