package org.jfritz.reverseLookup;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.jfritz.reverseLookup.api.ReverseLookupRequest;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.jfritz.reverseLookup.xml.ReverseLookupXMLHandler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.jfritz.MyTestHelper;
import org.jfritz.reverseLookup.api.ReverseLookupResponse;

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
