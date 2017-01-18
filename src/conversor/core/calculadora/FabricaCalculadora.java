package conversor.core.calculadora;

import conversor.exception.ConverterException;


public class FabricaCalculadora {
	
	
	
	public Calculadora fabricar(String tipo) throws ConverterException{
		if(tipo.equals("A")){
			return new CalculadoraTipoA();
		}if(tipo.equals("B")){
			return new CalculadoraTipoB();
		}
		throw new ConverterException("Tipo invalido!!");
	}
	
	public Calculadora fabricar(String from, String to) throws ConverterException{
		if(!from.equalsIgnoreCase("BRL") && to.equalsIgnoreCase("BRL")){
			return new CalculadoraRealCompra();
		}else if(from.equalsIgnoreCase("BRL") && !to.equalsIgnoreCase("BRL")){
			return new CalculadoraRealVenda();
		}
		throw new ConverterException("Tipo invalido!!");
	}

}
