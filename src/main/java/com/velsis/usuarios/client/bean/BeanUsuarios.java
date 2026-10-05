package com.velsis.usuarios.client.bean;

import java.io.Serializable;
import java.util.List;

import org.primefaces.PrimeFaces;

import com.velsis.usuarios.rest.feign.entity.Usuario;
import com.velsis.usuarios.rest.feign.exception.UsuarioAPIException;
import com.velsis.usuarios.rest.feign.service.UsuarioFacade;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * 
 * BeanUsuarios
 * 
 * Controller da página de gerenciamento de usuários
 * 
 * 
 */
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
        try {
            if (this.selectedUsuario.getId() == null) {
                this.selectedUsuario = UsuarioFacade.getClient().criar(this.selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(BeanLabel.getInstanciaBean().getPropriedadeComArgumentos(
                                                                                        "usuario.adicionado.sucesso", 
                                                                                                this.selectedUsuario.getNome())));
                this.usuarios.add(this.selectedUsuario);
                PrimeFaces.current().executeScript("PF('varDialogUsuario').hide()");
            } else {
                UsuarioFacade.getClient().atualizar(this.selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(BeanLabel.getInstanciaBean().getPropriedadeComArgumentos(
                                                                                        "usuario.alterado.sucesso", 
                                                                                                this.selectedUsuario.getNome())));
                 PrimeFaces.current().executeScript("PF('varDialogUsuario').hide()");                                                                                
            }    
        } catch (UsuarioAPIException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getProblem().getUserMessage(), e.getProblem().getDetail()));
        } 
        
        PrimeFaces.current().ajax().update("form:globalMessage", "form:dtUsuarios");
    }

    public void excluir() {
        try {
            if (this.selectedUsuario.getId() != null) {
                UsuarioFacade.getClient().excluir(this.selectedUsuario.getId());
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(BeanLabel.getInstanciaBean().getPropriedadeComArgumentos(
                                                                                        "usuario.excluido.sucesso", 
                                                                                                this.selectedUsuario.getNome())));
                this.getUsuarios().remove(this.selectedUsuario);
                this.selectedUsuario = null;
                PrimeFaces.current().executeScript("PF('varDialogUsuario').hide()");
            } 
        } catch (UsuarioAPIException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getProblem().getUserMessage(), e.getProblem().getDetail()));
        } 
        
        PrimeFaces.current().ajax().update("form:globalMessage", "form:dtUsuarios");
    }
        
}