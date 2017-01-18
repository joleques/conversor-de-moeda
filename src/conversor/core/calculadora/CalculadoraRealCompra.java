package conversor.core.calculadora;

import java.math.BigDecimal;

public class CalculadoraRealCompra extends Calculadora {

	@Override
	public BigDecimal calcular(String value, String[] infoFrom, String[] infoTo  ) {
		return new BigDecimal(ajustarFormato(infoFrom[4])).multiply(new BigDecimal(value));
	}

}
