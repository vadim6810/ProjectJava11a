package com.domain.interfaces;

import com.domain.model.ServiceStation;

public interface IServiceStation {
	boolean addServiceStation(ServiceStation servStat);

	boolean removeServiceStation(String serviceEmail);

	Iterable<ServiceStation> getServiceStationByArea(String... region);

	Iterable<ServiceStation> getServiceStationByCity(String city);

	Iterable<ServiceStation> getServiceStationByServiceTypes(String carServiceTypes);

	Iterable<ServiceStation> getServiceStationByVenicleTypes(String vehicleType);

	Iterable<ServiceStation> getServiceStationByVenicleServiceTypes(String vehicleType, String carServiceTypes);
	
	Iterable<ServiceStation> getServiceStationByName(String name);

	ServiceStation editServiceStation(ServiceStation servStat);
}
