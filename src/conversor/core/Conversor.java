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
import conversor.core.validadores.ValidadorParametrosEntrada;
import conversor.exception.ConverterException;
import conversor.exception.ParametroInvalidoException;

public class Conversor {

	private ValidadorParametrosEntrada validador;
	private FabricaCalculadora fabrica;
	private BufferedReader dados;
	
	private static final String BRL = "BRL";
	private static final String USD = "USD";
	private static final String FORMATO_DATA = "dd/MM/yyyy";
	private static final String CARACTER_PARA_SPLIT = ";";
	
	public Conversor() {
		super();
		fabrica = new FabricaCalculadora();
	}

	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation)  throws ConverterException{
		BigDecimal resultado = null;
		if (ehConversaoParaMesmaMoeda(from,to))
			return new BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_UP);
		try {
			validador = new ValidadorParametrosEntrada(from, to, value, quotation);
			validador.validar();
			String nomeArquivo = tratarDiasUteis(quotation);
			String[] cotacaoFrom = buscarCotacao(from, nomeArquivo);
			String[] cotacaoTo = buscarCotacao(to, nomeArquivo);
			Calculadora calculadora = getCalculadora(cotacaoFrom, cotacaoTo);
			resultado =  calculadora.calcular(value.toString(),cotacaoFrom,cotacaoTo);
		} catch (Exception e) {
			throw new ConverterException(e.getMessage());
		}
		return resultado.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	private boolean ehConversaoParaMesmaMoeda(String from, String to) {
		return from != null && to != null && from.equalsIgnoreCase(to);
	}

	private Calculadora getCalculadora(String[] cotacaoFrom, String[] cotacaoTo) throws ConverterException {
		if(ehConversaoParaReal(cotacaoFrom, cotacaoTo))
			return fabrica.fabricar(cotacaoFrom, cotacaoTo);
		
		return fabrica.fabricar(buscarTipoNaCotacao(cotacaoFrom, cotacaoTo));
	}

	private String buscarTipoNaCotacao(String[] cotacaoFrom, String[] cotacaoTo) {
		String tipo = cotacaoFrom[2];
		if(cotacaoTo[3].equals(USD))
			tipo = cotacaoTo[2];
		return tipo;
	}

	private boolean ehConversaoParaReal(String[] infoFrom, String[] cotacaoTo){
		return infoFrom == null || cotacaoTo == null;
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
		CotacaoDAO dao = new CotacaoDAO();
		dados =  dao.buscarDadosCotacao(quotation);
	}
}
