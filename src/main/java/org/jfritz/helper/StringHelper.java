package org.jfritz.helper;


public class StringHelper {

	public static String replaceSpecialCharsUTF(final String str) {
		String currentStr = str;
		currentStr = currentStr.replaceAll("&#x[00]*A0;", " ");

		currentStr = currentStr.replaceAll("&#x[00]*C4;", "Ä");
		currentStr = currentStr.replaceAll("&#x[00]*D6;", "Ö");
		currentStr = currentStr.replaceAll("&#x[00]*DC;", "Ü");

		currentStr = currentStr.replaceAll("&#x[00]*E4;", "ä");
		currentStr = currentStr.replaceAll("&#x[00]*F6;", "ö");
		currentStr = currentStr.replaceAll("&#x[00]*FC;", "ü");
		currentStr = currentStr.replaceAll("&#x[00]*DF;", "ß");

		return currentStr;
	}

}
