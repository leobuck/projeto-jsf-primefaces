package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.CidadeRepository;
import br.com.srv.interfaces.CidadeSrv;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CidadeSrv cidadeSrv;
	
	@Resource
	private CidadeRepository cidadeRepository;
	

}
