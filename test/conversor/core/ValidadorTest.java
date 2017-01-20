package conversor.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import conversor.core.validadores.ValidadorParametrosEntrada;
import conversor.exception.ConverterException;

public class ValidadorTest {
	

	private ValidadorParametrosEntrada validador;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void deveSubirExcecaoQuandoFromNulo(){
		try {
			validador = new ValidadorParametrosEntrada(null, "BRL", 100, "17/01/2017");
			validador.validar();
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("from esta nulo.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoToNulo(){
		try {
			validador = new ValidadorParametrosEntrada("BRL", null, 100, "17/01/2017");
			validador.validar();
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("to esta nulo.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoValueNulo(){
		try {
			validador = new ValidadorParametrosEntrada("BRL", "USD", null, "17/01/2017");
			validador.validar();
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("value invalido.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoQuatationNulo(){
		try {
			validador = new ValidadorParametrosEntrada("BRL", "USD", 100, null);
			validador.validar();
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("quotation esta nulo.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoValorMenorQueZero(){
		try {
			validador = new ValidadorParametrosEntrada("BRL", "USD", -100, null);
			validador.validar();
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("value invalido.", e.getMessage());
		}
	}

}
