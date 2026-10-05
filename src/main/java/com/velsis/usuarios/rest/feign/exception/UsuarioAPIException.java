package com.velsis.usuarios.rest.feign.exception;

public class UsuarioAPIException extends RuntimeException {

    private final Problem problem;

    public UsuarioAPIException(Problem problem) {
		super(problem.getDetail());
		this.problem = problem;
	}

    public Problem getProblem() {
		return problem;
	}
}