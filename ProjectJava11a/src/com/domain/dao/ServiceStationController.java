package com.domain.dao;

import com.domain.interfaces.IServiceStation;
import com.domain.model.ServiceStation;

public class ServiceStationController implements IServiceStation {

	@Override
	public boolean addServiceStation(ServiceStation servStat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeServiceStation(String serviceEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByArea(String... region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByServiceTypes(String carServiceTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByVenicleTypes(String vehicleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByVenicleServiceTypes(String vehicleType, String carServiceTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceStation editServiceStation(ServiceStation servStat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
