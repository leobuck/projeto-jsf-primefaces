package br.com.srv.implementacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.EntidadeRepository;
import br.com.srv.interfaces.EntidadeSrv;

@Service
public class EntidadeSrvImpl implements EntidadeSrv {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntidadeRepository entidadeRepository;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return entidadeRepository.getUltimoAcessoEntidadeLogada(login);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		entidadeRepository.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String login) {
		return entidadeRepository.existeUsuario(login);
	}

}
