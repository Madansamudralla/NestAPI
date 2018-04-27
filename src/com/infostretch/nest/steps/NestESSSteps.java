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
import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.NotYetImplementedException;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestESSSteps {
	JSONObject jsonObject, jsonObject2;
	JsonObject result, responseBody;
	JSONArray jsonArray;
	JsonArray results, object1Result;
	int index;
<<<<<<< HEAD
	JSONObject obj;
	JsonObject result;
	JsonArray param1results;
=======
	Response response;
	ESSBean essBean;
>>>>>>> 5c6ea8c20d3e9d3333aff02b2767ac4efce9ac32
	@QAFTestStep(description = "user should get-basic-details")
	public void userShouldGetBasicDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));

		ClientUtils.getWebResource(ESSEndPoints.GET_BASIC_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_firstname");
		CommonUtils.validateParameterInJsonObject(result, "ename");

		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get-personal-details")
	public void userShouldGetPersonalDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PERSONAL_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "emp_gender");


		
	}

	@QAFTestStep(description = "user should get-base-location")
	public void userShouldGetBaseLocation() {
		ClientUtils.getWebResource(ESSEndPoints.GET_BASE_LOCATION)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("city").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("country_code").toString(),
					Matchers.notNullValue());

		}

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get idenity details")
	public void userShouldGetIdenityDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_IDENTITY_DATAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_dri_lice_num");
		Reporter.log("Emp Number : "
				+ CommonUtils.validateParameterInJsonObject(result, "emp_number"));
	}

	@QAFTestStep(description = "user should get RR count")
	public void userShouldGetRRCount() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_RR_COUNT)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);

		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get priviledge list")
	public void userShouldGetPriviledgeList() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PRIVILEGES_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("sub_privileges").toString(), Matchers.notNullValue());
		}
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get employee goal hobbies")
	public void userShouldGetEmployeeGoalHobbies() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_GOALS_HOBBIES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "emp_hobbies");
	}

	@QAFTestStep(description = "user should edit employee goals")
	public void userShouldEditEmployeeGoals() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_long_term_goals", "Learn automation on nest internal poject");

		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "response_type");

		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should edit employee hobbies")
	public void userShouldEditEmployeeHobbies() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get country name")
	public void userShouldGetCountryName() {
		ClientUtils.getWebResource(ESSEndPoints.GET_COUNTRY_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("cou_code").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("cou_name").toString(),
					Matchers.notNullValue());
		}

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get emergenct contact details")
	public void userShouldGetEmergenctContactDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMEGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("eec_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("eec_relationship").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("eec_mobile_no").toString(), Matchers.notNullValue());

		}

		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get family memebers")
	public void userShouldGetFamilyMemebers() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_FAMILY_MEMEBERS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("ed_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject())
							.get("ed_relationship_type").toString(),
					Matchers.notNullValue());

		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get employee relationship details")
	public void userShouldGetEmployeeRelationshipDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_RELATIONSSHIP_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("ed_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject())
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
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("job_title").toString(),
					Matchers.notNullValue());

		}

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get employee-location-privileges")
	public void userShouldGetEmployeeLocationPrivileges() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_LOCAION_PRIVILEDGES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("admin_loc").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("loc_id").toString(),
					Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	
	
	@QAFTestStep(description = "user should get-Include")
	public void userShouldGetInclude() {
		obj = new JSONObject();
		obj.put("token", "db453c12423112f3ffb4a2b473139ed6");
		System.out.println("#########"+obj.toString());

		ClientUtils.getWebResource(ESSEndPoints.GET_INCLUDE)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "1");
		CommonUtils.validateParameterInJsonObject(result, "2");
		CommonUtils.validateParameterInJsonObject(result, "3");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
}

	@QAFTestStep(description = "user should /get-Supervisors-Name")
	public void userShouldGetSupervisorsName() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_SUPERVISORS_NAME)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
		

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}
	
	

	@QAFTestStep(description = "user should /get-employee-status-for-teams-list")
	public void userShouldGetEmployeeStatusForTeamsList() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_STATUS_FOR_TEAMS_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Appointed");
		CommonUtils.validateParameterInJsonObject(result, "Initiated_Confirmation");
		CommonUtils.validateParameterInJsonObject(result, "Confirmed");
		CommonUtils.validateParameterInJsonObject(result, "Left");
		CommonUtils.validateParameterInJsonObject(result, "Resigned");
		CommonUtils.validateParameterInJsonObject(result, "Terminated");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
		
	
	}

	@QAFTestStep(description = "user should /get-teams-profile-list")
	public void userShouldGetTeamsProfileList() 
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_TEAMS_PROFILE_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("empNumber").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("employeeId").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("firstName").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("lastName").toString(),
					Matchers.notNullValue());
		}
	}
	

	@QAFTestStep(description = "user should /add-new-employee")
	public void userShouldAddNewEmployee() {
	
	}

	@QAFTestStep(description = "user should /get-location-for-new-emp")
	public void userShouldGetLocationForNewEmp() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_LOCATION_FOR_NEW_EMP)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("location_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("location_name").toString(),
					Matchers.notNullValue());

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
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("rolelist_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
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
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "bank_name");
		CommonUtils.validateParameterInJsonObject(result, "account_no");
		CommonUtils.validateParameterInJsonObject(result, "pfno");
		CommonUtils.validateParameterInJsonObject(result, "emp_oth_email");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get employee languages")
	public void userShouldGetEmployeeLanguages() {
		ClientUtils.getWebResource(ESSEndPoints.EMPLOYEE_LANGUAGES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("seq_lang_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("lang_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("fluency").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("competency").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("lname").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("fluency_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("competency_id").toString(), Matchers.notNullValue());
		}

		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get professional experiance")
	public void userShouldGetProfessionalExperiance() {
		ClientUtils.getWebResource(ESSEndPoints.PROFESSIONAL_EXPERIANCE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("eexp_jobtit").toString(), Matchers.notNullValue());
		}

		Reporter.log(result.toString());

	}

	@QAFTestStep(description = "user should edit professional experiance")
	public void userShouldEditProfessionalExperiance() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("eexp_jobtit", essBean.getEexp_jobtit());
		jsonObject2.put("eexp_employer", essBean.getEexp_employer());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject2);
		jsonObject.put("profexp_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should delete professional experiance")
	public void userShouldDeleteProfessionalExperiance() {
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("profexp_details", jsonObject2);
		ClientUtils.getWebResource(ESSEndPoints.DELETE_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get education level")
	public void userShouldGetEducationLevel() {
		ClientUtils.getWebResource(ESSEndPoints.EDUCATION_LEVEL)
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
		}
	}

	@QAFTestStep(description = "user should get language name")
	public void userShouldGetLanguageName() {
		ClientUtils.getWebResource(ESSEndPoints.LANGUAGE_NAME)
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
		result = CommonUtils.getValidateResultObject(response);
		object1Result = result.get("details").getAsJsonArray();

		for (index = 0; index <= object1Result.size() - 1; index++) {
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((object1Result.get(index).getAsJsonObject())
					.get("institute").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(object1Result.get(index).getAsJsonObject()).get("year").toString(),
					Matchers.notNullValue());
		}
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get reporting structure")
	public void userShouldGetReportingStructure() {
		ClientUtils.getWebResource(ESSEndPoints.REPORTING_STRUCTURE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject().get("manager_reviews")
				.getAsJsonObject().get("Nest Test").getAsJsonObject();
		Validator.verifyThat(
				(result.get("Reporting Manager").getAsJsonArray()).toString(),
				Matchers.notNullValue());
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		Validator.verifyThat((result.get("my_reportee").getAsJsonArray()).toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(result.get("emp_project_pm_dm").getAsJsonObject()).toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user should get competency")
	public void userShouldGetCompetency() {
		ClientUtils.getWebResource(ESSEndPoints.COMPETENCY)
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
		}
	}
	
	@QAFTestStep(description = "user should get fluency")
	public void userShouldGetFluency() {
		ClientUtils.getWebResource(ESSEndPoints.FLUENCY)
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
		}
	}
	
	@QAFTestStep(description = "user should bgv status")
	public void userShouldBgvStatus() {
		ClientUtils.getWebResource(ESSEndPoints.BGV_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Initiated");
		CommonUtils.validateParameterInJsonObject(result, "Stop");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get marital status")
	public void userShouldGetMaritalStatus() {
		ClientUtils.getWebResource(ESSEndPoints.MARITAL_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Single");
		CommonUtils.validateParameterInJsonObject(result, "Married");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get employee status")
	public void userShouldGetEmployeeStatus() {
		ClientUtils.getWebResource(ESSEndPoints.EMPLOYEE_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Appointed");
		CommonUtils.validateParameterInJsonObject(result, "Terminated");
		Reporter.log(result.toString());
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
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "employee_id");
		CommonUtils.validateParameterInJsonObject(result, "rel_emp_number");
		CommonUtils.validateParameterInJsonObject(result, "ename");
		Reporter.log(result.toString());
	}

<<<<<<< HEAD
	@QAFTestStep(description = "user should /delete-Employee")
	public void userShouldDeleteEmployee() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
	JSONObject	obj1 =new JSONObject();
	
		obj1.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
	obj.put("emp_details", obj1);
	
		ClientUtils.getWebResource(ESSEndPoints.DELETE_EMPLOYEE)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		 result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");

=======
	@QAFTestStep(description = "user should edit relationship data")
	public void userShouldEditRelationshipData() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("rel_emp_number", essBean.getRel_emp_number());
		jsonObject2.put("rel_type", essBean.getRel_type());
		jsonObject2.put("emp_rel_id", essBean.getEmp_rel_id());
		jsonObject2.put("rel_employee_id", essBean.getRel_employee_id());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject2);
		jsonObject.put("rel_data", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log(result.toString());
	}
>>>>>>> 5c6ea8c20d3e9d3333aff02b2767ac4efce9ac32

	@QAFTestStep(description = "user should delete relationship data")
	public void userShouldDeleteRelationshipData() {
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("rel_data", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}
	/*
	 * Description : This request will create from HR manager view.
	 */
	@QAFTestStep(description = "user should edit basic details")
	public void userShouldEditBasicDetails() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number", essBean.getEmp_number());
		jsonObject2.put("emp_firstname", essBean.getEmp_firstname());
		jsonObject2.put("emp_lastname", essBean.getEmp_lastname());
		jsonObject2.put("employee_id", essBean.getEmployee_id());
		jsonObject.put("basic_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_BASIC_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}
	@QAFTestStep(description = "user should edit personal details")
	public void userShouldEditPersonalDetails() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("blood_group", essBean.getBlood_group());
		jsonObject2.put("gender", essBean.getGender());
		jsonObject.put("personal_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_PERSONAL_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log(result.toString());

	}

	@QAFTestStep(description = "user should edit identity details")
	public void userShouldEditIdentityDetails() {
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("identity_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_IDENTITY_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should edit emergency contact details")
	public void userShouldEditEmergencyContactDetails() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("eec_seqno", essBean.getEec_seqno());
		jsonObject2.put("eec_name", essBean.getEec_name());
		jsonObject2.put("eec_relationship", essBean.getEec_relationship());
		jsonObject2.put("eec_mobile_no", essBean.getEec_mobile_no());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject2);
		jsonObject.put("ec_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMERGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should delete emergency contact details")
	public void userShouldDeleteEmergencyContactDetails() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("eec_seqno", essBean.getEec_seqno());
		jsonObject.put("ec_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_EMERGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should edit family members")
	public void userShouldEditFamilyMembers() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("ed_name", essBean.getEd_name());
		jsonObject2.put("ed_relationship_type", essBean.getEd_relationship_type());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject2);
		jsonObject.put("family_details", jsonArray);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_FAMILY_MEMBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should delete family members")
	public void userShouldDeleteFamilyMembers() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject2.put("ed_seqno", essBean.getEd_seqno());
		jsonObject.put("family_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_FAMILY_MEMBERS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
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
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "job_title");
		CommonUtils.validateParameterInJsonObject(result, "emp_work_email");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should edit role description")
	public void userShouldEditRoleDescription() {
		essBean = new ESSBean();
		essBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number", essBean.getEmp_number());
		jsonObject2.put("job_title", essBean.getJob_title());
		jsonObject2.put("emp_work_email", essBean.getEmp_work_email());
		jsonObject.put("role_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.EDIT_ROLE_DESCRIPTION)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should delete display pic")
	public void userShouldDeleteDisplayPic() {
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("emp_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.DELETE_DISPLAY_PIC)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get nationality")
	public void userShouldGetNationality() {
		ClientUtils.getWebResource(ESSEndPoints.GET_NATIONALITY)
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
		}
	}
	


}
