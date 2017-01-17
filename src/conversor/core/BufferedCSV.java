package conversor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import conversor.exception.ConverterException;
import conversor.exception.ParametroInvalidoException;

public class BufferedCSV {

	
	private static final String CARACTER_PARA_SPLIT_DATA = "/";
	private static final String EXTENSAO = ".csv";
	protected static final String URL = "http://www4.bcb.gov.br/Download/fechamento/";

	
	public BufferedReader getBufferedReader(String quotation) throws ConverterException{
		try {
			URL url = new URL(construirUrl(quotation));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			validarResponse(connection);
		  	InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
		    return new BufferedReader(streamReader);
		  }catch(Exception ex){
			  throw new ConverterException(ex);
		  }
	}


	private void validarResponse(HttpURLConnection connection) throws IOException,ConverterException {
		if (connection.getResponseCode() != 200)
			throw new ConverterException("Não possui cotação para o dia solicitado!");
	}
	
	protected String construirUrl(String quotation) throws ParametroInvalidoException{
		return URL + getNomeArquivo(quotation) + EXTENSAO;
	}

	private String getNomeArquivo(String quotation) throws ParametroInvalidoException {
		parametroValido(quotation);
		String[] arrayData = quotation.split(CARACTER_PARA_SPLIT_DATA);
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
