package com.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

public class SubServices implements Serializable {

	@ElementCollection(fetch = FetchType.EAGER)
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

	public boolean addSubService(String subSetvice) {
		return subServices.add(subSetvice);
	}

	public boolean removeSubService(String subService) {
		return subServices.remove(subService);
	}

}
