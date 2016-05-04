package com.domain.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import com.domain.interfaces.IServiceStation;
import com.domain.model.ServiceStation;

public class ServiceStationController implements IServiceStation {

	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

	@Override
	@Transactional
	public boolean addServiceStation(ServiceStation servStat) {
		boolean res = false;
		if (em.find(ServiceStation.class, servStat.getEmail()) == null) {
			em.persist(servStat);
			res = true;
		}
		return res;
	}

	@Override
	@Transactional
	public boolean removeServiceStation(String serviceEmail) {
		boolean res = false;
		ServiceStation servStat = em.find(ServiceStation.class, serviceEmail);
		if (serviceEmail != null) {
			em.remove(servStat);
			res = true;
		}
		return res;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByName(String name) {
		Query query = em.createQuery("select s from ServiceStation s where s.name=?1");
		query.setParameter(1, name);
		return query.getResultList();
	}

	@Override
	public Iterable<ServiceStation> getServiceStationsByRequest(String... requests) {
		Set<ServiceStation> res = new HashSet<ServiceStation>();
		String regionQuery = "select s from ServiceStation s";
		if (requests[0] != null || requests[1] != null) {
			regionQuery = regionQuery + " where";
			if (requests[0] != null) {
				regionQuery = regionQuery + " s.area='" + requests[0] + "'";
				if (requests[1] != null) {
					regionQuery = regionQuery + " and s.city='" + requests[1] + "'";
				}
			} else {
				if (requests[1] != null) {
					regionQuery = regionQuery + " s.city='" + requests[1] + "'";
				}
			}
		}
		Query query = em.createQuery(regionQuery);
		Iterable<ServiceStation> stations = query.getResultList();
		
		for(ServiceStation station : stations){
			if((requests[2] != null) && !station.getCarServiceTypes().contains(requests[2])){
				break;
			}
			
			if((requests[3] != null) && !station.getCarModels().contains(requests[3])){
				break;
			}
			
			if((requests[4] != null) && !station.getVehicleType().contains(requests[4])){
				break;
			}
			if((requests[5] != null) && !station.getServicesDirectory().getServicesDirectory().contains(requests[5])){
				break;
			}
			if((requests[6] != null) && !station.getServicesDirectory().getSubServicesDirectory(requests[5]).contains(requests[6])){
				break;
			}
			res.add(station);
		}
				
		return res;
	}
}
