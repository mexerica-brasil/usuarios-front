package com.velsis.usuarios.client.bean;

import java.io.Serializable;
import java.util.List;

import org.primefaces.PrimeFaces;

import com.velsis.usuarios.rest.feign.entity.Usuario;
import com.velsis.usuarios.rest.feign.service.UsuarioFacade;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named 
@ViewScoped 
public class BeanUsuarios implements Serializable {
    
    private List<Usuario> usuarios;
    private Usuario selectedUsuario;
    
	public void preRenderView() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.usuarios = UsuarioFacade.getClient().listar();
		}
	}

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public void incluir() {
        this.selectedUsuario = new Usuario();
    }

    public void salvar() {
        if (this.selectedUsuario.getId() == null) {
            this.usuarios.add(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário adicionado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário atualizado"));
        }
        PrimeFaces.current().executeScript("PF('varDialogUsuario').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
    }
        
}