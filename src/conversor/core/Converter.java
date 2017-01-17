package conversor.core;

import java.math.BigDecimal;

import conversor.exception.ParametroInvalidoException;

public class Converter {

	
	private static final String CARACTER_PARA_SPLIT = "/";
	private static final String EXTENSAO = ".csv";
	protected static final String URL = "http://www4.bcb.gov.br/Download/fechamento/";

	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation){
		return null;
	}
	
	protected String construirUrl(String quotation) throws ParametroInvalidoException{
		return URL + getNomeArquivo(quotation) + EXTENSAO;
	}

	private String getNomeArquivo(String quotation) throws ParametroInvalidoException {
		parametroValido(quotation);
		String[] arrayData = quotation.split(CARACTER_PARA_SPLIT);
		dataValida(arrayData);
		return arrayData[2] + arrayData [1] + arrayData[0];
	}

	private void parametroValido(String quotation) throws ParametroInvalidoException {
		if(quotation == null )
			excecaoParametroInvalido();
	}

	private void excecaoParametroInvalido() throws ParametroInvalidoException {
		throw new ParametroInvalidoException("Data para conversão invalida!");
	}

	private void dataValida(String[] arrayData)	throws ParametroInvalidoException {
		if(arrayData == null || arrayData.length != 3 )
			excecaoParametroInvalido();
	}
	
}
