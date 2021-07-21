package br.com.project.carregamento.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.framework.controller.crud.Controller;
import br.com.project.listener.ContextLoaderListenerUtils;

public class CarregamentoLazyListForObject<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private List<T> list = new ArrayList<>();

	private int totalRegistrosConsulta = 0;

	private String query = "";

	private Controller controller = (Controller) ContextLoaderListenerUtils.getBean(Controller.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

		try {
			if (query != null && !query.isEmpty()) {
				list = (List<T>) controller.findListByQueryDinamica(query, first, pageSize);

				if (totalRegistrosConsulta == 0) {
					setRowCount(0);
				} else {
					setRowCount(totalRegistrosConsulta);
				}
			}

			setPageSize(pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (List<T>) list;
	}

	public void setTotalRegistrosConsulta(int totalRegistrosConsulta, String queryDeBuscaConsulta) {
		this.query = queryDeBuscaConsulta;
		this.totalRegistrosConsulta = totalRegistrosConsulta;
	}

	public List<T> getList() {
		return list;
	}

	public void clean() {
		this.query = "";
		this.totalRegistrosConsulta = 0;
		this.list.clear();
	}

	public void remove(T objetoSelecionado) {
		this.list.remove(objetoSelecionado);
	}

	public void add(T objetoSelecionado) {
		this.list.add(objetoSelecionado);
	}

	public void addAll(List<T> collection) {
		this.list.addAll(collection);
	}
}
