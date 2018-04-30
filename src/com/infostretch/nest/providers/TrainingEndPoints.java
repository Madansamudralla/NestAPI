package com.infostretch.nest.providers;

public interface TrainingEndPoints {
	public final static String Training_Calendar = "/get-training-calendar";
	public final static String Training_Detail = "/view-training-detail";
	public static final String I_AM_Attending = "/get-i-am-attending";
	public static final String I_HAVE_ATTENDED = "/get-i-have-attended";
	public static final String I_HAVE_TAUGHT = "/get-i-have-taught";
	public static final String GET_I_AM_TEACHING = "/get-i-am-teaching";
	public static final String GET_FEEDBACK_QUESTIONS_LIST =
			"/get-feedback-questions-list";

	public static final String GET_COURSE_LIST = "/get-course-list";
	public static final String GET_STATISTICS = "/get-statistics";
	public static final String GET_TRAINING_TYPE_DD = "/get-training-type-dd";
	public static final String GET_INDIVIDUAL_EMP_STATISTICS =
			"/get-individual-emp-statistics";
	public static final String GET_VENUE_LIST = "/get-venue-list";
	public static final String ADD_EDIT_VENUE = "/add-edit-venue";

}
