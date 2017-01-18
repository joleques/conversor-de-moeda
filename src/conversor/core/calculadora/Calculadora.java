package conversor.core.calculadora;

import java.math.BigDecimal;

public abstract class Calculadora {


	protected String ajustarFormato(String linha) {
		return linha.replace(",", ".");
	}
	
	public BigDecimal calcular (String value, String[] infoFrom, String[] infoTo ){
		if(fromOuToehReal(infoFrom, infoTo))
			return converter(value, infoTo ,infoFrom );
		if(fromOuToehDolar(infoFrom, infoTo)){
			if((ehDolar(infoTo) && infoFrom[2].equals("A")) || (ehDolar(infoFrom) && infoTo[2].equals("A")))
				return converter(value, infoFrom, infoTo );
			else
				return converter(value, infoTo ,infoFrom );
		}
		return converter(value, infoFrom, infoTo);
	}
	
	private boolean fromOuToehReal(String[] from, String[] to) {
		return from == null || to == null;
	}
	
	private boolean fromOuToehDolar(String[] from, String[] to) {
		return ehDolar(from) || ehDolar(to);
	}

	private boolean ehDolar(String[] moeda) {
		return moeda[3].equals("USD");
	}
	
	protected abstract BigDecimal converter (String value, String[] infoFrom, String[] infoTo );
}
