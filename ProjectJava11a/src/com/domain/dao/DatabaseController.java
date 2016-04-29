package com.domain.dao;

import java.util.Set;

import com.domain.interfaces.IPersistenceController;
import com.domain.model.Client;
import com.domain.model.TenderRequest;

public class DatabaseController implements IPersistenceController {

	@Override
	public boolean addClient(Client client) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeClient(String clientEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client getClientByEmail(String clientEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client editClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTender(String clientEmail, TenderRequest tender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TenderRequest getTenderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TenderRequest> getTendersListByClient(String clientEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenderRequest removeTenderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenderRequest editTender(String clientEmail, TenderRequest tender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TenderRequest> getTendersListForService(String serviceEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOfferToTender(int tenderId, String serviceEmail, float bid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeOfferFromTender(int tenderId, String serviceEmail) {
		// TODO Auto-generated method stub
		return false;
	}

}
