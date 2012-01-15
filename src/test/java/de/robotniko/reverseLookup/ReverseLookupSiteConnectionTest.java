package de.robotniko.reverseLookup;

import java.io.IOException;
import java.net.URLConnection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.structs.ReverseLookupEntry;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupSiteConnectionTest {

	private ReverseLookupSiteConnection rlsc;

	@BeforeClass
	public static void setup() {
		MyTestHelper.initLogging();
	}

	@Before
	public void init() {
		 rlsc = new ReverseLookupSiteConnection();
	}

	@Test
	public void test() throws IOException {
		ReverseLookupSite lookupSite = new ReverseLookupSite();
		lookupSite.setName("www.dasoertliche.de");
		lookupSite.setUrl("http://dasoertliche.de/Controller?form_name=search_inv&ph=$NUMBER");
		lookupSite.setPrefix("0");

		ReverseLookupEntry entry = new ReverseLookupEntry();
		entry.setFirstOccurance("zipcode");
		entry.setNamePattern("\\sna: \"([^\"]*)\",");
		entry.setStreetPattern("\\sst: \"([^\"]*)\",");
		entry.setCityPattern("\\sci: \"([^\"]*)\",");
		entry.setZipPattern("\\spc: \"([^\"]*)\",");

		lookupSite.addEntry(entry);

		URLConnection connection = rlsc.connect(lookupSite, "072130806");
		connection.getConnectTimeout();
		Assert.assertEquals("ISO-8859-15", rlsc.getCharSet());
	}
}
