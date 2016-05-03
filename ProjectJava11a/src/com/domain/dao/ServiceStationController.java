package com.domain.dao;

import javax.persistence.*;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
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
		if (em.find(ServiceStation.class, servStat.getEmail())==null){
			em.persist(servStat);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeServiceStation(String serviceEmail) {
		boolean res = false;
		ServiceStation servStat = em.find(ServiceStation.class, serviceEmail);
			if (serviceEmail !=null){
				em.remove(servStat);
				res = true;
		}
		return res;
	}

	@Override
	public Iterable<ServiceStation> getServiceStationByName(String name) {
		Query query = (Query) em.createQuery("select s from ServiceStation s where s.name=?1");
		query.setParameter(1, name);
		return ((javax.persistence.Query) query).getResultList();
	}

	@Override
	public Iterable<ServiceStation> getServiceStationsByRequest(String... requests) {
		Query query = (Query) em.createQuery("select s from ServiceStation s where s.requests=?1");
		query.setParameter(1, requests);
		return ((javax.persistence.Query) query).getResultList();
	}

}
