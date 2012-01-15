package de.robotniko;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;

public class MyTestHelper {

	public static void initLogging() {
		ConsoleAppender ca = new ConsoleAppender();
		ca.setName("Console");
		ca.setTarget("System.out");
		ca.setLayout(new PatternLayout("%d{dd.MM.yyyy HH:mm:ss}|%p|%C{1}|%m%n"));
		ca.activateOptions();
		ca.setThreshold(Level.DEBUG);
		LogManager.getRootLogger().addAppender(ca);
	}

}
