package conversor.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import conversor.exception.ConverterException;

public class ValidadorTest {
	

	private Validador validador;

	@Before
	public void setUp() throws Exception {
		validador = new Validador();
	}
	
	@Test
	public void naoDeveConverterParaMesmaMoeda(){
		try {
			validador.validarParametrosEntrada("BRL", "BRL", 100, "17/01/2017");
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("Moedas não podem ser iguais.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoFromNulo(){
		try {
			validador.validarParametrosEntrada(null, "BRL", 100, "17/01/2017");
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("from esta nulo.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoToNulo(){
		try {
			validador.validarParametrosEntrada("BRL", null, 100, "17/01/2017");
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("to esta nulo.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoValueNulo(){
		try {
			validador.validarParametrosEntrada("BRL", "USD", null, "17/01/2017");
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("value invalido.", e.getMessage());
		}
	}
	
	@Test
	public void deveSubirExcecaoQuandoQuatationNulo(){
		try {
			validador.validarParametrosEntrada("BRL", "USD", 100, null);
			fail("Teste falho!");
		} catch (ConverterException e) {
			assertEquals("quotation esta nulo.", e.getMessage());
		}
	}

}
