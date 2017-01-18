package conversor.core;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import conversor.exception.ConverterException;

public class ConversorTest {
	
	private Conversor converter;

	@Before
	public void setUp() throws Exception {
		converter = new Conversor();
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
	public void deveConverterLibraEgipiciaParaRandeAfricano(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("EGP","ZAR", 100, "18/01/2017");
			assertEquals(quotation, new BigDecimal("71.61"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterRandeAfricanoParaLibraEgipicia(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("ZAR","EGP", 100, "18/01/2017");
			assertEquals(quotation, new BigDecimal("139.16"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterRandeAfricanoParaDolar(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("USD","ZAR", 100, "18/01/2017");
			assertEquals(quotation, new BigDecimal("1348.63"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterDolarParaRandeAfricano(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("ZAR","USD", 100, "18/01/2017");
			assertEquals(quotation, new BigDecimal("7.41"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterDolarParaEuro(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("USD", "EUR", 100, "18/01/2017");
			assertEquals(quotation, new BigDecimal("93.57"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveConverterEuroParaDolar(){
		BigDecimal quotation;
		try {
			quotation = converter.currencyQuotation("EUR", "USD", 100, "18/01/2017");
			assertEquals(quotation, new BigDecimal("106.84"));
		} catch (ConverterException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveDecrementarDoisDiasQuandoDataPassadaForDomingo(){
		try {
			String data = converter.tratarDiasUteis("15/01/2017");
			assertEquals(data, "13/01/2017");
		} catch (ParseException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void deveDecrementarUmDiaQuandoDataPassadaForSabado(){
		try {
			String data = converter.tratarDiasUteis("14/01/2017");
			assertEquals(data, "13/01/2017");
		} catch (ParseException e) {
			fail("Teste falho!");
		}
	}
	
	@Test
	public void naoDeveDecrementarDiaQuandoDataPassadaForDiaSemana(){
		try {
			String data = converter.tratarDiasUteis("12/01/2017");
			assertEquals(data, "12/01/2017");
		} catch (ParseException e) {
			fail("Teste falho!");
		}
	}
	

}
