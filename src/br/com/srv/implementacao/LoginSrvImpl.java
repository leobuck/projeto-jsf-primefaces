package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.LoginRepository;
import br.com.srv.interfaces.LoginSrv;

@Service
public class LoginSrvImpl implements LoginSrv {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		return loginRepository.autentico(login, senha);
	}

}
