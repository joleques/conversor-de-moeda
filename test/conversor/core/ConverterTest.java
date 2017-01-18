package conversor.core;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import conversor.exception.ConverterException;

public class ConverterTest {
	
	private Converter converter;

	@Before
	public void setUp() throws Exception {
		converter = new Converter();
	}
	
	@Test
	public void deveConverterPesoChilenoEmReal(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("CLP", "BRL", 100, "17/01/2017");
			assertEquals(quotation, new BigDecimal("0.49"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterRealParaPesoArgentino(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("BRL", "ARS", 100, "17/01/2017");
			assertEquals(quotation, new BigDecimal("494.80"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterRandeParaLibraEgipicia(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("ZAR", "EGP", 100, "17/01/2017");
			assertEquals(quotation, new BigDecimal("142.86"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	

}
