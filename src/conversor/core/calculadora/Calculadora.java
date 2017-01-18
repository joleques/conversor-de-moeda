package conversor.core.calculadora;

import java.math.BigDecimal;

public abstract class Calculadora {


	protected String ajustarFormato(String linha) {
		return linha.replace(",", ".");
	}
	
	public abstract BigDecimal calcular (String value, String[] infoFrom, String[] infoTo );
}
