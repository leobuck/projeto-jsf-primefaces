package br.com.project.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Estado;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			return (Estado) HibernateUtil.getCurrentSession().get(Estado.class, Long.parseLong(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null) {
			Estado e = (Estado) objeto;
			return e.getEstId() != null && e.getEstId() > 0 ? e.getEstId().toString() : null;
		}
		return null;
	}

}
