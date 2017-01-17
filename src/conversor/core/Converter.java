package conversor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

import conversor.exception.ConverterException;

public class Converter {

	private static final String CARACTER_PARA_SPLIT = ";";

	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation){
		/**
		  	a) De Moeda para Dólar
			[Paridade Compra Moeda] X [Valor]
			
			b) De Dólar para Moeda
			[Paridade Compra Moeda] X [Valor]
			c) De Real para Dólar
			[Valor] / [Paridade de Venda do Dólar]
			d) De Dólar para Real
			[Valor] * [Paridade Compra Dólar]
			e) De Real para Moeda
			[Paridade de Compra Moeda] x [Valor]
			f) De Moeda para Real
			[Valor] / [Paridade de Compra Moeda]
			g) De Moeda1 para Moeda2 (ex: Euro para Libra Esterlina)
			([Valor] X [Paridade Compra Moeda1]) / [Paridade Venda Moeda2]
		 */
		return null;
	}

	public void read(String quotation) throws ConverterException{
	  try {
		    BufferedCSV bufferedCSV = new BufferedCSV();
		    BufferedReader br = bufferedCSV.getBufferedReader(quotation);
	        String linha = "";
		    while ((linha = br.readLine()) != null) {
                String[] arrayLinha = linha.split(CARACTER_PARA_SPLIT);
                System.out.println("Country [code= " + arrayLinha[4] + " , name=" + arrayLinha[5] + "]");
            }
	  } catch (IOException ex) {
		  throw new ConverterException(ex);
	  }
	}
}
