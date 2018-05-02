/**
 * 
 */
package com.infostretch.nest.steps;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.TrainingBean;
import com.infostretch.nest.providers.TrainingEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.NotYetImplementedException;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

/**
 * Pranita Sinkar
 */
public class Training {
	JSONObject jsonObject;
	JsonObject object2Result;
	JsonObject object1Result;
	JsonObject result, responseBody;
	JSONArray jsonArray;
	JsonArray results;
	int year;
	int month;
	int index;
	Response response;
	TrainingBean trainingBean = new TrainingBean();

	@QAFTestStep(description = "user should get training calender")
	public void ShouldGetTrainingCalender() {

		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("month", month);
		jsonObject.put("year", year);
		ClientUtils.getWebResource(TrainingEndPoints.Training_Calendar)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "monthname");
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		results = result.get("13").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("trainer").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("trainingdate").toString(),
					Matchers.notNullValue());

		}
	}
	@QAFTestStep(description = "user should verify training details for course ID")
	public void ShouldVerifyTrainingDetailsForCourseID() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", trainingBean.getTrn_course_id());
		ClientUtils.getWebResource(TrainingEndPoints.Training_Detail)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		object1Result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("trainer_details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(object1Result, "department");
		CommonUtils.validateParameterInJsonObject(object1Result, "emp_number");
		CommonUtils.validateParameterInJsonObject(object1Result, "ename");
		
		object2Result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("training_detail").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "trn_course_id");
		CommonUtils.validateParameterInJsonObject(result, "description");
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
	}

	@QAFTestStep(description = "user should verify that he is attending training")
	public void userShouldVerifyThatHeIsAttendingTarining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_AM_Attending)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should verify that he has attended training")
	public void userShouldVerifyThatHeHasAttendedTraining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_HAVE_ATTENDED)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should verify that I have taught")
	public void userShouldVerifyThatHeHasTaughtTraining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_HAVE_TAUGHT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	/**
	 * Auto-generated code snippet by QMetry Automation Framework.
	 */
	@QAFTestStep(description = "user should verify that I am teaching")
	public void userShouldVerifyThatIHasTaughtTraining() {
		ClientUtils.getWebResource(TrainingEndPoints.GET_I_AM_TEACHING)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	/**
	 * Feedback questions list
	 */
	@QAFTestStep(description = "user should get feedback questions list")
	public void userShouldGetFeedbackQuestionsList() {
		// TODO: remove NotYetImplementedException and call test steps
		throw new NotYetImplementedException();
	}


}
