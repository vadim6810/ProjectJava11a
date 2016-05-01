package com.domain.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.scheduling.annotation.Scheduled;

@Entity
@Indexed
public class TenderRequest {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne
	Client client;
	boolean status = false;
	@Temporal(value = TemporalType.DATE)
	Date startDate;
	@Temporal(value = TemporalType.DATE)
	Date closeDate;
	String tenderDescription;
	String vehicleType;
	@Embedded
	Vehicle car;
	@Field
	String area;
	@Field
	String city;
	@Field
	String carServiceType;
	String serviceType;
	String subServiceType;
	@ManyToMany(mappedBy = "tenders")
	Set<ServiceStation> tenderMembers;
	Map<String, Float> bids; // here String - email of ServiceStation

	public TenderRequest() {
		// TODO Auto-generated constructor stub
	}

	public TenderRequest(Client client, Date closeDate, String tenderDescription, String vehicleType, Vehicle car,
			String area, String city, String carServiceType, String serviceType, String subServiceType) {
		this.client = client;
		this.startDate = new Date();
		this.closeDate = closeDate;
		this.tenderDescription = tenderDescription;
		this.vehicleType = vehicleType;
		this.car = car;
		this.area = area;
		this.city = city;
		this.carServiceType = carServiceType;
		this.serviceType = serviceType;
		this.subServiceType = subServiceType;
		checkstatus();
	}

	public int getId() {
		return id;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getTenderDescription() {
		return tenderDescription;
	}

	public void setTenderDescription(String tenderDescription) {
		this.tenderDescription = tenderDescription;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Vehicle getCar() {
		return car;
	}

	public void setCar(Vehicle car) {
		this.car = car;
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

	public String getCarServiceType() {
		return carServiceType;
	}

	public void setCarServiceType(String carServiceType) {
		this.carServiceType = carServiceType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSubServiceType() {
		return subServiceType;
	}

	public void setSubServiceType(String subServiceType) {
		this.subServiceType = subServiceType;
	}

	public Client getClient() {
		return client;
	}

	public Set<ServiceStation> getTenderMembers() {
		return tenderMembers;
	}

	public Map<String, Float> getBids() {
		return bids;
	}

	public void addTenderMember(ServiceStation station, float price) {
		tenderMembers.add(station);
		bids.put(station.getEmail(), price);

	}

	public Map.Entry<String, Float> getTenderResult() {
		Map.Entry<String, Float> res = null;
		if ((status == true) || !(bids.isEmpty())) {
			Set<Map.Entry<String, Float>> bidsSet = bids.entrySet();
			Float min = Float.MAX_VALUE;
			for (Map.Entry<String, Float> bid : bidsSet) {
				if (bid.getValue() < min) {
					min = bid.getValue();
					res = bid;
				}

			}
		}
		return res;
	}

	//Version temporary. Need include the to method getTenderResult
	@Scheduled(fixedDelay = 43200000)
	private void checkstatus() {
		if ((new Date()).after(closeDate)) {
			status = true;
		}

	}

}
