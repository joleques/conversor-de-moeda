package conversor.core.calculadora;

import java.math.BigDecimal;

public abstract class Calculadora {


	protected String ajustarFormato(String linha) {
		return linha.replace(",", ".");
	}
	
	public BigDecimal calcular (String value, String[] infoFrom, String[] infoTo ){
		if(fromOuToehReal(infoFrom, infoTo))
			return converter(value, infoTo ,infoFrom );
		return converter(value, infoFrom, infoTo);
	}
	
	private boolean fromOuToehReal(String[] from, String[] to) {
		return from == null || to == null;
	}
	
	protected abstract BigDecimal converter (String value, String[] infoFrom, String[] infoTo );
}
