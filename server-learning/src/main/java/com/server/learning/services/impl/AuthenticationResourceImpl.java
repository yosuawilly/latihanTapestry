package com.server.learning.services.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.tapestry5.ioc.annotations.Inject;
import com.learning.engine.BusinessException;
import com.learning.engine.json.PojoJsonMapper;
import com.learning.engine.model.Siswa;
import com.learning.engine.model.json.Result;
import com.learning.engine.service.SiswaService;
import com.server.learning.services.AuthenticationResource;

public class AuthenticationResourceImpl implements AuthenticationResource{
	
	@Inject
	private SiswaService siswaService;

	public String login(@Context HttpServletRequest context,
			@QueryParam("username") String username,
			@QueryParam("password") String password) {
		
		Siswa siswa = siswaService.getByUsername(username);
		
		if(siswa == null) {
			return PojoJsonMapper.toJson(new BusinessException("LS-1001"));
		}
		else {
			if(!password.equals(siswa.getPassword())) {
				return PojoJsonMapper.toJson(new BusinessException("LS-1002"));
			} else {
				Result<Siswa> result = new Result<Siswa>(true, "Login Sukses", siswa);
				
				return PojoJsonMapper.toJson(result);
			}
		}
	}

}
