package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name="AutenticacaoFault")
public class AutenticationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2968005669197306190L;

	public AutenticationException() {
	}

	public AutenticationException(String msg) {
		super(msg);
	}
	
	public InfoFault getFaultInfo(){
		return new InfoFault("Token inválido",new Date());
	}
}
