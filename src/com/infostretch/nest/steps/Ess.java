package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.infostretch.nest.providers.EssEndPoint;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
public class Ess {
	
	@QAFTestStep(description = "User should get employee privileges")
	public void userShouldGetEmployeePrivileges() {
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_PRIVILEGES).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "User should get contact details")
	public void userShouldGetContactDetails() {
		
		JSONObject jsonObject =new JSONObject();
		jsonObject.put(" emp_number","113");
		jsonObject.put("token","5813e4557d75dce6f5c9e616fb9010b6");
		ClientUtils.getWebResource(EssEndPoint.CONTACT_DETAILS)
		.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
	}
	
	@QAFTestStep(description = "User should get employee languages")
	public void userShouldGetEmployeeLanguages() {
		
		ClientUtils.getWebResource(EssEndPoint.EMPLOYEE_LANGUAGES).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "User should get professional experiance")
	public void userShouldGetProfessionalExperiance() {
		ClientUtils.getWebResource(EssEndPoint.PROFESSIONAL_EXPERIANCE).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));	
	}
	
	@QAFTestStep(description = "User should get education level")
	public void userShouldGetEducationLevel() {
		ClientUtils.getWebResource(EssEndPoint.EDUCATION_LEVEL).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "User should get language name")
	public void userShouldGetLanguageName() {
		ClientUtils.getWebResource(EssEndPoint.LANGUAGE_NAME).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		}
	
	@QAFTestStep(description = "User should get qualification details")
	public void userShouldGetQualificationDetails() {
		ClientUtils.getWebResource(EssEndPoint.QUALIFICATION_DETAILS).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
			
		}
}
