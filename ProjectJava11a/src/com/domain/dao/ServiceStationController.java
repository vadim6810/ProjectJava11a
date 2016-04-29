package com.domain.dao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.interfaces.IServiceStation;
import com.domain.model.ServiceStation;

public class ServiceStationController implements IServiceStation {

	@Override
	public boolean addServiceStation(ServiceStation servStat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeServiceStation(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByArea(String area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
