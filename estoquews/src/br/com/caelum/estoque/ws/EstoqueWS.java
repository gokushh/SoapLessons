package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.item.*;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;
import br.com.caelum.estoque.modelo.usuario.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    // Método simples de GET
//    @WebMethod(operationName = "todosOsItens")
//    @RequestWrapper(localName="listaItens")
//    @WebResult(name = "item")
//    @ResponseWrapper(localName = "itens")
//    public List<Item> getItens() {
//        System.out.println("Chamando getItens()");
//        return dao.todosItens();
//    }


    // Segundo método de GET com Wrappers, utilizando filtros de busca.
    // No SoapUI (client), podemos utilizar mais de um filtro
    // A tarefa da classe ListaItens é "embrulhar" (wrapper) os itens para que haja uma melhor visualização no XML
    // A classe Filtros também servirá como um "wrapper" para embrulhar um filtro. O @WebParam faz essa função neste caso.
    @WebMethod(operationName = " o")
    @ResponseWrapper(localName = "itens")
    @WebResult(name = "itens")
    public ListaItens getItensComFiltro(@WebParam(name="filtros") Filtros filtros) {
        System.out.println("Chamando getItens()");
        List<Filtro> lista = filtros.getLista();
        List<Item> itensResultado = dao.todosItens(lista);
        return new ListaItens(itensResultado);
    }

    // Método POST
    @WebMethod(operationName = "cadastrarItem")
    @WebResult(name = "item")
    public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario token, @WebParam(name = "item") Item item) throws AutorizacaoException {

        System.out.println("Cadastrando item " + item + " Token: " + token);

        boolean valido = new TokenDao().ehValido(token);

        // Exemplo de excessão
        if(!valido) {
            throw new AutorizacaoException("Token inválido");
        }

        new ItemValidador(item).validate();

        this.dao.cadastrar(item);

        return item;
    }
}