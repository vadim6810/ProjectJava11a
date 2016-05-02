package com.domain.interfaces;

import com.domain.model.ServiceStation;

public interface IServiceStation {
	boolean addServiceStation(ServiceStation servStat);

	boolean removeServiceStation(String serviceEmail);

	Iterable<ServiceStation> getServiceStationsByRequest(String... requests);

	Iterable<ServiceStation> getServiceStationByName(String name);

}
