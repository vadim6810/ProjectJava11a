package com.domain.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import com.domain.interfaces.*;
import com.domain.model.*;

@RestController
public class RequestHandler {
	private static final String COOKIE_SESSION_NAME = "sId";
	private static final String TEST_URL = "test/test";

	@Autowired
	ApplicationContext ctx;
	@Autowired
	IPersistenceController dbController;
	@Autowired
	IServiceStation dbScenter;
	@Autowired
	IDummy dummy;

	@RequestMapping(value = IRequestConstants.ADD_CLIENT, method = RequestMethod.PUT)
	public @ResponseBody boolean addShop(@RequestBody Client client) {
		return dbController.addClient(client);
	}

	@RequestMapping(value = IRequestConstants.ADD_OFFER_TO_TENDER, method = RequestMethod.PUT)
	public @ResponseBody boolean addOfferTender(@RequestBody int tenderId, String serviceEmail, float bid) {
		return dbController.addOfferToTender(tenderId, serviceEmail, bid);
	}

	@RequestMapping(value = IRequestConstants.REMOVE_CLIENT, method = RequestMethod.DELETE)
	public @ResponseBody boolean removeClient(@RequestBody String clientEmail) {
		return dbController.removeClient(clientEmail);
	}

	@RequestMapping(value = IRequestConstants.GET_CLIENT_BY_EMAIL, method = RequestMethod.GET)
	public @ResponseBody Client getClientByEmail(@RequestBody String clientEmail) {
		return dbController.getClientByEmail(clientEmail);
	}

	@RequestMapping(value = IRequestConstants.PUT_SCORE, method = RequestMethod.PUT)
	public @ResponseBody boolean putScore(@RequestBody String serviceEmail, String clientEmail, int score) {
		return dbController.putScore(serviceEmail, clientEmail, score);
	}

	@RequestMapping(value = IRequestConstants.PUT_COMMENT, method = RequestMethod.PUT)
	public @ResponseBody void putComment(@RequestBody String serviceEmail, String comment) {
		dbController.putComment(serviceEmail, comment);
	}

	@RequestMapping(value = IRequestConstants.ADD_TENDER, method = RequestMethod.PUT)
	public @ResponseBody void addTender(@RequestBody String clientEmail, TenderRequest tender) {
		dbController.addTender(clientEmail, tender);
	}

	@RequestMapping(value = IRequestConstants.GET_TENDER_BY_ID, method = RequestMethod.GET)
	public @ResponseBody TenderRequest getTenderById(@RequestBody int id) {
		return dbController.getTenderById(id);
	}

	@RequestMapping(value = IRequestConstants.GET_TENDERS_LIST_BY_CLIENT, method = RequestMethod.GET)
	Iterable<TenderRequest> getTendersListByClient(@RequestBody String clientEmail) {
		return dbController.getTendersListByClient(clientEmail);
	}

	@RequestMapping(value = IRequestConstants.REMOVE_TENDER_BY_ID, method = RequestMethod.DELETE)
	public @ResponseBody boolean removeTenderById(@RequestBody int id) {
		return dbController.removeTenderById(id);
	}

	@RequestMapping(value = IRequestConstants.GET_TENDERS_LIST_FOR_SERVICE, method = RequestMethod.GET)
	public @ResponseBody Iterable<TenderRequest> getTendersListForService(@RequestBody String serviceEmail) {
		return dbController.getTendersListForServiceStation(serviceEmail);
	}

	@RequestMapping(value = IRequestConstants.ADD_SERVICE_STATION, method = RequestMethod.PUT)
	public @ResponseBody boolean addServiceStation(@RequestBody ServiceStation servStat) {
		return dbScenter.addServiceStation(servStat);
	}

	@RequestMapping(value = IRequestConstants.REMOVE_SERVICE_STATION, method = RequestMethod.DELETE)
	public @ResponseBody boolean removeServiceStation(@RequestBody String serviceEmail) {
		return dbScenter.removeServiceStation(serviceEmail);
	}

	@RequestMapping(value = IRequestConstants.GET_SERVICE_STATION_BY_REQUEST, method = RequestMethod.GET)
	public @ResponseBody Iterable<ServiceStation> getServiceStationsByRequest(@RequestBody String... requests) {
		return dbScenter.getServiceStationsByRequest(requests);
	}

	@RequestMapping(value = IRequestConstants.GET_SERVICESTATION_BY_NAME, method = RequestMethod.GET)
	public @ResponseBody Iterable<ServiceStation> getServiceStationByName(@RequestBody String name) {
		return dbScenter.getServiceStationByName(name);
	}

	@RequestMapping(value = TEST_URL, method = RequestMethod.GET)
	Map<String, Object> refreshGamesList(@CookieValue(value = COOKIE_SESSION_NAME, defaultValue = "null") String sId,
			HttpServletResponse response) {
		response = setHeaders(response);
		try {
			return setResponse(dummy.getDummyResponse(ctx, dbController), null);
		} catch (Exception e) {
			return setResponse(null, e);
		}
	}

	private HttpServletResponse setHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		// response.addHeader("Access-Control-Allow-Credentials", "true");
		return response;
	}

	// private HttpServletResponse setCookie(HttpServletResponse response,
	// String name, String value, int expDate) {
	// Cookie cookie = new Cookie(name, value);
	// cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(expDate));
	// cookie.setPath("/");
	// response.addCookie(cookie);
	// return response;
	// }

	private LinkedHashMap<String, Object> setResponse(Object obj, Exception e) {
		LinkedHashMap<String, Object> res = new LinkedHashMap<>();
		if (e == null) {
			res.put("status", "SUCCESS");
			res.put("data", obj);
			res.put("message", null);
		} else {
			String message = e.getMessage();
			if (message == null)
				message = "General Server Error";
			res.put("status", "EXCEPTION");
			res.put("data", null);
			res.put("message", message);
			e.printStackTrace();
		}
		return res;
	}
}
