package com.velsis.usuarios.rest.feign.client;

import java.util.List;

import com.velsis.usuarios.rest.feign.entity.Usuario;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 
 * UsuariosClient
 * 
 * Interface para fazer as chamadas aos serviços da API Usuarios
 * 
 */
@Headers({
  "Accept: application/json",
	"Content-Type: application/json"
})
public interface UsuariosClient {
    
    @RequestLine("GET /usuarios")
    public List<Usuario> listar();

    @RequestLine("POST /usuarios")
    public Usuario criar(Usuario usuario);

    @RequestLine("GET /usuarios/{cpf}")
    public Usuario recuperarPeloCpf(@Param ("cpf") String cpf);

    @RequestLine("DELETE /usuarios/{id}")
    public void excluir(@Param("id") Integer id);

    @RequestLine("PUT /usuarios")
    public Usuario atualizar(Usuario usuario);
}