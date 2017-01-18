package conversor.core.calculadora;

import java.math.BigDecimal;

public class CalculadoraTipoB extends CalculadoraOutra {

	@Override
	protected BigDecimal resultadoFromEmDolar(String value, String taxa) {
		return new BigDecimal(value.toString()).multiply(new BigDecimal(ajustarFormato(taxa)));
	}

}
