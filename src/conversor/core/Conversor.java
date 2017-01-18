package conversor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import conversor.core.calculadora.Calculadora;
import conversor.core.calculadora.FabricaCalculadora;
import conversor.exception.ConverterException;
import conversor.exception.ParametroInvalidoException;

public class Conversor {

	private Validador validador;
	private FabricaCalculadora fabrica;
	private BufferedReader dados;
	
	private static final String BRL = "BRL";
	private static final String FORMATO_DATA = "dd/MM/yyyy";
	private static final String CARACTER_PARA_SPLIT = ";";
	
	public Conversor() {
		super();
		validador = new Validador();
		fabrica = new FabricaCalculadora();
	}

	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation)  throws ConverterException{
		BigDecimal resultado = null;
		try {
			validador.validarParametrosEntrada(from, to, value, quotation);
			String nomeArquivo = tratarDiasUteis(quotation);
			String[] cotacaoFrom = buscarCotacao(from, nomeArquivo);
			Calculadora calculadora = getCalculadora(from, to, cotacaoFrom);
			resultado =  calculadora.calcular(value.toString(), cotacaoFrom,  buscarCotacao(to, nomeArquivo));
		} catch (Exception e) {
			throw new ConverterException(e.getMessage());
		}
		return resultado.setScale(2, RoundingMode.HALF_EVEN);
	}

	private Calculadora getCalculadora(String from, String to, String[] infoFrom) throws ConverterException {
		if(ehConversaoParaReal(from, to))
			return fabrica.fabricar(from, to);
		return fabrica.fabricar(infoFrom[2]);
	}

	private boolean ehConversaoParaReal(String from, String to){
		return from.equalsIgnoreCase(BRL) || to.equalsIgnoreCase(BRL);
	}
	
	protected String tratarDiasUteis(String quotation) throws ParseException {
		Calendar data = Calendar.getInstance();
        SimpleDateFormat simpleFormat = new SimpleDateFormat(FORMATO_DATA);
        data.setTime(simpleFormat.parse(quotation));
        if(data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            data.add(Calendar.DATE, -2);
        }else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            data.add(Calendar.DATE, -1);
        }
		return simpleFormat.format(data.getTime());
	}

	public String[] buscarCotacao(String from,String quotation) throws ConverterException, IOException{
		if(from.equals(BRL))
			return null;
		
		String[] resultado = null;
	    buscarDadosCotacao(quotation);
	    String linha = "";
	    while ((linha = dados.readLine()) != null) {
	        String[] arrayLinha = linha.split(CARACTER_PARA_SPLIT);
	    	if(from.equalsIgnoreCase(arrayLinha[3])){
	    		resultado = arrayLinha;
	    		break;
	    	}
	    }
	    if(resultado == null)
	    	throw new ConverterException("Data invalida");
	    return resultado;
	}

	private void buscarDadosCotacao(String quotation) throws ConverterException, IOException {
		//if(dados == null){
			CotacaoDAO dao = new CotacaoDAO();
			dados =  dao.buscarDadosCotacao(quotation);
		//}else{
		//	dados.reset();
		//}
	}
}
