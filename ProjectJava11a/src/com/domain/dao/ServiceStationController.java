package com.domain.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.domain.interfaces.IServiceStation;
import com.domain.model.ServiceStation;

public class ServiceStationController implements IServiceStation {

	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

	@Override
	@Transactional
	public boolean addServiceStation(ServiceStation serviceStation) {
		boolean res = false;
		if (em.find(ServiceStation.class, serviceStation.getEmail()) == null) {
			em.persist(serviceStation);
			res = true;
		} else {
			em.merge(serviceStation);
			res = true;
		}
		return res;
	}

	@Override
	@Transactional
	public boolean removeServiceStation(String serviceEmail) {
		boolean res = false;
		ServiceStation serviceStation = em.find(ServiceStation.class, serviceEmail);
		if (serviceStation != null) {
			em.remove(serviceStation);
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

		boolean indicator = true;
		for (int i = 2; i < requests.length; i++) {
			if (requests[i] != null) {
				indicator = false;
				break;
			}
		}

		if (indicator) {
			return stations;
		}

		for (ServiceStation station : stations) {
			Set<String> currentSet = station.getCarServiceTypes();
			if ((requests[2] != null) && currentSet != null && !currentSet.contains(requests[2])) {
				break;
			}

			currentSet = station.getCarModels();
			if ((requests[3] != null) && currentSet != null && !currentSet.contains(requests[3])) {
				break;
			}

			currentSet = station.getVehicleType();
			if ((requests[4] != null) && currentSet != null && !currentSet.contains(requests[4])) {
				break;
			}

			if ((requests[5] != null) && station.getServicesDirectory() != null) {
				currentSet = station.getServicesDirectory().getServicesDirectory().keySet();
				if (!currentSet.contains(requests[5])) {
					break;
				}
			}

			currentSet = station.getServicesDirectory().getSubServicesDirectory(requests[5]);
			if ((requests[6] != null) && currentSet != null && !currentSet.contains(requests[6])) {
				break;
			}
			res.add(station);
		}

		return res;
	}
}
