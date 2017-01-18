package conversor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import conversor.core.calculadora.Calculadora;
import conversor.core.calculadora.FabricaCalculadora;
import conversor.exception.ConverterException;
import conversor.exception.ParametroInvalidoException;

public class Converter {

	private Validador validador;
	private FabricaCalculadora fabrica;
	private static final String CARACTER_PARA_SPLIT = ";";

	
	
	public Converter() {
		super();
		validador = new Validador();
		fabrica = new FabricaCalculadora();
	}

	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation)  throws ConverterException{
		validador. validarParametrosEntrada(from, to, value, quotation);
		BigDecimal resultado = null;
		try {
			if(ehConversaoParaReal(from, to)){
				Calculadora calculadora = fabrica.fabricar(from, to);
				resultado = calculadora.calcular(value.toString(), read(from, quotation), read(to, quotation));
			}else{
				String[] infoFrom = read(from, quotation);
				Calculadora calculadora = fabrica.fabricar(infoFrom[2]);
				resultado = calculadora.calcular(value.toString(), infoFrom,  read(to, quotation));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//tratar exceção de formato
		
		return resultado.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	private boolean ehConversaoParaReal(String from, String to){
		return from.equalsIgnoreCase("BRL") || to.equalsIgnoreCase("BRL");
	}

	public String[] read(String from,String quotation) throws ConverterException, IOException{
		String[] resultado = null;
	    BufferedCSV bufferedCSV = new BufferedCSV();
	    BufferedReader br = bufferedCSV.getBufferedReader(quotation);
	    String linha = "";
	    while ((linha = br.readLine()) != null) {
	        String[] arrayLinha = linha.split(CARACTER_PARA_SPLIT);
	    	if(from.equalsIgnoreCase(arrayLinha[3])){
	    		resultado = arrayLinha;
	    		break;
	    	}
	    }
	    return resultado;
	}
}
