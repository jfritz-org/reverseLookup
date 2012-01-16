package de.robotniko.reverseLookup;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.xml.ReverseLookupXMLHandler;

public class ReverseLookupServiceTest {

	@BeforeClass
	public static void setup() {
		MyTestHelper.initLogging();
	}

	@Test
	public void testBlockingLookup() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+497211330");

		ReverseLookupService service = new ReverseLookupService();
		service.loadConfig(ReverseLookupXMLHandler.class.getResourceAsStream("/testXMLHandler"));
		List<ReverseLookupResponse> result = service.blockingLookup(request);
		Assert.assertEquals(0, result.size());
	}
}
