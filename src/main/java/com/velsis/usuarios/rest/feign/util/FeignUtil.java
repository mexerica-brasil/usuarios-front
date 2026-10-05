package com.velsis.usuarios.rest.feign.util;

import com.velsis.usuarios.client.bean.BeanApplication;

import feign.okhttp.OkHttpClient;

public class FeignUtil {
	
	private FeignUtil() {
		// nada
	}
	
	public static OkHttpClient buildOkHttpClient() {
		return buildOkHttpClientWithOutProxy();
	}

	private static OkHttpClient buildOkHttpClientWithOutProxy() {
		return new OkHttpClient();
	}
	 
	public static String getURLBASELPI() {
		return BeanApplication.getInstanciaBean().getPropriedade("url.base.api.usuarios");
	}
}