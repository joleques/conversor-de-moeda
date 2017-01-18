package conversor.core.calculadora;

import java.math.BigDecimal;

public class CalculadoraRealCompra extends Calculadora {

	@Override
	public BigDecimal converter(String value, String[] infoFrom, String[] infoTo  ) {
		return new BigDecimal(ajustarFormato(infoTo[4])).multiply(new BigDecimal(value));
	}

}
