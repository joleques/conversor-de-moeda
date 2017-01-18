package conversor.core.calculadora;

import java.math.BigDecimal;

public abstract class Calculadora {


	protected String ajustarFormato(String linha) {
		return linha.replace(",", ".");
	}
	
	public BigDecimal calcular (String value, String[] infoFrom, String[] infoTo ){
		if(fromOuToehRealOuDolar(infoFrom, infoTo))
			return converter(value, infoTo ,infoFrom );
		return converter(value, infoFrom, infoTo);
	}
	
	private boolean fromOuToehRealOuDolar(String[] from, String[] to) {
		return from == null || to == null || from[3].equals("USD") || to[3].equals("USD");
	}
	
	protected abstract BigDecimal converter (String value, String[] infoFrom, String[] infoTo );
}
