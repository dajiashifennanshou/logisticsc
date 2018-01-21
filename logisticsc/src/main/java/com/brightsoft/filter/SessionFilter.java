package com.brightsoft.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.brightsoft.model.SysUser;




public class SessionFilter extends OncePerRequestFilter {

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Object object = httpSession.getAttribute("user");
		SysUser user= null;
		if(object!=null){
			user = (SysUser) httpSession.getAttribute("user");
		}
		
		filterChain.doFilter(request, response); 
			
	}

}
