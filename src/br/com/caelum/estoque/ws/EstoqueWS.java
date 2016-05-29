package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutenticationException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {
	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	@ResponseWrapper(localName="itens")
	@WebResult(name="item")
	public List<Item> getItens(@WebParam(name="filtros")Filtros filtros) {
		System.out.println("Chamando getItens()");
        List<Filtro> lista = filtros != null ? filtros.getLista() : null;
        List<Item> itensResultado = dao.todosItens(lista);
        return itensResultado;
	}
	
	@WebMethod(operationName="cadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario token,
			@WebParam(name = "item") Item item) throws AutenticationException {
		boolean ehValido = new TokenDao().ehValido(token);
		
		if (!ehValido){
			throw new AutenticationException("Autenticacao falhou");
		}
		
		new ItemValidador(item).validate();
		
		dao.cadastrar(item);
		return item;
	}
}
