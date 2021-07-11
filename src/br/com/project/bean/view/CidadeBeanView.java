package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CidadeController cidadeController;

	private Cidade objetoSelecionado = new Cidade();
	
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	
	private List<Cidade> list = new ArrayList<>();

	@Override
	public String save() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}

	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Cidade();
		return url;
	}
	
	@Override
	public String editar() throws Exception {
		
		return url;
	}
	
	@Override
	public void excluir() throws Exception {
		cidadeController.delete(objetoSelecionado);
		novo();
	}

	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public List<Cidade> getList() throws Exception {
		list = new CidadeController().findList(Cidade.class);
		return list;
	}
}
