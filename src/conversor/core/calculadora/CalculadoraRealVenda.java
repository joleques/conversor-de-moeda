package conversor.core.calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraRealVenda extends Calculadora {

	@Override
	public BigDecimal calcular(String value, String[] infoFrom, String[] infoTo ) {
		return new BigDecimal(value).divide(new BigDecimal(ajustarFormato(infoTo[5])), 2, RoundingMode.HALF_UP);
	}

}
