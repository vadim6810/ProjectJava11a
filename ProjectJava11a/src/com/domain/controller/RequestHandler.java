package com.domain.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.interfaces.IDummy;
import com.domain.interfaces.IPersistenceController;

@RestController
public class RequestHandler {
private static final String COOKIE_SESSION_NAME = "sId";
private static final String TEST_URL = "test/test";

@Autowired
ApplicationContext ctx;
@Autowired
IPersistenceController dbController;
@Autowired
IDummy dummy;

@RequestMapping(value=TEST_URL, method=RequestMethod.GET)
Map<String, Object> refreshGamesList(@CookieValue(value = COOKIE_SESSION_NAME, defaultValue = "null") String sId, HttpServletResponse response){
    response = setHeaders(response);
	try {
		return setResponse(dummy.getDummyResponse(ctx, dbController), null);
	} catch (Exception e) {
		return setResponse(null, e);}
}

private HttpServletResponse setHeaders(HttpServletResponse response) {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
    response.addHeader("Access-Control-Max-Age", "1728000");
//   response.addHeader("Access-Control-Allow-Credentials", "true");
	return response;
}

//private HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int expDate) {
//	Cookie cookie = new Cookie(name, value);
//	cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(expDate));
//	cookie.setPath("/");
//	response.addCookie(cookie);
//	return response;
//}

private LinkedHashMap<String, Object> setResponse(Object obj, Exception e){
	LinkedHashMap<String, Object> res=new LinkedHashMap<>();
	if(e == null){
		res.put("status", "SUCCESS");
		res.put("data", obj);
		res.put("message", null);
	}else{
		String message = e.getMessage();
		if(message == null)
			message = "General Server Error";
		res.put("status","EXCEPTION");
		res.put("data", null);
		res.put("message",message);
		e.printStackTrace();
	}
	return res;
}
}
