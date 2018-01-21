package com.brightsoft.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet Filter implementation class SSOAuth
 */
public class SSOAuth implements Filter {
	
	private String ssoService;
	
	private String cookieName;
	 
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		ssoService = fConfig.getInitParameter("SSOService");
		cookieName = fConfig.getInitParameter("cookieName");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getContextPath();
		String gotoURL = request.getParameter("gotoURL");
		if(gotoURL == null)
			gotoURL = request.getRequestURL().toString();
		String URL = ssoService + "?action=preLogin&setCookieURL=" + request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/setCookie&gotoURL=" + gotoURL;
		
		Cookie ticket = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					ticket = cookie;
					break;
				}
			}
		if(request.getRequestURI().equals(path + "/logout"))
			doLogout(request, response, chain, ticket, URL);
		else if(request.getRequestURI().equals(path + "/setCookie"))
			setCookie(request, response);
		else if(ticket != null)
			authCookie(request, response, chain, ticket, URL);
		else
			response.sendRedirect(URL);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	private void setCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie ticket = new Cookie(cookieName, request.getParameter("ticket"));
		ticket.setPath("/");
		ticket.setMaxAge(Integer.parseInt(request.getParameter("expiry")));
		response.addCookie(ticket);
		
		String gotoURL = request.getParameter("gotoURL");
		if(gotoURL != null)
			response.sendRedirect(gotoURL);
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Cookie ticket, String URL) throws IOException, ServletException {
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair("action", "logout");
		params[1] = new NameValuePair("cookieName", ticket.getValue());
		try {
			post(request, response, chain, params);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		} finally {
			response.sendRedirect(URL);
		}
	}

	private void authCookie(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Cookie ticket, String URL) throws IOException, ServletException {
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair("action", "authTicket");
		params[1] = new NameValuePair("cookieName", ticket.getValue());
		try {
			JSONObject result = post(request, response, chain, params);
			if(result.getBoolean("error")) {
				response.sendRedirect(URL);
			} else {
				request.setAttribute("username", result.getString("username"));
				chain.doFilter(request, response);
			}
		} catch (JSONException e) {
			response.sendRedirect(URL);
			throw new RuntimeException(e);
		}
	}
	
	private JSONObject post(HttpServletRequest request, HttpServletResponse response, FilterChain chain, NameValuePair[] params) throws IOException, ServletException, JSONException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(ssoService);
		postMethod.addParameters(params);
		switch(httpClient.executeMethod(postMethod)) {
			case HttpStatus.SC_OK:
				return new JSONObject(postMethod.getResponseBodyAsString());
			default:
				// 其它处理
				return null;
		}
	}

}
