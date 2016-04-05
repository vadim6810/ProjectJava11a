package com.domain.interfaces;

import java.util.Map;

import org.springframework.context.ApplicationContext;

public interface IDummy {
	Map <String, String> getDummyResponse(ApplicationContext ctx, IPersistenceController dbController);
}
