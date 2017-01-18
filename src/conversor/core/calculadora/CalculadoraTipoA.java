package conversor.core.calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraTipoA extends CalculadoraOutra {

	@Override
	protected BigDecimal resultadoFromEmDolar(String value, String taxa) {
		return new BigDecimal(value.toString()).divide(new BigDecimal(ajustarFormato(taxa)), 2, RoundingMode.HALF_UP);
	}

	@Override
	protected BigDecimal calcular(String taxaTo, BigDecimal resultadoFromEmDolar) {
		return resultadoFromEmDolar.multiply((new BigDecimal(ajustarFormato(taxaTo))));
	}

}
