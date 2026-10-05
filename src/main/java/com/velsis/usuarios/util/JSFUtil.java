package com.velsis.usuarios.util;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;


public final class JSFUtil {

	private JSFUtil() {
	}

	public static FacesContext getCurrentInstance() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getCurrentInstance().getExternalContext();
	}

	public static <T> T getValueExpression(String expression, Class<T> t) {
		return (T) getCurrentInstance().getApplication().evaluateExpressionGet(getCurrentInstance(), expression, t);
	}
}