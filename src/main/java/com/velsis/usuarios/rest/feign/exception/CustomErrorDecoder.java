package com.velsis.usuarios.rest.feign.exception;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * 
 * CustomErrorDecoder
 * 
 * Interceptar as exceções da API e dipara uma exceção para a aplicação
 * 
 * 
 */
public class CustomErrorDecoder implements ErrorDecoder {

	private final ObjectMapper mapper;

    public CustomErrorDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

	@Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream responseBodyIs = response.body().asInputStream()) {
			String erro = new String(
                responseBodyIs.readAllBytes(),
                StandardCharsets.UTF_8
            );

        	mapper.registerModule(new JavaTimeModule());
            Problem exceptionMessage = mapper.readValue(erro, Problem.class);

            return new UsuarioAPIException(exceptionMessage);
        } catch (IOException _) {
            return FeignException.errorStatus(methodKey, response);
		}
    }
}