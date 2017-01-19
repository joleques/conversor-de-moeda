package conversor.core.calculadora;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculadoraTipoB extends CalculadoraOutra {

	@Override
	protected BigDecimal resultadoEmDolar(String value, String taxa) {
		return new BigDecimal(value.toString()).multiply(new BigDecimal(ajustarFormato(taxa)));
	}
	
	@Override
	protected BigDecimal calcular(String taxaTo, BigDecimal resultadoFromEmDolar) {
		return resultadoFromEmDolar.divide((new BigDecimal(ajustarFormato(taxaTo))), 2, RoundingMode.HALF_UP);
	}


}
