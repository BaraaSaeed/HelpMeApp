/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/
/**
 * 
 */
package co.grandcircus.HelpMeApp.model;

import java.util.List;

public class OrgResponse {
	private List<OrgObject> results;

	public OrgResponse() {
		super();
	}

	public OrgResponse(List<OrgObject> results) {
		super();
		this.results = results;
	}

	public List<OrgObject> getResults() {
		return results;
	}

	public void setResults(List<OrgObject> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "OrgResponse [results=" + results + "]";
	}

}
