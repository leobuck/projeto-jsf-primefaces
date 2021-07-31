package br.com.project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEntUltimoAcesso();
	}

	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeLogada.getEntSenha())) {
			addMsg("Senha atual digitada inválida!");
			return;
		} else if (entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("Nova senha não pode ser igual a senha atual!");
			return;
		} else if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("Nova senha digitada diferente da confirmação!");
			return;
		} else {
			entidadeLogada.setEntSenha(entidadeAtualizaSenhaBean.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findById(Entidade.class, entidadeLogada.getEntCodigo());
			if (entidadeLogada.getEntSenha().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("Nova senha não atualizada!");
				erro();
			}
		}
	}
	
	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	public void setEntidadeAtualizaSenhaBean(EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}
	
}
