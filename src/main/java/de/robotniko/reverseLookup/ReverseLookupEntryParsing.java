package de.robotniko.reverseLookup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import de.robotniko.helper.HTMLUtil;
import de.robotniko.helper.StringHelper;
import de.robotniko.reverseLookup.structs.ParseItem;
import de.robotniko.reverseLookup.structs.ParseItemType;
import de.robotniko.reverseLookup.structs.ReverseLookupEntry;

public class ReverseLookupEntryParsing {
	private final static Logger LOG = Logger.getLogger(ReverseLookupEntryParsing.class);

	private ReverseLookupEntry entry;

	private Pattern cityPattern;
	private Pattern firstNamePattern;
	private Pattern lastNamePattern;
	private Pattern namePattern;
	private Pattern streetPattern;
	private Pattern houseNumberPattern;
	private Pattern zipPattern;
	private Pattern companyPattern;

	private List<ParseItem> parseItems = new ArrayList<ParseItem>();
	private int numLinesAtOnce;

	public ReverseLookupEntryParsing(final ReverseLookupEntry entry) {
		this.entry = entry;

		initPatterns(entry);
	}

	public List<ParseItem> getResultList() {
		return this.parseItems;
	}

	public void parseLine(final int lineNumber, final String currentLine, final int numLinesAtOnce) {
		this.numLinesAtOnce = numLinesAtOnce;
				
		Matcher nameMatcher;
		Matcher cityMatcher;
		Matcher firstNameMatcher;
		Matcher lastNameMatcher;
		Matcher streetMatcher;
		Matcher houseNumberMatcher;
		Matcher zipMatcher;
		Matcher companyMatcher;

//		if (currentLine.contains("Johannes")) {
//			System.out.println(currentLine);
//		}
		if (namePattern != null) {
			nameMatcher = namePattern.matcher(currentLine);
			if (nameMatcher.find() && nameMatcher.groupCount() > 0) {
				List<ParseItem> resp = parseNameFields(nameMatcher, lineNumber);
				for (ParseItem item: resp) {
					addItemIfNotAlreadyTracked(item, lineNumber);
				}
			}
		}

		if (cityPattern != null) {
			cityMatcher = cityPattern.matcher(currentLine);
			if (cityMatcher.find() && cityMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.CITY, cityMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}

		if (firstNamePattern != null) {
			firstNameMatcher = firstNamePattern.matcher(currentLine);
			if (firstNameMatcher.find() && firstNameMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.FIRSTNAME, firstNameMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}

		if (lastNamePattern != null) {
			lastNameMatcher = lastNamePattern.matcher(currentLine);
			if (lastNameMatcher.find() && lastNameMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.LASTNAME, lastNameMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}

		if (streetPattern != null) {
			streetMatcher = streetPattern.matcher(currentLine);
			if (streetMatcher.find() && streetMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.STREET, streetMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}

		if (houseNumberPattern != null) {
			houseNumberMatcher = houseNumberPattern.matcher(currentLine);
			if (houseNumberMatcher.find() && houseNumberMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.HOUSE_NUMBER, houseNumberMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}

		if (zipPattern != null) {
			zipMatcher = zipPattern.matcher(currentLine);
			if (zipMatcher.find() && zipMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.ZIPCODE, zipMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}

		if (companyPattern != null) {
			companyMatcher = companyPattern.matcher(currentLine);
			if (companyMatcher.find() && companyMatcher.groupCount() > 0) {
				ParseItem item = parseLine(ParseItemType.COMPANY, companyMatcher, lineNumber);
				addItemIfNotAlreadyTracked(item, lineNumber);
			}
		}
	}
	
	private void addItemIfNotAlreadyTracked(ParseItem item, int lineNumber) {
		boolean found = false;
		for (int i=0; i<parseItems.size(); i++) {
		  ParseItem trackedItem = parseItems.get(i);
		  if (item.getType() == trackedItem.getType())
		  {
			  int line1 = item.getLine()-numLinesAtOnce+1;
			  int line2 = trackedItem.getLine();
			  if (line1 <= line2) {
				  found = true;
			  }
		  }
		}
		if (!found) {
			parseItems.add(item);
		}
	}

	private void initPatterns(final ReverseLookupEntry entry) {
		if (entry.getCityPattern() != null) {
			this.cityPattern = Pattern.compile(entry.getCityPattern());
		}
		if (entry.getFirstNamePattern() != null) {
			this.firstNamePattern = Pattern.compile(entry.getFirstNamePattern());
		}
		if (entry.getLastNamePattern() != null) {
			this.lastNamePattern = Pattern.compile(entry.getLastNamePattern());
		}
		if (entry.getNamePattern() != null) {
			this.namePattern = Pattern.compile(entry.getNamePattern());
		}
		if (entry.getStreetPattern() != null) {
			this.streetPattern = Pattern.compile(entry.getStreetPattern());
		}
		if (entry.getHouseNumberPattern() != null) {
			this.houseNumberPattern = Pattern.compile(entry.getHouseNumberPattern());
		}
		if (entry.getZipPattern() != null) {
			this.zipPattern = Pattern.compile(entry.getZipPattern());
		}
		if (entry.getCompanyPattern() != null) {
			this.companyPattern = Pattern.compile(entry.getCompanyPattern());
		}
	}

	private ParseItem parseLine(final ParseItemType type, final Matcher matcher, final int lineNumber) {
		//read in and concatenate all groupings
		String str = "";
		for(int k=1; k <= matcher.groupCount(); k++){
			if(matcher.group(k) != null)
				str = str + matcher.group(k).trim() + " ";
		}

		String value = cleanupString(str);

		int count = matcher.groupCount();
		ParseItem parseItem = new ParseItem(type);
		parseItem.setLine(lineNumber);
		parseItem.setStartIndex(matcher.start(1));
		parseItem.setValue(removeUnnecessaryWhitespaces(value));

		return parseItem;
	}
	
	private String removeUnnecessaryWhitespaces(String input) {
		return input.trim().replaceAll("\\s+", " ");
	}

	private String cleanupString(String str) {
		String value = HTMLUtil.stripEntities(str);
		value = StringHelper.replaceSpecialCharsUTF(value);
		value = value.replaceAll("%20", " ");
		value = value.replaceAll(" ", " "); // first char is an encoded space, not an ASCII space
		value = value.replaceAll("\\s\\s+", " ");
		value = value.replaceAll(",", "");
		value = value.trim();
		if (value.startsWith("\\d\\.")) {
			value = value.substring(2).trim();
		}
		return value;
	}

	private List<ParseItem> parseNameFields(Matcher nameMatcher, int line) {
		//read in and concatenate all groupings
		String str = "";
		for(int k=1; k <= nameMatcher.groupCount(); k++){
			if(nameMatcher.group(k) != null)
				str = str + nameMatcher.group(k).trim() + " ";
		}

		String[] split;
		split = str.split(" ", 2);

		String foundFirst = cleanupString(split[0]);
		if (foundFirst.matches("^\\d\\.")) {
			str = str.substring(3).trim();
			split = str.split(" ", 2);
			foundFirst = cleanupString(split[0]);
		}
		
		String foundSecond = "";
		if (split.length > 1) {
			if (split[1].length() > 0) {
				foundSecond = cleanupString(split[1]);
			}
		}
		
		ParseItem firstnameItem = new ParseItem(ParseItemType.FIRSTNAME);
		ParseItem lastnameItem = new ParseItem(ParseItemType.LASTNAME);

		if (!entry.shouldSwapFirstAndLastName()) {
			lastnameItem.setLine(line);
			lastnameItem.setStartIndex(nameMatcher.start(1));
			lastnameItem.setValue(foundFirst);
			firstnameItem.setLine(line);
			firstnameItem.setStartIndex(nameMatcher.start(1));
			firstnameItem.setValue(foundSecond);
		} else {
			firstnameItem.setLine(line);
			firstnameItem.setStartIndex(nameMatcher.start(1));
			firstnameItem.setValue(foundFirst);
			lastnameItem.setLine(line);
			lastnameItem.setStartIndex(nameMatcher.start(1));
			lastnameItem.setValue(foundSecond);
		}

		List<ParseItem> result = new ArrayList<ParseItem>();
		result.add(firstnameItem);
		result.add(lastnameItem);

		return result;
	}
}
