package br.com.project.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Entidade;

@FacesConverter(forClass = Entidade.class, value = "entidadeConverter")
public class EntidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			return (Entidade) HibernateUtil.getCurrentSession().get(Entidade.class, Long.parseLong(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null) {
			Entidade e = (Entidade) objeto;
			return e.getEntCodigo() != null && e.getEntCodigo() > 0 ? e.getEntCodigo().toString() : null;
		}
		return null;
	}

}
