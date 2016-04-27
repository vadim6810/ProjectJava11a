package com.domain.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.domain.interfaces.IDummy;
import com.domain.interfaces.IPersistenceController;

public class Dummy implements IDummy{

	public Map<String, String> getDummyResponse(ApplicationContext ctx, IPersistenceController dbController) {
		Map <String, String> response = new HashMap<>();
		response.put("Key1", "Value1");
		response.put("Key2", "Value2");
		response.put("Key3", "Value3");
		return response;
	}
}
