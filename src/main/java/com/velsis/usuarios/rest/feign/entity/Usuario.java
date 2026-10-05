package com.velsis.usuarios.rest.feign.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.velsis.usuarios.rest.feign.constants.Constants;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;


public class Usuario {

    private Integer id;

    @CPF 
    @NotNull 
    @NotBlank 
    //@Size (min = 14, max = 14)
    private String cpf;

    @NotNull
    @NotBlank 
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull
    @PastOrPresent 
    private LocalDate dataNascimento;

    @Valid 
    private Endereco endereco = new Endereco();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    @JsonIgnore 
    public String getDataNascimentoFormatada() {
        if (dataNascimento == null) {
            return "";
        }

        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
	public String toString() {
		return this.id.toString().concat(Constants.CARACTERESPACO).concat(this.nome);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Usuario)) {
			return false;
		}
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}