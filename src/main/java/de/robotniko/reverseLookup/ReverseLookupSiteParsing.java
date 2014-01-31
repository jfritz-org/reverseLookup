package de.robotniko.reverseLookup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.structs.ParseItem;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupSiteParsing {
	private final static Logger LOG = Logger.getLogger(ReverseLookupSiteParsing.class);

	private ReverseLookupSite lookupSite;
	private List<String> lines;

	private List<ReverseLookupResponse> responseList = new ArrayList<ReverseLookupResponse>();

	public ReverseLookupSiteParsing(final ReverseLookupSite lookupSite, final List<String> lines) {
		this.lookupSite = lookupSite;
		this.lines = lines;
	}

	public void parse() {
		LOG.debug("Start parsing");
		
		responseList.clear();
		int numLinesAtOnce = lookupSite.getNumLines();
		if (numLinesAtOnce == 0) {
			numLinesAtOnce = 1;
		}

		List<ReverseLookupEntryParsing> entryList = new ArrayList<ReverseLookupEntryParsing>();
		for (int i=0; i<lookupSite.getNumEntries(); i++) {
			entryList.add(new ReverseLookupEntryParsing(lookupSite.getEntry(i)));
		}

		String currentLineToMatch;
		LOG.debug("Received " + lines.size() + " lines");
		for (int line=0; line<lines.size(); line++) {
			if (numLinesAtOnce == 1) {
				currentLineToMatch = lines.get(line);
			} else {
				currentLineToMatch="";
				for (int lineIt=0; lineIt<numLinesAtOnce; lineIt++) {
					if (line+lineIt<lines.size()) {
						if (!lines.get(line+lineIt).trim().equals(""))
							currentLineToMatch += lines.get(line+lineIt);
					}
				}
			}
			// TODO parse start and endline

			if (currentLineToMatch != null) {
				for (int i=0; i<entryList.size(); i++) {
					entryList.get(i).parseLine(line, currentLineToMatch, numLinesAtOnce);
				}
			}
		}

		for (int i=0; i<entryList.size(); i++) {
			List<ParseItem> resultingItems = entryList.get(i).getResultList();
			Collections.sort(resultingItems);

			ReverseLookupResponse response = new ReverseLookupResponse();
			for (ParseItem parseItem: resultingItems) {
				response = createResponseFromResult(responseList, parseItem, response);
			}

			if (isResponseEmpty(response)) {
				LOG.debug("Response is empty, not adding to response list");
			} else if (!isNameOrCompanySet(response)) {
				LOG.debug("Name and company is not set, not adding to response list");
			} else {
				LOG.debug("Response not empty, adding to response list");
				responseList.add(response);
			}

			for (ReverseLookupResponse r: responseList) {
				r.setFoundBy(lookupSite.getName());
			}
		}
	}

	public List<ReverseLookupResponse> getResponseList() {
		return this.responseList;
	}

	private ReverseLookupResponse createResponseFromResult(
			final List<ReverseLookupResponse> responseList,
			final ParseItem parseItem,
			ReverseLookupResponse currentResponse) {
		switch (parseItem.getType()) {
			case FIRSTNAME: {
				if (currentResponse.getFirstName() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setFirstName(parseItem.getValue());
				break;
			}
			case LASTNAME: {
				if (currentResponse.getLastName() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setLastName(parseItem.getValue());
				break;
			}
			case CITY: {
				if (currentResponse.getCity() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setCity(parseItem.getValue());
				break;
			}
			case COMPANY: {
				if (currentResponse.getCompany() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setCompany(parseItem.getValue());
				break;
			}
			case STREET: {
				if (currentResponse.getStreet() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setStreet(parseItem.getValue());
				break;
			}
			case HOUSE_NUMBER: {
				if (currentResponse.getHouseNumber() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setHouseNumber(parseItem.getValue());
				break;
			}
			case ZIPCODE: {
				if (currentResponse.getZipCode() != null) {
					responseList.add(currentResponse);
					currentResponse = new ReverseLookupResponse();
				}
				currentResponse.setZipCode(parseItem.getValue());
				break;
			}
		}
		return currentResponse;
	}

	private boolean isResponseEmpty(final ReverseLookupResponse response) {
		return (response.getCity() == null && response.getCompany() == null
				&& response.getFirstName() == null && response.getLastName() == null
				&& response.getStreet() == null && response.getHouseNumber() == null
				&& response.getZipCode() == null);
	}
	
	private boolean isNameOrCompanySet(final ReverseLookupResponse response) {
		return (response.getFirstName() != null || response.getLastName() != null || response.getCompany() != null);
	}
}