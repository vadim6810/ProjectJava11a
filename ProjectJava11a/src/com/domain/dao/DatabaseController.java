package com.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.domain.interfaces.IPersistenceController;
import com.domain.model.Client;
import com.domain.model.ServiceStation;
import com.domain.model.TenderRequest;

public class DatabaseController implements IPersistenceController {

	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

	@Override
	@Transactional
	public boolean addClient(Client client) {
		boolean res = false;
		if (em.find(Client.class, client.getEmail()) == null) {
			em.persist(client);
			res = true;
		}
		return res;
	}

	@Override
	@Transactional
	public boolean removeClient(String clientEmail) {
		boolean res = false;
		Client client = em.find(Client.class, clientEmail);
		if (client == null) {
			em.remove(client);
			res = true;
		}
		return res;
	}

	@Override
	public Client getClientByEmail(String clientEmail) {
		return em.find(Client.class, clientEmail);
	}

	@Override
	@Transactional
	public Client editClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addTender(String clientEmail, TenderRequest tender) {
		if (em.find(TenderRequest.class, tender.getId()) == null) {
			em.persist(tender);
		}
		Client client = em.find(Client.class, clientEmail);
		client.addTender(tender);
		em.persist(client);

	}

	@Override
	public TenderRequest getTenderById(int id) {
		return em.find(TenderRequest.class, id);
	}

	@Override
	public Iterable<TenderRequest> getTendersListByClient(String clientEmail) {
		Client client = em.find(Client.class, clientEmail);
		return client.getTenders();
	}

	@Override
	@Transactional
	public boolean removeTenderById(int id) {
		boolean res = false;
		TenderRequest tender = em.find(TenderRequest.class, id);
		if (tender != null) {
			em.remove(tender);
			res = true;
		}
		return res;
	}

	@Override
	@Transactional
	public TenderRequest editTender(String clientEmail, TenderRequest tender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TenderRequest> getTendersListForService(String serviceEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOfferToTender(int tenderId, String serviceEmail, float bid) {
		boolean res = false;
		TenderRequest tender = em.find(TenderRequest.class, tenderId);
		if (tender != null) {
			ServiceStation station = em.find(ServiceStation.class, serviceEmail);
			if (station != null) {
				Float curBid = tender.getBids().get(serviceEmail);
				if ((curBid == null) || (bid > curBid)) {
					tender.addTenderMember(station, bid);
					em.persist(tender);
					res = true;
				}

			}
		}

		return res;
	}

	/*@Override
	@Transactional
	public boolean removeOfferFromTender(int tenderId, String serviceEmail) {
		TenderRequest tender = em.find(TenderRequest.class, tenderId);
		ServiceStation station = em.find(ServiceStation.class, serviceEmail);
		return false;
	}*/

	@Override
	@Transactional
	public boolean putScore(String serviceEmail, String clientEmail, int score) {
		boolean res = false;
		if (score <= 5 && score > 0) {
			ServiceStation station = em.find(ServiceStation.class, serviceEmail);
			if (station != null) {
				Client client = em.find(Client.class, clientEmail);
				if (client != null) {
					if (client.addScoredStation(station)) {
						station.addClientRate(client, score);
						res = true;
						em.persist(client);
						em.persist(station);
					}
				}
			}
		}
		return res;
	}

	@Override
	@Transactional
	public void putComment(String serviceEmail, String comment) {
		ServiceStation station = em.find(ServiceStation.class, serviceEmail);
		station.addComment(comment);
		em.persist(station);
	}

}
