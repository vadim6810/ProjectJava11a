package com.domain.interfaces;

import java.util.Set;

import com.domain.model.Client;
import com.domain.model.TenderRequest;

public interface IPersistenceController {

	boolean addClient(Client client);

	boolean removeClient(String clientEmail);

	Client getClientByEmail(String clientEmail);

	Client editClient(Client client);

	void addTender(String clientEmail, TenderRequest tender);

	TenderRequest getTenderById(int id);

	Set<TenderRequest> getTendersListByClient(String clientEmail);

	TenderRequest removeTenderById(int id);

	TenderRequest editTender(String clientEmail, TenderRequest tender);

	Set<TenderRequest> getTendersListForService(String serviceEmail);

	boolean addOfferToTender(int tenderId, String serviceEmail, float bid);

	boolean removeOfferFromTender(int tenderId, String serviceEmail);

}
