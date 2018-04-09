package com.infostretch.nest.steps;

import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestLandingSteps {

	@QAFTestStep(description = "I login as manager")
	public static String loginAsManagerUser() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "I login as normal employee")
	public static String loginAsNormalUser() {

		return UserType.NormalUser.doLogin();
	}

}
