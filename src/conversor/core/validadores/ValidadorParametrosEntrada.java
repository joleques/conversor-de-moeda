package conversor.core.validadores;

import java.math.BigDecimal;

import conversor.exception.ConverterException;
import conversor.exception.ParametroInvalidoException;

public class ValidadorParametrosEntrada implements IValidador{

	private String from;
	private String to;
	private Number value;
	private String quotation;

	@Override
	public void validar() throws ConverterException {
		if(this.from == null)
			excecaoParametroInvalido("from esta nulo.");
		if(this.to == null)
			excecaoParametroInvalido("to esta nulo.");
		if(this.value == null || (new BigDecimal(value.toString()).compareTo(new BigDecimal("0")) == -1))
			excecaoParametroInvalido("value invalido.");
		if(this.quotation == null)
			excecaoParametroInvalido("quotation esta nulo.");
	}

	

	public ValidadorParametrosEntrada(String from, String to, Number value, String quotation) {
		super();
		this.from = from;
		this.to = to;
		this.value = value;
		this.quotation = quotation;
	}



	private void excecaoParametroInvalido(String message) throws ParametroInvalidoException {
		throw new ParametroInvalidoException(message);
	}
}
