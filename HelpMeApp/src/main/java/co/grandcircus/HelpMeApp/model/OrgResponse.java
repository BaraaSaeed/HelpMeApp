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

public class OrgResponse {

	private String charityName;
	private Integer ein;
	private MailingAddress mailingAddress;
	
	public String getCharityName() {
		return charityName;
	}
	public void setCharityName(String charityName) {
		this.charityName = charityName;
	}
	public Integer getEin() {
		return ein;
	}
	public void setEin(Integer ein) {
		this.ein = ein;
	}
	public MailingAddress getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(MailingAddress mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	@Override
	public String toString() {
		return "OrgResponse [charityName=" + charityName + ", ein=" + ein + ", mailingAddress=" + mailingAddress + "]";
	}

	
}
