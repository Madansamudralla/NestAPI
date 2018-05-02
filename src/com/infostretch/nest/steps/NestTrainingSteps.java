package com.infostretch.nest.steps;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import com.google.gson.JsonObject;
import com.infostretch.nest.bean.TrainingBean;
import com.infostretch.nest.providers.TrainingEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestTrainingSteps {
	JSONObject jsonObject, jsonObject1, newResult;
	JSONObject jsonObject2;
	JsonObject result, responseBody, jsonObjectResult;
	JSONArray jsonArray;
	JsonArray results, object1Result, jsonArrayResult, NewResultArray;
	int index;
	Response response;
	int year;
	int month;
	
	TrainingBean trainingBean = new TrainingBean();

	@QAFTestStep(description = "user should get training calender")
	public void ShouldGetTrainingCalender() {

		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) ;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("month", month);
		jsonObject.put("year", year);
		ClientUtils.getWebResource(TrainingEndPoints.Training_Calendar)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "monthname");
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
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", trainingBean.getTrn_course_id());
		System.out.println("Training Bean "+trainingBean.getTrn_course_id());
		ClientUtils.getWebResource(TrainingEndPoints.Training_Detail)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("trainer_details").getAsJsonObject();
		System.out.println(responseBody);
		CommonUtils.validateParameterInJsonObject(result, "department");
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "ename");
		
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
	//	jsonObject.put("token", TokenUtils.getTokenAsStr());
//		jsonObject.put("sort", "");
//		jsonObject.put("order", "");
//		
		ClientUtils.getWebResource(TrainingEndPoints.I_HAVE_ATTENDED)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();
		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_firstname").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_lastname").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trainer").toString(), Matchers.notNullValue());
		
		}
	}

	@QAFTestStep(description = "user should verify that I have taught")
	public void userShouldVerifyThatHeHasTaughtTraining() {
		ClientUtils.getWebResource(TrainingEndPoints.I_HAVE_TAUGHT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();
		
		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_firstname").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_lastname").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trainer").toString(), Matchers.notNullValue());

		}
	}

	/**
	 * Auto-generated code snippet by QMetry Automation Framework.
	 */
	@QAFTestStep(description = "user should verify that I am teaching")
	public void userShouldVerifyThatIHasTaughtTraining() {
		trainingBean.fillRandomData();


		ClientUtils.getWebResource(TrainingEndPoints.GET_I_AM_TEACHING)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();
		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trn_venue_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trainer").toString(), Matchers.notNullValue());
		
		}

	}

	/**
	 * Feedback questions list
	 */

	@QAFTestStep(description = "user should get training dates dd")
	public void userShouldGetTrainingDatesDd() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", "8");
		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINING_DATES_DD)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("trn_course_time_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("date").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get training members for attendance")
	public void userShouldGetTrainingMembersForAttendance() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", "8");
		jsonObject.put("trn_course_time_id", "8");
		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINING_MEMBERS_FOR_ATTENDENCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject())
							.get("trn_course_registration_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("displayPicture").toString(), Matchers.notNullValue());
		}
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should export training feedback report")
	public void userShouldExportTrainingFeedbackReport() {
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

	@QAFTestStep(description = "user should get registered employee list dd")
	public void userShouldGetRegisteredEmployeeListDd() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("trn_course_id", "8");
		jsonObject.put("trn_course_time_id", "8");
		ClientUtils.getWebResource(TrainingEndPoints.GET_REGISTERED_EMPLOYEE_LIST_DD)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("employee_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
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

	@QAFTestStep(description = "user can login as a manager")
	public String userCanLoginAsAManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user can get the course list for training module")
	public void userCanGetTheCourseListForTrainingModule() {

		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
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
		jsonObject.put("token", TokenUtils.getTokenAsStr());
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
		jsonObject.put("token", TokenUtils.getTokenAsStr());
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

	@QAFTestStep(description = "user should get training list dd")
	public void userShouldGetTrainingListDd() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject2 = new JSONObject();
		jsonObject2.put("date_from", trainingBean.getDate_from());
		jsonObject2.put("date_to", trainingBean.getDate_to());
		jsonObject.put("search", jsonObject2);
		ClientUtils.getWebResource(TrainingEndPoints.GET_TRAINING_LIST_DD)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("trn_venue_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("start_date").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get summary report")
	public void userShouldGetSummaryReport() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject2 = new JSONObject();
		jsonObject2.put("start_time", trainingBean.getDate_from());
		jsonObject2.put("end_time", trainingBean.getDate_to());
		jsonObject.put("trainingSearchDetails", jsonObject2);
		ClientUtils.getWebResource(TrainingEndPoints.GET_SUMMARY_REPORT)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		jsonObjectResult = result.getAsJsonObject();
		object1Result = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("ctitle").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("instructor").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("date").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should export summary report")
	public void userShouldExportSummaryReport() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject2 = new JSONObject();
		jsonObject2.put("start_time", trainingBean.getDate_from());
		jsonObject2.put("end_time", trainingBean.getDate_to());
		jsonObject.put("trainingSearchDetails", jsonObject2);
		ClientUtils.getWebResource(TrainingEndPoints.EXPORT_SUMMARY_REPORT)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		Validator.verifyThat((result.getAsJsonObject()).get("Download_URL").toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user should delete venue")
	public void userShouldDeleteVenue() {
		trainingBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject2 = new JSONObject();
		jsonObject2.put("trn_venue_id", trainingBean.getTrn_venue_id());
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
