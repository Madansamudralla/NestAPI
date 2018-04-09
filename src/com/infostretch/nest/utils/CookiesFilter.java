package com.infostretch.nest.utils;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class CookiesFilter extends ClientFilter {

	public final static String COOKIES = "com.qaf.cookies";

	URI uri;

	@SuppressWarnings("unchecked")
	@Override
	public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
		this.uri = request.getURI();
		Set<Cookie> cookies = new HashSet<Cookie>();
		Object ck = ConfigurationManager.getBundle().getObject(COOKIES);
		if (ck != null) {
			if (ck instanceof Cookie)
				cookies.add((Cookie) ck);
			else cookies.addAll((Collection<? extends Cookie>) ck);
		}
		Set<Cookie> requestCookies = new HashSet<>(cookies);
		StringBuilder sb = new StringBuilder();
		for (Object cookie : requestCookies) {
			if (cookie instanceof Cookie) {
				Cookie newCookie = (Cookie) cookie;
				if (!isCookieExpired(newCookie)) {
					sb.append(newCookie.getName());
					sb.append("=");
					sb.append(newCookie.getValue());
					sb.append(";");
				} else {
					cookies.remove(newCookie);
				}
			} else {
				sb.append(String.valueOf(cookie));
				sb.append(";");
			}
		}
		String cookieString = sb.toString();
		request.getHeaders().putSingle("Cookie", cookieString);
		ClientResponse response = getNext().handle(request);
		List<NewCookie> responseCookies = response.getCookies();
		if (responseCookies != null) {
			cookies.addAll(responseCookies);
			ConfigurationManager.getBundle().setProperty(COOKIES, cookies);
		}
		return response;
	}

	public URI getUri() {
		return uri;
	}

	private boolean isCookieExpired(Cookie cookie) {
		return false;
	}

}
