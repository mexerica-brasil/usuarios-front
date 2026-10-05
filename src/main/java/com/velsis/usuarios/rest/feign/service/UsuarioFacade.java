package com.velsis.usuarios.rest.feign.service;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.velsis.usuarios.rest.feign.client.UsuariosClient;
import com.velsis.usuarios.rest.feign.util.FeignUtil;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class UsuarioFacade {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());

        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    private UsuarioFacade() {
        // nada
    }

    public static UsuariosClient getClient() {
		return Feign.builder().client(FeignUtil.buildOkHttpClient())
				                        .encoder(new JacksonEncoder(OBJECT_MAPPER))
				                        .decoder(new JacksonDecoder(OBJECT_MAPPER))
                                        .logger(new Slf4jLogger())
                                        .logLevel(Logger.Level.FULL)
				                        .options(new Request.Options(120, TimeUnit.SECONDS, 420, TimeUnit.SECONDS, true))
				                        .target(UsuariosClient.class, FeignUtil.getURLBASELPI());
	}
}