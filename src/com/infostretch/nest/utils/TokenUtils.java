package com.infostretch.nest.utils;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.testng.Reporter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.EndPoints;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class TokenUtils {

	static UserType currentUser = null;

	public static void setCurrentUser(UserType user) {
		currentUser = user;
	}

	public static String getTokenAsJsonStr() {
		return String.format("{\"token\":\"%s\"}", currentUser.getToken());
	}

	public enum UserType {
		NormalUser("normal.user"), ManagerUser("manager.user");

		private final String key;
		private String token;

		UserType(final String text) {
			this.key = text;
		}

		@Override
		public String toString() {
			return key;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getToken() {
			return this.token;
		}

		public String doLogin() {
			if (null == this.token) {
				String userDetails = ConfigurationManager.getBundle().getString(this.toString());
				ClientUtils.getWebResource(EndPoints.LOGIN).type(MediaType.APPLICATION_JSON).entity(userDetails).post();
				Response response = ClientUtils.getResponse();
				Reporter.log(response.getMessageBody());
				Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
				JsonObject responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
				JsonObject results = responseBody.get("response").getAsJsonObject().get("results").getAsJsonObject();
				Validator.verifyThat(results, Matchers.notNullValue());
				this.token = results.get("token").getAsString();
				Validator.verifyThat(this.token, Matchers.notNullValue());
			}
			setCurrentUser(this);
			return this.token;
		}

	}

}
