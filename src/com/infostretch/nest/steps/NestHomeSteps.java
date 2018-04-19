package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;

import com.infostretch.nest.providers.EndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestHomeSteps {

	@QAFTestStep(description = "user should get accessible menu list")
	public void verifyAccessibleMenuList() {
		ClientUtils.getWebResource(EndPoints.ACCESSIBLE_MENU_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		// JsonArray array = new
		// JsonParser().parse(getResponse.getResponse().getMessageBody()).getAsJsonArray();
		// Validator.verifyThat(array.size(),
		// Matchers.equalTo(expecteditems.size()));
		// for (int i = 0; i < array.size(); ++i) {
		// JsonObject obj = array.get(i).getAsJsonObject();
		// String actualId = obj.get("itemId").getAsString();
		// int actualQuantity = obj.get("quantity").getAsInt();
		// Validator.assertThat(expecteditems, Matchers.hasEntry(actualId,
		// actualQuantity));
		// }
	}
}
