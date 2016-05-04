package com.example.myapp.model;

public class Password {
	
	private static String passwordEdit;
	
	private static String passwordProblem;
	
	private static String passwordAnswer;
	
	public static boolean hasPassword = false;
	
	public static void setPasswordEdit(String mpasswordEdit) {
		passwordEdit = mpasswordEdit;
	}
	
	public static void setPasswordProblem(String mpasswordProblem) {
		passwordProblem = mpasswordProblem;
	}
	
	public static void setPasswordAnswer(String mpasswordAnswer) {
		passwordAnswer = mpasswordAnswer;
	}
	
	public static String getPasswordEdit() {
		return passwordEdit;
	}
	
	public static String getPasswordProblem() {
		return passwordProblem;
	}
	
	public static String getPasswordAnswer() {
		return passwordAnswer;
	}

}
