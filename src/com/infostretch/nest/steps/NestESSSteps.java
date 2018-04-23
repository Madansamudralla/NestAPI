package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

	@QAFTestStep(description = "user should get-basic-details")
	public void userShouldGetBasicDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",ConfigurationManager.getBundle().getPropertyValue("emp_id") );
		
		ClientUtils.getWebResource(ESSEndPoints.GET_BASIC_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());

	}

	@QAFTestStep(description = "user should get-personal-details")
	public void userShouldGetPersonalDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PERSONAL_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
	}

	@QAFTestStep(description = "user should get-base-location")
	public void userShouldGetBaseLocation() {
		ClientUtils.getWebResource(ESSEndPoints.GET_BASE_LOCATION)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));

	}

	@QAFTestStep(description = "user should get idenity details")
	public void userShouldGetIdenityDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_IDENTITY_DATAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("Emp Number : "
				+ CommonUtils.validateParameterInJsonObject(result, "emp_number"));

	}

	@QAFTestStep(description = "user should get RR count")
	public void userShouldGetRRCount() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
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
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PRIVILEGES_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());

	}

	@QAFTestStep(description = "user should get employee goal hobbies")
	public void userShouldGetEmployeeGoalHobbies() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_GOALS_HOBBIES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("result:" +result);
		Reporter.log("Emp Number : "
				+ CommonUtils.getValidateResultObject(response));

	}

	@QAFTestStep(description = "user should edit employee goals")
	public void userShouldEditEmployeeGoals()
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_long_term_goals","Learn automation on nest internal poject" );
		
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("Emp Number : "
				+ CommonUtils.getValidateResultObject(response));

}

	@QAFTestStep(description = "user should edit employee hobbies")
	public void userShouldEditEmployeeHobbies() 
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("Emp Number : "
				+ CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get country name")
	public void userShouldGetCountryName() 
	{
				ClientUtils.getWebResource(ESSEndPoints.GET_COUNTRY_NAME)
					.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
					.post();
			Response response = ClientUtils.getResponse();
			JsonArray results = CommonUtils.getValidatedResultArray(response);
			Validator.verifyThat(results.size(), Matchers.greaterThan(0));
		

	}

	@QAFTestStep(description = "user should get emergenct contact details")
	public void userShouldGetEmergenctContactDetails()
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMEGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("Emp Number : "
				+ CommonUtils.getValidateResultObject(response));
		
	}

	@QAFTestStep(description = "user should get family memebers")
	public void userShouldGetFamilyMemebers()
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_FAMILY_MEMEBERS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		System.out.println(result.toString());

	}

	@QAFTestStep(description = "user should get employee relationship details")
	public void userShouldGetEmployeeRelationshipDetails() 
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_RELATIONSSHIP_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		System.out.println(result.toString());
	}

	@QAFTestStep(description = "user should get role title")
	public void userShouldGetRoleTitle()
	{
		ClientUtils.getWebResource(ESSEndPoints.GET_ROLE_TITLE)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
Response response = ClientUtils.getResponse();
JsonArray results = CommonUtils.getValidatedResultArray(response);
Validator.verifyThat(results.size(), Matchers.greaterThan(0));

	}

	@QAFTestStep(description = "user should get employee-location-privileges")
	public void userShouldGetEmployeeLocationPrivileges()
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_LOCAION_PRIVILEDGES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("Emp Number : "
				+ CommonUtils.getValidateResultObject(response));
		
	}

	@QAFTestStep(description = "user should save-location-privileges")
	public void userShouldSaveLocationPrivileges()
	{
		
	}

	@QAFTestStep(description = "user should save-privileges")
	public void userShouldSavePrivileges()
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		obj.put("privileges", "187");
		ClientUtils.getWebResource(ESSEndPoints.SAVE_PRIVILEDGES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log("Emp Number : "
				+ CommonUtils.getValidateResultObject(response));
		
	}

}
