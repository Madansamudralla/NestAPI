package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.providers.TrainingEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestTrainingSteps {
	Response response;
	JsonArray results, NewResultArray;
	JsonObject result, newResult;
	int index;
	JSONObject jsonObject, jsonObject2;

	@QAFTestStep(description = "user should get training list dd")
	public void userShouldGetTrainingListDd() {
		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINING_LIST_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("trainer").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("start_date").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("trn_venue_id").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should export summary report")
	public void userShouldExportSummaryReport() {
		ClientUtils.getWebResource(TrainingEndPoints.EXPORT_SUMMARY_REPORT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Download_URL");
	}

	@QAFTestStep(description = "user should get summary report")
	public void userShouldGetSummaryReport() {
		ClientUtils.getWebResource(TrainingEndPoints.GET_SUMMARY_REPORT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);

		NewResultArray = result.get("details").getAsJsonArray();
		for (index = 0; index <= NewResultArray.size() - 1; index++) {
			Validator.verifyThat((NewResultArray.get(index).getAsJsonObject())
					.get("ctitle").toString(), Matchers.notNullValue());
			Validator.verifyThat((NewResultArray.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((NewResultArray.get(index).getAsJsonObject())
					.get("description").toString(), Matchers.notNullValue());
			Validator.verifyThat((NewResultArray.get(index).getAsJsonObject())
					.get("instructor").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should delete venue")
	public void userShouldDeleteVenue() {
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("trn_venue_id", "12");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("venue_details", jsonObject2);
		ClientUtils.getWebResource(TrainingEndPoints.DELETE_VENUE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "trn_venue_id");
		Validator.verifyThat((result.getAsJsonObject()).get("action_message").toString(),
				Matchers.containsString("Venue removed successfully"));
		CommonUtils.validateParameterInJsonObject(result, "response_type");
	}
}
