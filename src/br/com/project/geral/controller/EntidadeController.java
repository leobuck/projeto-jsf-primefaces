package br.com.project.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.EntidadeSrv;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntidadeSrv entidadeSrv;

	public void updateUltimoAcessoUser(String login) {
		entidadeSrv.updateUltimoAcessoUser(login);
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return entidadeSrv.getUltimoAcessoEntidadeLogada(login);
	}
	
	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findUniqueByProperty(Entidade.class, userLogado, "entLogin", " AND entity.entInativo IS FALSE");
	}

	public boolean existeCpf(String cpf) throws Exception {
		return super.findListByQueryDinamica("FROM Entidade WHERE cpf = '" + cpf + "'").size() > 0;
	}

}
