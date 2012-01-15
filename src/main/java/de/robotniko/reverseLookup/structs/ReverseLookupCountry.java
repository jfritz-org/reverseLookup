package de.robotniko.reverseLookup.structs;

import java.util.ArrayList;
import java.util.List;

public class ReverseLookupCountry {

	private String code;
	private List<ReverseLookupSite> sites = new ArrayList<ReverseLookupSite>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void addWebsite(final ReverseLookupSite site) {
		if (!sites.contains(site)) {
			sites.add(site);
		}
	}

	public int getNumWebsites() {
		return sites.size();
	}

	public ReverseLookupSite getLookupSite(final int index) {
		if (index >= 0 && index<sites.size()) {
			return sites.get(index);
		}
		return null;
	}
}