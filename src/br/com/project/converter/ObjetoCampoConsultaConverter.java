package br.com.project.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.project.bean.geral.ObjetoCampoConsulta;

@FacesConverter(forClass = ObjetoCampoConsulta.class)
public class ObjetoCampoConsultaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
			String[] vals = value.split("\\*");
			ObjetoCampoConsulta objCampoConsulta = new ObjetoCampoConsulta();
			objCampoConsulta.setCampoBanco(vals[0]);
			objCampoConsulta.setTipoClass(vals[1]);
			return objCampoConsulta;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			ObjetoCampoConsulta obj = (ObjetoCampoConsulta) value;
			return obj.getCampoBanco() + "*" + obj.getTipoClass();
		}
		
		return null;
	}

}
