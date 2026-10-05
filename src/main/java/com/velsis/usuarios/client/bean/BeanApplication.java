package com.velsis.usuarios.client.bean;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.velsis.usuarios.util.JSFUtil;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named 
@ApplicationScoped 
public class BeanApplication {

    private ResourceBundle resourceBundle;
	private static final Logger log = LoggerFactory.getLogger(BeanApplication.class);
	private static final String RESOURCE_FILE = "application";
	private static final Locale LOCALE = Locale.of("pt", "BR");

	@PostConstruct 
	public void init() {
		try {
			resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE, LOCALE);
			log.info("Arquivo de propriedades carregado com sucesso: {}", RESOURCE_FILE);
		} catch (MissingResourceException e) {
			log.error("Erro ao carregar o arquivo de propriedades: {}", RESOURCE_FILE, e);
		}
	}

	public String getPropriedade(String chave) {
		return getMensagem(chave);
	}

	public String getPropriedadeComArgumentos(String chave, Object... args) {
		return getMensagem(chave, args);
	}

	private String getMensagem(String chave, Object... args) {
		if (chave == null || chave.isBlank()) { 
			log.warn( "Chave da mensagem não informada." ); 
			return "Mensagem não cadastrada"; 
		}
		
		if (resourceBundle == null) { 
			log.error( "ResourceBundle não foi inicializado: {}", RESOURCE_FILE ); 
			return "Mensagem não cadastrada: " + chave; 
		} 
		
		try {
			String conteudo = resourceBundle.getString(chave); 
			
			if (args != null && args.length > 0) { 
				return MessageFormat.format( conteudo, args ); 
			} 
			
			return conteudo; 
		} catch (MissingResourceException e) { 
			String mensagemNaoCadastrada = "Mensagem não cadastrada: " + chave; 
			log.warn( "Mensagem não cadastrada: {}", chave ); 
			return mensagemNaoCadastrada; 
		} catch (IllegalArgumentException e) { 
			String mensagemInvalida = "Mensagem inválida: " + chave; 
			log.error( "Erro ao formatar mensagem: {}", chave, e ); 
			return mensagemInvalida; 
		}
	}

	public static BeanApplication getInstanciaBean() {
		return JSFUtil.getValueExpression("#{beanApplication}", BeanApplication.class);
	}   
}