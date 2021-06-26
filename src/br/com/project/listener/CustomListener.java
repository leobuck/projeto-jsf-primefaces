package br.com.project.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import br.com.frameworks.utils.UtilFramework;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.InformacaoRevisao;

@Service
public class CustomListener implements RevisionListener, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisaoEntidade) {
		InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisaoEntidade;
		Long codUsuario = UtilFramework.getThreadLocal().get();
		
		Entidade entidade = new Entidade();
		
		if (codUsuario != null && codUsuario != 0L) {
			entidade.setEntCodigo(codUsuario);
			informacaoRevisao.setEntidade(entidade);
		}
	}

}
