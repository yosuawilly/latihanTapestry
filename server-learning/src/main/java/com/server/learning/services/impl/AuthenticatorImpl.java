package com.server.learning.services.impl;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

import com.learning.engine.model.Siswa;
import com.learning.engine.service.SiswaService;
import com.server.learning.services.Authenticator;

public class AuthenticatorImpl implements Authenticator{
	
	public static final String AUTH_TOKEN = "authToken";
	
	@Inject
	private Request request;
	
	@Inject
	SiswaService siswaService;

	@Override
	public boolean login(String username, String password) {
		Siswa siswa = siswaService.getByUsername(username);
		if(siswa != null) {
			if(siswa.getPassword().equals(password)) {
				Session session = request.getSession(true);
				session.setAttribute(AUTH_TOKEN, siswa);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLoggedIn() {
		Session session = request.getSession(false);
		if(session != null) {
			return session.getAttribute(AUTH_TOKEN) != null;
		}
		return false;
	}

	@Override
	public void logout() {
		Session session = request.getSession(false);
		if(session != null){
			session.setAttribute(AUTH_TOKEN, null);
			session.invalidate();
		}
	}

}
