package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.ESSBean;
import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestESSSteps {
	JSONObject jsonObject, jsonObject1;
	JsonObject jsonObjectResult, responseBody;
	JSONArray jsonArray;
	JsonArray jsonArrayResult;
	int index;
	Response response;
	ESSBean essBean = new ESSBean();;

	@QAFTestStep(description = "user should get-basic-details")
	public void userShouldGetBasicDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_BASIC_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_firstname");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "ename");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get-personal-details")
	public void userShouldGetPersonalDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PERSONAL_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_gender");
	}

	@QAFTestStep(description = "user should get-base-location")
	public void userShouldGetBaseLocation() {
		ClientUtils.getWebResource(ESSEndPoints.GET_BASE_LOCATION)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("city").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("country_code").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get idenity details")
	public void userShouldGetIdenityDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_IDENTITY_DATAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_dri_lice_num");
		Reporter.log("Emp Number : " + CommonUtils
				.validateParameterInJsonObject(jsonObjectResult, "emp_number"));
	}

	@QAFTestStep(description = "user should get RR count")
	public void userShouldGetRRCount() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_RR_COUNT)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get priviledge list")
	public void userShouldGetPriviledgeList() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PRIVILEGES_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("sub_privileges").toString(), Matchers.notNullValue());
		}
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get employee goal hobbies")
	public void userShouldGetEmployeeGoalHobbies() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_GOALS_HOBBIES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_hobbies");
	}

	@QAFTestStep(description = "user should edit employee goals")
	public void userShouldEditEmployeeGoals() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_long_term_goals", "Learn automation on nest internal poject");
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should edit employee hobbies")
	public void userShouldEditEmployeeHobbies() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get country name")
	public void userShouldGetCountryName() {
		ClientUtils.getWebResource(ESSEndPoints.GET_COUNTRY_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("cou_code").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("cou_name").toString(), Matchers.notNullValue());
		}
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get emergenct contact details")
	public void userShouldGetEmergenctContactDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMEGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("eec_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("eec_relationship").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("eec_mobile_no").toString(), Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get family memebers")
	public void userShouldGetFamilyMemebers() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_FAMILY_MEMEBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("ed_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("ed_relationship_type").toString(),
					Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get employee relationship details")
	public void userShouldGetEmployeeRelationshipDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_RELATIONSSHIP_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();
		
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
						Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("ed_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("ed_relationship_type").toString(),
					Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get role title")
	public void userShouldGetRoleTitle() {
		ClientUtils.getWebResource(ESSEndPoints.GET_ROLE_TITLE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("job_title").toString(), Matchers.notNullValue());
		}
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get employee-location-privileges")
	public void userShouldGetEmployeeLocationPrivileges() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_LOCAION_PRIVILEDGES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("admin_loc").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("loc_id").toString(), Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get-Include")
	public void userShouldGetInclude() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_INCLUDE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "1");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "2");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "3");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should /get-Supervisors-Name")
	public void userShouldGetSupervisorsName() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_SUPERVISORS_NAME)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("ename").toString(), Matchers.notNullValue());
		}
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should /get-employee-status-for-teams-list")
	public void userShouldGetEmployeeStatusForTeamsList() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_STATUS_FOR_TEAMS_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Appointed");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"Initiated_Confirmation");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Confirmed");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Left");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Resigned");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Terminated");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should /get-teams-profile-list")
	public void userShouldGetTeamsProfileList() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_TEAMS_PROFILE_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("empNumber").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("employeeId").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("firstName").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("lastName").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should /get-location-for-new-emp")
	public void userShouldGetLocationForNewEmp() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_LOCATION_FOR_NEW_EMP)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("location_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("location_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get employee privileges")
	public void userShouldGetEmployeePrivileges() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EMPLOYEE_PRIVILEGES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("rolelist_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get contact details")
	public void userShouldGetContactDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "bank_name");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "account_no");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "pfno");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_oth_email");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get employee languages")
	public void userShouldGetEmployeeLanguages() {
		ClientUtils.getWebResource(ESSEndPoints.EMPLOYEE_LANGUAGES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("seq_lang_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("lang_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("fluency").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("competency").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("lname").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("fluency_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("competency_id").toString(), Matchers.notNullValue());
		}
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get professional experiance")
	public void userShouldGetProfessionalExperiance() {
		ClientUtils.getWebResource(ESSEndPoints.PROFESSIONAL_EXPERIANCE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("eexp_jobtit").toString(), Matchers.notNullValue());
		}
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit professional experiance")
	public void userShouldEditProfessionalExperiance() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("eexp_jobtit", essBean.getEexp_jobtit());
		jsonObject1.put("eexp_employer", essBean.getEexp_employer());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject1);
		jsonObject.put("profexp_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should delete professional experiance")
	public void userShouldDeleteProfessionalExperiance() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("profexp_details", jsonObject1);
		ClientUtils.getWebResource(ESSEndPoints.DELETE_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get education level")
	public void userShouldGetEducationLevel() {
		ClientUtils.getWebResource(ESSEndPoints.EDUCATION_LEVEL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get language name")
	public void userShouldGetLanguageName() {
		ClientUtils.getWebResource(ESSEndPoints.LANGUAGE_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get qualification details")
	public void userShouldGetQualificationDetails() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.QUALIFICATION_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("institute").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("year").toString(),
					Matchers.notNullValue());
		}
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get reporting structure")
	public void userShouldGetReportingStructure() {
		ClientUtils.getWebResource(ESSEndPoints.REPORTING_STRUCTURE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject().get("manager_reviews")
				.getAsJsonObject().get("Nest Test").getAsJsonObject();
		Validator.verifyThat(
				(jsonObjectResult.get("Reporting Manager").getAsJsonArray()).toString(),
				Matchers.notNullValue());
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		Validator.verifyThat(
				(jsonObjectResult.get("my_reportee").getAsJsonArray()).toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(jsonObjectResult.get("emp_project_pm_dm").getAsJsonObject()).toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user should get competency")
	public void userShouldGetCompetency() {
		ClientUtils.getWebResource(ESSEndPoints.COMPETENCY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get fluency")
	public void userShouldGetFluency() {
		ClientUtils.getWebResource(ESSEndPoints.FLUENCY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should bgv status")
	public void userShouldBgvStatus() {
		ClientUtils.getWebResource(ESSEndPoints.BGV_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Initiated");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Stop");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get marital status")
	public void userShouldGetMaritalStatus() {
		ClientUtils.getWebResource(ESSEndPoints.MARITAL_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Single");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Married");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get employee status")
	public void userShouldGetEmployeeStatus() {
		ClientUtils.getWebResource(ESSEndPoints.EMPLOYEE_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Appointed");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Terminated");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get relationship data")
	public void userShouldGetRelationshipData() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "employee_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "rel_emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "ename");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should /delete-Employee")
	public void userShouldDeleteEmployee() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		jsonObject.put("emp_details", jsonObject1);
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		jsonObject.put("emp_details", jsonObject1);
		ClientUtils.getWebResource(ESSEndPoints.DELETE_EMPLOYEE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user should edit relationship data")
	public void userShouldEditRelationshipData() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("rel_emp_number", essBean.getRel_emp_number());
		jsonObject1.put("rel_type", essBean.getRel_type());
		jsonObject1.put("emp_rel_id", essBean.getEmp_rel_id());
		jsonObject1.put("rel_employee_id", essBean.getRel_employee_id());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject1);
		jsonObject.put("rel_data", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should delete relationship data")
	public void userShouldDeleteRelationshipData() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("rel_data", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}
	/*
	 * Description : This request will create from HR manager view.
	 */
	@QAFTestStep(description = "user should edit basic details")
	public void userShouldEditBasicDetails() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number", essBean.getEmp_number());
		jsonObject1.put("emp_firstname", essBean.getEmp_firstname());
		jsonObject1.put("emp_lastname", essBean.getEmp_lastname());
		jsonObject1.put("employee_id", essBean.getEmployee_id());
		jsonObject.put("basic_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_BASIC_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit personal details")
	public void userShouldEditPersonalDetails() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("blood_group", essBean.getBlood_group());
		jsonObject1.put("gender", essBean.getGender());
		jsonObject.put("personal_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_PERSONAL_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit identity details")
	public void userShouldEditIdentityDetails() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("identity_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_IDENTITY_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit emergency contact details")
	public void userShouldEditEmergencyContactDetails() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("eec_seqno", essBean.getEec_seqno());
		jsonObject1.put("eec_name", essBean.getEec_name());
		jsonObject1.put("eec_relationship", essBean.getEec_relationship());
		jsonObject1.put("eec_mobile_no", essBean.getEec_mobile_no());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject1);
		jsonObject.put("ec_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMERGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should delete emergency contact details")
	public void userShouldDeleteEmergencyContactDetails() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("eec_seqno", essBean.getEec_seqno());
		jsonObject.put("ec_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_EMERGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit family members")
	public void userShouldEditFamilyMembers() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("ed_seqno", essBean.getEd_seqno());
		jsonObject1.put("ed_name", essBean.getEd_name());
		jsonObject1.put("ed_relationship_type", essBean.getEd_relationship_type());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject1);
		jsonObject.put("family_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_FAMILY_MEMBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should delete family members")
	public void userShouldDeleteFamilyMembers() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("ed_seqno", essBean.getEd_seqno());
		jsonObject.put("family_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_FAMILY_MEMBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get role description")
	public void userShouldGetRoleDescription() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_ROLE_DESCRIPTION)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "job_title");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_work_email");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should edit role description")
	public void userShouldEditRoleDescription() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number", essBean.getEmp_number());
		jsonObject1.put("job_title", essBean.getJob_title());
		jsonObject1.put("emp_work_email", essBean.getEmp_work_email());
		jsonObject.put("role_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_ROLE_DESCRIPTION)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should delete display pic")
	public void userShouldDeleteDisplayPic() {
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("emp_details", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_DISPLAY_PIC)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		Reporter.log(jsonObjectResult.toString());
	}

	@QAFTestStep(description = "user should get nationality")
	public void userShouldGetNationality() {
		ClientUtils.getWebResource(ESSEndPoints.GET_NATIONALITY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(jsonArrayResult.size(), Matchers.greaterThan(0));

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description="user should edit employee languages")
	public void userShouldEditEmployeeLanguages() {
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject1.put("seq_lang_id", essBean.getSeq_lang_id());
		jsonObject1.put("lang_id", essBean.getLang_id());
		jsonObject1.put("fluency_id", essBean.getFluency_id());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject1);
		jsonObject.put("lang_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_LANGUAGES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
		Reporter.log(jsonObjectResult.toString());
	}
}
