package com.domain.interfaces;

import com.domain.model.ServiceStation;

public interface IServiceStation {
	boolean addServiceStation(ServiceStation servStat);
	boolean removeServiceStation(int id);
	Iterable<ServiceStation>getServiceStationByArea(String area);
	Iterable<ServiceStation>getServiceStationByCity(String city);
	
}
