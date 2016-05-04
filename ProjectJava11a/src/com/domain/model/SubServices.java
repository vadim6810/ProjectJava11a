package com.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class SubServices implements Serializable {
	
	@ElementCollection
	Set<String> subServices = new HashSet<String>();

	public SubServices() {
		// TODO Auto-generated constructor stub
	}

	public SubServices(Set<String> subServices) {
		this.subServices = subServices;
	}

	public Set<String> getSubServices() {
		return subServices;
	}

	public void setSubServices(Set<String> subServices) {
		this.subServices = subServices;
	}

}
