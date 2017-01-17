package conversor.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import conversor.exception.ParametroInvalidoException;

public class ConverterTest {
	
	private Converter converter;

	@Before
	public void setUp() throws Exception {
		converter = new Converter();
	}
	
	@Test
	public void deveConstruirUrlParaDownloadCSVQuandoReceberDataPorParametro(){
		String quotation = "17/01/2016";
		String url;
		try {
			url = converter.construirUrl(quotation);
			assertEquals("http://www4.bcb.gov.br/Download/fechamento/20160117.csv",url);
		} catch (Exception e) {
			fail("Teste falho: " + e.getMessage());
		}
	}
	
	@Test
	public void deveSubirParametroInvalidoExceptionQuandoReceberParametroDataInvalido(){
		String quotation = "17/01/2016/12";
		String url;
		try {
			url = converter.construirUrl(quotation);
			assertEquals("http://www4.bcb.gov.br/Download/fechamento/20160117.csv",url);
		} catch (ParametroInvalidoException e) {
			assertEquals("Data para conversão invalida!", e.getMessage());
		}catch (Exception e) {
			fail("Teste falho: " + e.getMessage());
		}
	}
	
	@Test
	public void deveSubirParametroInvalidoExceptionQuandoReceberParametroDataNulo(){
		String quotation = null;
		String url;
		try {
			url = converter.construirUrl(quotation);
			assertEquals("http://www4.bcb.gov.br/Download/fechamento/20160117.csv",url);
		} catch (ParametroInvalidoException e) {
			assertEquals("Data para conversão invalida!", e.getMessage());
		}catch (Exception e) {
			fail("Teste falho: " + e.getMessage());
		}
	}
	

}
