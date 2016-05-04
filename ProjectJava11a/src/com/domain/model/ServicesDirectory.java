package com.domain.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
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
	
	/*follow methods return true if the data was modified,
	and return false otherwise*/

	public Set<String> getServicesDirectory() {
		return servicesDirectory.keySet();
	}

	public Set<String> getSubServicesDirectory(String service) {
		return servicesDirectory.get(service).getSubServices();
	}

	public boolean addServiceType(String service) {
		if (!servicesDirectory.containsKey(service)) {
			servicesDirectory.put(service, new SubServices());
			return true;
		}
		servicesDirectory.put(service, new SubServices());
		return false;
	}

	public boolean addServiceType(String service, Set<String> subServices) {
		if (!servicesDirectory.containsKey(service)) {
			servicesDirectory.put(service, new SubServices(subServices));
			return true;
		}
		servicesDirectory.put(service, new SubServices(subServices));
		return false;
	}

	public SubServices removeServiceType(String service) {
		return servicesDirectory.remove(service);
	}

	public boolean addSubServiceType(String service, String subService) {
		if (!servicesDirectory.containsKey(service)) {
			SubServices subServices = servicesDirectory.get(service);
			if (!subServices.getSubServices().add(subService)) {
				servicesDirectory.put(service, subServices);
				return true;
			}
		}
		return false;
	}

	public boolean removeSubServiceType(String service, String subService) {
		if (!servicesDirectory.containsKey(service)) {
			SubServices subServices = servicesDirectory.get(service);
			if (!subServices.getSubServices().remove(subService)) {
				servicesDirectory.put(service, subServices);
				return true;
			}
		}
		return false;
	}

}
