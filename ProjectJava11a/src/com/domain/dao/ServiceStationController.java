package com.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.domain.interfaces.IServiceStation;
import com.domain.model.ServiceStation;

public class ServiceStationController implements IServiceStation {
	
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

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
	public Iterable<ServiceStation> getServiceStationByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationsByRequest(String... requests) {
		// TODO Auto-generated method stub
		return null;
	}

}
