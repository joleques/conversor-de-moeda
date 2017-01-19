package conversor.core;

import java.math.BigDecimal;

import conversor.exception.ParametroInvalidoException;

public class Validador {


	public void validarParametrosEntrada(String from, String to, Number value, String quotation) throws ParametroInvalidoException {
		if(from == null)
			excecaoParametroInvalido("from esta nulo.");
		if(to == null)
			excecaoParametroInvalido("to esta nulo.");
		if(value == null || (new BigDecimal(value.toString()).compareTo(new BigDecimal("0")) == -1))
			excecaoParametroInvalido("value invalido.");
		if(quotation == null)
			excecaoParametroInvalido("quotation esta nulo.");
		
	}

	private void excecaoParametroInvalido(String message) throws ParametroInvalidoException {
		throw new ParametroInvalidoException(message);
	}
}
