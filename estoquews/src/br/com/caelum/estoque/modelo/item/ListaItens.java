package br.com.caelum.estoque.modelo.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// A tarefa da classe ListaItens é "embrulhar" os itens para que haja uma melhor visualização no XML
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaItens {

	@XmlElement(name = "item")
	private List<Item> itens;

	public ListaItens(List<Item> itens) {
		this.itens = itens;
	}

	// O JAX-B precisa do construtor sem argumentos
	ListaItens() {
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
}
