package com.domain.dao;

import java.util.*;

public class ServicesDirectory {
	Map<String, Set<String>> servicesDirectory;

	public ServicesDirectory() {
		this.servicesDirectory = new HashMap<String, Set<String>>();
	}

	public ServicesDirectory(Map<String, Set<String>> servicesDirectory) {
		this.servicesDirectory = servicesDirectory;
	}

	public ServicesDirectory(Set<String> services) {
		for (String service : services) {
			this.servicesDirectory.put(service, new HashSet<String>());
		}
	}
	
	/*follow methods return true if the data was modified,
	and return false otherwise*/

	public Set<String> getServicesDirectory() {
		return servicesDirectory.keySet();
	}

	public Set<String> getSubServicesDirectory(String service) {
		return servicesDirectory.get(service);
	}

	public boolean addServiceType(String service) {
		if (!servicesDirectory.containsKey(service)) {
			servicesDirectory.put(service, new HashSet<String>());
			return true;
		}
		servicesDirectory.put(service, new HashSet<String>());
		return false;
	}

	public boolean addServiceType(String service, Set<String> subServices) {
		if (!servicesDirectory.containsKey(service)) {
			servicesDirectory.put(service, subServices);
			return true;
		}
		servicesDirectory.put(service, subServices);
		return false;
	}

	public Set<String> removeServiceType(String service) {
		return servicesDirectory.remove(service);
	}

	public boolean addSubServiceType(String service, String subService) {
		if (!servicesDirectory.containsKey(service)) {
			Set<String> subServices = servicesDirectory.get(service);
			if (!subServices.add(subService)) {
				servicesDirectory.put(service, subServices);
				return true;
			}
		}
		return false;
	}

	public boolean removeSubServiceType(String service, String subService) {
		if (!servicesDirectory.containsKey(service)) {
			Set<String> subServices = servicesDirectory.get(service);
			if (!subServices.remove(subService)) {
				servicesDirectory.put(service, subServices);
				return true;
			}
		}
		return false;
	}

}
