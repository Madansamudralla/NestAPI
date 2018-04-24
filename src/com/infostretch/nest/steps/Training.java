/**
 * 
 */
package com.infostretch.nest.steps;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.infostretch.nest.providers.TrainingEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

/**
 * @author Megha.Garg
 */
public class Training {
	@QAFTestStep(description = "user should get training calender")
	public void ShouldGetTrainingCalender() {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("month", month);
		obj.put("year", year);
		ClientUtils.getWebResource(TrainingEndPoints.Training_Calendar)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should verify training details for course ID {0}")
	public void ShouldVerifyTrainingDetailsForCourseID(String id) {
		JSONObject ob = new JSONObject();
		ob.put("token", TokenUtils.getTokenAsStr());
		ob.put("trn_course_id", id);
		ClientUtils.getWebResource(TrainingEndPoints.Training_Detail)
				.type(MediaType.APPLICATION_JSON).post(ob.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should verify that he is attending training")
	public void userShouldVerifyThatHeIsAttendingTarining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_AM_Attending)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should verify that he has attended training")
	public void userShouldVerifyThatHeHasAttendedTraining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_HAVE_ATTENDED)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should verify that he has taught training")
	public void userShouldVerifyThatHeHasTaughtTraining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_HAVE_TAUGHT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

}
