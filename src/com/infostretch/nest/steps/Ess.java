package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.providers.EssEndPoint;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class Ess {
	JSONObject jsonObject;
	int index;

	@QAFTestStep(description = "user should get employee privileges")
	public void userShouldGetEmployeePrivileges() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_PRIVILEGES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1reaults = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1reaults.size() - 1; index++) {
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("rolelist_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1reaults.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get contact details")
	public void userShouldGetContactDetails() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(EssEndPoint.CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get employee languages")
	public void userShouldGetEmployeeLanguages() {
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_LANGUAGES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1reaults = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1reaults.size() - 1; index++) {
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("seq_lang_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("lang_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("fluency").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("competency").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1reaults.get(index).getAsJsonObject()).get("lname").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("fluency_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("competency_id").toString(), Matchers.notNullValue());
		}
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get professional experiance")
	public void userShouldGetProfessionalExperiance() {
		ClientUtils.getWebResource(EssEndPoint.PROFESSIONAL_EXPERIANCE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1reaults = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1reaults.size() - 1; index++) {
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("eexp_jobtit").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("eexp_from_date").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("eexp_to_date").toString(), Matchers.notNullValue());
		}
		Reporter.log(result.toString());

	}

	@QAFTestStep(description = "user should edit professional experiance")
	public void userShouldEditProfessionalExperiance() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("eexp_jobtit", "dcdfkkkk");
		jsonObject.put("eexp_employer", "kddfdj");
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));

		ClientUtils.getWebResource(EssEndPoint.EDIT_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should delete professional experiance")
	public void userShouldDeleteProfessionalExperiance() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(EssEndPoint.DELETE_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get education level")
	public void userShouldGetEducationLevel() {
		ClientUtils.getWebResource(EssEndPoint.EDUCATION_LEVEL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
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
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
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
		ClientUtils.getWebResource(EssEndPoint.QUALIFICATION_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get reporting structure")
	public void userShouldGetReportingStructure() {
		ClientUtils.getWebResource(EssEndPoint.REPORTING_STRUCTURE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get competency")
	public void userShouldGetCompetency() {
		ClientUtils.getWebResource(EssEndPoint.COMPETENCY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
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
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
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
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Initiated");
		CommonUtils.validateParameterInJsonObject(result, "Stop");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get marital status")
	public void userShouldGetMaritalStatus() {
		ClientUtils.getWebResource(EssEndPoint.MARITAL_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Single");
		CommonUtils.validateParameterInJsonObject(result, "Married");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get employee status")
	public void userShouldGetEmployeeStatus() {
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_STATUS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Appointed");
		CommonUtils.validateParameterInJsonObject(result, "Terminated");
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get relationship data")
	public void userShouldGetRelationshipData() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number", "1150");
		ClientUtils.getWebResource(EssEndPoint.RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should edit relationship data")
	public void userShouldEditRelationshipData() {
		ClientUtils.getWebResource(EssEndPoint.EDIT_RELATIONSHIP_DATA)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should delete relationship data")
	public void userShouldDeleteRelationshipData() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		ClientUtils.getWebResource(EssEndPoint.DELETE_RELATIONSHIP_DATA)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		Reporter.log(result.toString());
	}

}
