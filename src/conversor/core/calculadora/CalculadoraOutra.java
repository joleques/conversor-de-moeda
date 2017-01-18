package conversor.core.calculadora;

import java.math.BigDecimal;

public abstract class CalculadoraOutra extends Calculadora {

	@Override
	public BigDecimal converter(String value, String[] infoFrom, String[] infoTo) {
		return calcular(infoTo[6], resultadoFromEmDolar(value, infoFrom[7]));
	}

	protected abstract BigDecimal calcular(String taxaTo, BigDecimal resultadoFromEmDolar);
	
	protected abstract BigDecimal resultadoFromEmDolar(String value, String taxa);

}
