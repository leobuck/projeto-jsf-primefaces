package br.com.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao {

	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
	}

	@Override
	public void saveEdit() throws Exception {
	}

	@Override
	public void excluir() throws Exception {
	}

	@Override
	public String ativar() throws Exception {
		return null;
	}

	@Override
	public String novo() throws Exception {
		return null;
	}

	@Override
	public String editar() throws Exception {
		return null;
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
	}

	@Override
	public void consultarEntidade() throws Exception {
	}

	@Override
	public void statusOperation(StatusPersistencia status) throws Exception {
		Mensagens.responseOperation(status);
	}

	@Override
	public String redirecionarNovaEntidade() throws Exception {
		return null;
	}

	@Override
	public String redirecionarBuscarEntidade() throws Exception {
		return null;
	}

	@Override
	public void addMsg(String msg) {
		Mensagens.msg(msg);
	}

	protected void sucesso() throws Exception {
		statusOperation(StatusPersistencia.SUCESSO);
	}
	
	protected void erro() throws Exception {
		statusOperation(StatusPersistencia.ERRO);
	}
}
