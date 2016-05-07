package com.domain.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyTemporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class ServiceStation {
	@Id
	String email;
	String password;
	String name;
	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> carServiceTypes = new HashSet<String>();
	String phone;
	String fax;
	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> vehicleType = new HashSet<String>();
	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> carModels = new HashSet<String>();
	@Field
	String area;
	@Field
	String city;
	String address;
	@Embedded
	ServicesDirectory servicesDirectory;
	String url;
	String avatar;
	float rating = 0;
	int nRates = 0;
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyTemporal(value = TemporalType.TIMESTAMP)
	Map<Date, String> comments = new HashMap<Date, String>();
	@ManyToMany(fetch = FetchType.EAGER)
	Set<TenderRequest> tenders = new HashSet<TenderRequest>();
	@ManyToMany(mappedBy = "scoredStations", fetch = FetchType.EAGER)
	Set<Client> clientRate = new HashSet<Client>();

	/*
	 * String contactPerson; boolean isInsuranceCompaniesAccepted; String
	 * license;
	 */

	public ServiceStation() {
		// TODO Auto-generated constructor stub
	}

	public ServiceStation(String email, String password, String name, Set<String> carServiceTypes, String phone,
			String area, String city, String address) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.carServiceTypes = carServiceTypes;
		this.phone = phone;
		this.area = area;
		this.city = city;
		this.address = address;
		this.servicesDirectory = new ServicesDirectory();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getCarServiceTypes() {
		return carServiceTypes;
	}

	public void setCarServiceTypes(Set<String> carServiceTypes) {
		this.carServiceTypes = carServiceTypes;
	}

	public Set<String> getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Set<String> vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Set<String> getCarModels() {
		return carModels;
	}

	public void setCarModels(Set<String> carModels) {
		this.carModels = carModels;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ServicesDirectory getServicesDirectory() {
		return servicesDirectory;
	}

	public void setServicesDirectory(ServicesDirectory servicesDirectory) {
		this.servicesDirectory = servicesDirectory;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public float getRating() {
		return rating;
	}

	public Map<Date, String> getComments() {
		return comments;
	}

	public Set<TenderRequest> getTenders() {
		return tenders;
	}

	public Set<Client> getClientRate() {
		return clientRate;
	}

	public int getnRates() {
		return nRates;
	}

	public boolean addCarServiceType(String carServiceType) {
		return carServiceTypes.add(carServiceType);
	}

	public boolean removeCarServiceType(String carServiceType) {
		return carServiceTypes.remove(carServiceType);
	}

	public String addComment(String comment) {
		return comments.put(new Date(), comment);
	}

	public void removeOldComments(Date oldDate) {
		Set<Date> dates = comments.keySet();
		for (Date date : dates) {
			if (date.before(oldDate)) {
				comments.remove(date);
			}
		}
	}

	public boolean addVehicleType(String carType) {
		return vehicleType.add(carType);
	}

	public boolean removeVehicleType(String carType) {
		return vehicleType.remove(carType);
	}

	public boolean addCareModel(String carModel) {
		return carModels.add(carModel);
	}

	public boolean removeCarModel(String carModel) {
		return carModels.remove(carModel);
	}

	public boolean addTender(TenderRequest tender) {
		return tenders.add(tender);
	}

	public boolean removeTender(TenderRequest tender) {
		return tenders.remove(tender);
	}

	public void addClientRate(Client client, int newRate) {
		if (clientRate.add(client)) {
			rating = (rating * nRates + newRate) / (nRates + 1);
			nRates++;
		}
	}

	@Override
	public String toString() {
		return "ServiceStation [email=" + email + ", name=" + name + ", carServiceTypes=" + carServiceTypes + ", phone="
				+ phone + ", area=" + area + ", city=" + city + ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceStation other = (ServiceStation) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
