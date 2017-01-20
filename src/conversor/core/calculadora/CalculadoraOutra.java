package conversor.core.calculadora;

import java.math.BigDecimal;

public abstract class CalculadoraOutra extends Calculadora {

	@Override
	public BigDecimal calcular(String value, String[] cotacaoFrom, String[] cotacaoTo) {
		if(ehParaExecutarCalculoTipoA(cotacaoFrom, cotacaoTo) || fromOuToNaoehDolar(cotacaoFrom, cotacaoTo))
			return calcular(cotacaoTo[6], resultadoEmDolar(value, cotacaoFrom[7]));
		else
			return calcular(cotacaoFrom[6], resultadoEmDolar(value, cotacaoTo[7]));
	}

	private boolean ehParaExecutarCalculoTipoA(String[] cotacaoFrom, String[] cotacaoTo) {
		return (ehDolar(cotacaoTo) && ehTipoA(cotacaoFrom)) || (ehDolar(cotacaoFrom) && ehTipoA(cotacaoTo));
	}

	private boolean ehTipoA(String[] cotacao) {
		return cotacao[2].equals("A");
	}

	private boolean ehDolar(String[] moeda) {
		return moeda[3].equals("USD");
	}
	
	protected boolean fromOuToNaoehDolar(String[] from, String[] to) {
		return !(ehDolar(from) || ehDolar(to));
	}
	
	protected abstract BigDecimal calcular(String taxaTo, BigDecimal resultadoFromEmDolar);
	
	protected abstract BigDecimal resultadoEmDolar(String value, String taxa);

}