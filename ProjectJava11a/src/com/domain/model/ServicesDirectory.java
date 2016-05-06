package com.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;

public class ServicesDirectory implements Serializable {
	@ElementCollection
	Map<String, SubServices> servicesDirectory;

	public ServicesDirectory() {
		this.servicesDirectory = new HashMap<String, SubServices>();
	}

	public ServicesDirectory(Map<String, SubServices> servicesDirectory) {
		this.servicesDirectory = servicesDirectory;
	}

	public ServicesDirectory(Set<String> services) {
		for (String service : services) {
			this.servicesDirectory.put(service, new SubServices());
		}
	}

	/*
	 * follow methods return true if the data was modified, and return false
	 * otherwise
	 */

	public Map<String, SubServices> getServicesDirectory() {
		return servicesDirectory;
	}

	public Set<String> getSubServicesDirectory(String service) {
		return servicesDirectory.get(service).getSubServices();
	}

	public boolean addServiceType(String service) {
		boolean res = false;
		if (!servicesDirectory.containsKey(service)) {
			servicesDirectory.put(service, new SubServices());
			res = true;
		}
		return res;
	}

	public boolean addServiceType(String service, Set<String> subServices) {
		boolean res = false;
		if (servicesDirectory.containsKey(service)) {
			res = true;
		}
		servicesDirectory.put(service, new SubServices(subServices));
		return res;
	}

	public SubServices removeServiceType(String service) {
		return servicesDirectory.remove(service);
	}

	public void addServiceType(String service, SubServices subServices) {
		servicesDirectory.put(service, subServices);
	}

}
