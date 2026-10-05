package com.velsis.usuarios.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter ("cpfConverter")
public class CpfConverter implements Converter<String> {

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() != 11) {
            return value;
        }
        return value.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})","$1.$2.$3-$4");
    }

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("\\D", "");
    }
}