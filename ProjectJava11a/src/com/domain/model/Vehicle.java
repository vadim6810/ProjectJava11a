package com.domain.model;

import java.io.Serializable;

public class Vehicle implements Serializable {
	String vendor;
	String model;
	String transmissionType;
	String carBodyType;
	int year;
	int engineCapacity;

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(String vendor, String model, String transmissionType, String carBodyType, int year,
			int engineCapacity) {
		this.vendor = vendor;
		this.model = model;
		this.transmissionType = transmissionType;
		this.carBodyType = carBodyType;
		this.year = year;
		this.engineCapacity = engineCapacity;
	}

	public String getVendor() {
		return vendor;
	}

	public String getModel() {
		return model;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public String getCarBodyType() {
		return carBodyType;
	}

	public int getYear() {
		return year;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	@Override
	public String toString() {
		return "Vehicle [vendor=" + vendor + ", model=" + model + ", transmissionType=" + transmissionType
				+ ", carBodyType=" + carBodyType + ", year=" + year + ", engineCapacity=" + engineCapacity + "]";
	}

}
