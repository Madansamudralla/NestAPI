package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.ESSBean;
import com.infostretch.nest.providers.EssEndPoint;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class Ess {
	JSONObject jsonObject, jsonObject2;
	JsonObject result, responseBody;
	JSONArray jsonArray;
	JsonArray results, object1Result;
	int index;
	Response response;
	ESSBean essBean;

	@QAFTestStep(description = "user should get employee privileges")
	public void userShouldGetEmployeePrivileges() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_PRIVILEGES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		JsonArray object1Result = result.get("details").getAsJsonArray();

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
		ClientUtils.getWebResource(EssEndPoint.CONTACT_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_LANGUAGES)
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
		ClientUtils.getWebResource(EssEndPoint.PROFESSIONAL_EXPERIANCE)
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
		ClientUtils.getWebResource(EssEndPoint.EDIT_PROFESSIONAL_EXPERIANCE)
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
		ClientUtils.getWebResource(EssEndPoint.DELETE_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get education level")
	public void userShouldGetEducationLevel() {
		ClientUtils.getWebResource(EssEndPoint.EDUCATION_LEVEL)
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
		ClientUtils.getWebResource(EssEndPoint.LANGUAGE_NAME)
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
		ClientUtils.getWebResource(EssEndPoint.QUALIFICATION_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.REPORTING_STRUCTURE)
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
		ClientUtils.getWebResource(EssEndPoint.COMPETENCY)
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
		ClientUtils.getWebResource(EssEndPoint.FLUENCY)
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
		ClientUtils.getWebResource(EssEndPoint.BGV_STATUS)
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
		ClientUtils.getWebResource(EssEndPoint.MARITAL_STATUS)
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
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_STATUS)
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
		ClientUtils.getWebResource(EssEndPoint.RELATIONSHIP_DATA)
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
		ClientUtils.getWebResource(EssEndPoint.EDIT_RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should delete relationship data")
	public void userShouldDeleteRelationshipData() {
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("rel_data", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(EssEndPoint.DELETE_RELATIONSHIP_DATA)
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
		jsonObject2.put("emp_lastname",essBean.getEmp_lastname());
		jsonObject2.put("employee_id",essBean.getEmployee_id());
		jsonObject.put("basic_details", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(EssEndPoint.EDIT_BASIC_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.EDIT_PERSONAL_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.EDIT_IDENTITY_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.EDIT_EMERGENCY_CONTACT_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.DELETE_EMERGENCY_CONTACT_DETAILS)
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
		ClientUtils.getWebResource(EssEndPoint.EDIT_FAMILY_MEMBERS)
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
		ClientUtils.getWebResource(EssEndPoint.DELETE_FAMILY_MEMBERS)
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
		ClientUtils.getWebResource(EssEndPoint.GET_ROLE_DESCRIPTION)
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
	ClientUtils.getWebResource(EssEndPoint.EDIT_ROLE_DESCRIPTION)
			.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
	response = ClientUtils.getResponse();
	result = CommonUtils.getValidateResultObject(response);
	CommonUtils.validateParameterInJsonObject(result, "action_message");
	Reporter.log(result.toString());
	}
}
