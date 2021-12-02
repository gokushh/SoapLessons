package br.com.caelum.estoque.modelo.usuario;

import javax.xml.ws.WebFault;
import java.util.Date;

@WebFault(name = "AutorizacaoFault")
public class AutorizacaoException extends Exception {

    public AutorizacaoException(String mensagem) {
        super(mensagem);
    }

    // Customiza a mensagem de excessão
    public InfoFault getFaultInfo() {
        return new InfoFault("Token invalido", new Date());
    }

}
