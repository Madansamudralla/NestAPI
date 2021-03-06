package com.infostretch.nest.steps;

import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.step.NotYetImplementedException;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestLandingSteps {

	@QAFTestStep(description = "user login as manager")
	public static String loginAsManagerUser() {

		
		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user login as normal employee")
	public static String loginAsNormalUser() {

		return UserType.NormalUser.doLogin();
	}

	@QAFTestStep(description = "User login as hr")
	public static String loginAsHr() {

		return UserType.HrUser.doLogin();
	}
	@QAFTestStep(description = "user login as visa user")
	public String userLoginAsVisaUser() {
		
		return UserType.VisaUser.doLogin();
	}	
}
