package conversor.core.calculadora;

import conversor.exception.ConverterException;


public class FabricaCalculadora {
	
	private static final String TIPO_B = "B";
	private static final String TIPO_A = "A";
	private static final String BRL = "BRL";

	public Calculadora fabricar(String tipo) throws ConverterException{
		if(tipo.equals(TIPO_A)){
			return new CalculadoraTipoA();
		}if(tipo.equals(TIPO_B)){
			return new CalculadoraTipoB();
		}
		throw new ConverterException("Tipo invalido!!");
	}
	
	public Calculadora fabricar(String from, String to) throws ConverterException{
		if(!from.equalsIgnoreCase(BRL) && to.equalsIgnoreCase(BRL)){
			return new CalculadoraRealCompra();
		}else if(from.equalsIgnoreCase(BRL) && !to.equalsIgnoreCase(BRL)){
			return new CalculadoraRealVenda();
		}
		throw new ConverterException("Tipo invalido!!");
	}

}
