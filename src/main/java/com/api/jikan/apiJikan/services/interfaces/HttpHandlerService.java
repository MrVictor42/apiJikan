package com.api.jikan.apiJikan.services.interfaces;

import java.io.InputStream;

public interface HttpHandlerService {
	
	public String makeServiceCall(String reqUrl);
	public String convertStreamToString(InputStream is);
}