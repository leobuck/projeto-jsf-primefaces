package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.CidadeRepository;
import br.com.srv.interfaces.CidadeSrv;

@Service
public class CidadeSrvImpl implements CidadeSrv {

	private static final long serialVersionUID = 1L;

	@Resource
	private CidadeRepository cidadeRepository;
}
