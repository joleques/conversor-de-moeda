package conversor.core;

import conversor.exception.ParametroInvalidoException;

public class Validador {


	public void validarParametrosEntrada(String from, String to, Number value, String quotation) throws ParametroInvalidoException {
		if(from == null)
			excecaoParametroInvalido("from esta nulo.");
		if(to == null)
			excecaoParametroInvalido("to esta nulo.");
		if(value == null || value.equals(0))
			excecaoParametroInvalido("value invalido.");
		if(quotation == null)
			excecaoParametroInvalido("quotation esta nulo.");
		if(from.equalsIgnoreCase(to))
			excecaoParametroInvalido("Moedas não podem ser iguais.");
		
	}

	private void excecaoParametroInvalido(String message) throws ParametroInvalidoException {
		throw new ParametroInvalidoException(message);
	}
}
