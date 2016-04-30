package com.domain.interfaces;

import com.domain.model.Client;
import com.domain.model.TenderRequest;

public interface IPersistenceController {

	boolean addClient(Client client);

	boolean removeClient(String clientEmail);

	Client getClientByEmail(String clientEmail);

	Client editClient(Client client);

	boolean putScore(String serviceEmail, String clientEmail, int score);

	void putComment(String serviceEmail, String comment);

	void addTender(String clientEmail, TenderRequest tender);

	TenderRequest getTenderById(int id);

	Iterable<TenderRequest> getTendersListByClient(String clientEmail);

	boolean removeTenderById(int id);

	TenderRequest editTender(String clientEmail, TenderRequest tender);

	Iterable<TenderRequest> getTendersListForService(String serviceEmail);

	boolean addOfferToTender(int tenderId, String serviceEmail, float bid);

	//boolean removeOfferFromTender(int tenderId, String serviceEmail);

}
