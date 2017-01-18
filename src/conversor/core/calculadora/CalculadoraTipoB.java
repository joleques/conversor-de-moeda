package conversor.core.calculadora;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculadoraTipoB extends CalculadoraOutra {

	@Override
	protected BigDecimal resultadoFromEmDolar(String value, String taxa) {
		return new BigDecimal(value.toString()).multiply(new BigDecimal(ajustarFormato(taxa)));
	}
	
	@Override
	protected BigDecimal calcular(String taxaTo, BigDecimal resultadoFromEmDolar) {
		return resultadoFromEmDolar.divide((new BigDecimal(ajustarFormato(taxaTo))), MathContext.DECIMAL32);
	}


}
