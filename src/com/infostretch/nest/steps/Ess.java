package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.EssEndPoint;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class Ess {

	@QAFTestStep(description = "user should get employee privileges")
	public void userShouldGetEmployeePrivileges() {
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_PRIVILEGES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		if (CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get contact details")
	public void userShouldGetContactDetails() {

		// JSONObject jsonObject =new JSONObject();
		// jsonObject.put("token",TokenUtils.getTokenAsStr());
		// jsonObject.put("
		// emp_number","113");//ConfigurationManager.getBundle().getPropertyValue("emp_id");
		ClientUtils.getWebResource(EssEndPoint.CONTACT_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		if (CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get employee languages")
	public void userShouldGetEmployeeLanguages() {

		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_LANGUAGES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		if (CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get professional experiance")
	public void userShouldGetProfessionalExperiance() {
		ClientUtils.getWebResource(EssEndPoint.PROFESSIONAL_EXPERIANCE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		if (CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should edit professional experiance")
	public void userShouldEditProfessionalExperiance() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("eexp_jobtit", "dcdfkkkk");
		jsonObject.put("eexp_employer", "kddfdj");
		jsonObject.put("eexp_seqno", "1");
		jsonObject.put("emp_number", "113");// ConfigurationManager.getBundle().getPropertyValue("emp_id");

		ClientUtils.getWebResource(EssEndPoint.EDIT_PROFESSIONAL_EXPERIANCE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get education level")
	public void userShouldGetEducationLevel() {
		ClientUtils.getWebResource(EssEndPoint.EDUCATION_LEVEL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get language name")
	public void userShouldGetLanguageName() {
		ClientUtils.getWebResource(EssEndPoint.LANGUAGE_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get qualification details")
	public void userShouldGetQualificationDetails() {
		ClientUtils.getWebResource(EssEndPoint.QUALIFICATION_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		if (CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get reporting structure")
	public void userShouldGetReportingStructure() {
		ClientUtils.getWebResource(EssEndPoint.REPORTING_STRUCTURE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		if (CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get competency")
	public void userShouldGetCompetency() {
		ClientUtils.getWebResource(EssEndPoint.COMPETENCY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get fluency")
	public void userShouldGetFluency() {
		ClientUtils.getWebResource(EssEndPoint.FLUENCY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonArray results =
				responseBody.get("response").getAsJsonObject().getAsJsonArray("results");
		Validator.verifyThat(results.size(), Matchers.equalTo(3));
		Validator.verifyThat((results.get(0).getAsJsonObject()).get("name").toString(),
				Matchers.containsString("Writing"));
		Validator.verifyThat((results.get(1).getAsJsonObject()).get("name").toString(),
				Matchers.containsString("Speaking"));
		Validator.verifyThat((results.get(2).getAsJsonObject()).get("name").toString(),
				Matchers.containsString("Reading"));

	}
}
