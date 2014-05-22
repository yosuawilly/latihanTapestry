package com.server.learning.services;

public interface Authenticator {
	boolean login(String username, String password);
	boolean isLoggedIn();
	void logout();
}
