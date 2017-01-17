package conversor.core;

import static org.junit.Assert.*;

import java.io.BufferedReader;

import org.junit.Before;
import org.junit.Test;

import conversor.exception.ConverterException;
import conversor.exception.ParametroInvalidoException;

public class BufferedCSVTest {
	
	private BufferedCSV leitor;

	@Before
	public void setUp() throws Exception {
		leitor = new BufferedCSV();
	}
	
	@Test
	public void deveConstruirUrlParaDownloadCSVQuandoReceberDataPorParametro(){
		String quotation = "17/01/2016";
		String url;
		try {
			url = leitor.construirUrl(quotation);
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
			url = leitor.construirUrl(quotation);
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
			url = leitor.construirUrl(quotation);
			assertEquals("http://www4.bcb.gov.br/Download/fechamento/20160117.csv",url);
		} catch (ParametroInvalidoException e) {
			assertEquals("Data para conversão invalida!", e.getMessage());
		}catch (Exception e) {
			fail("Teste falho: " + e.getMessage());
		}
	}
	
	@Test
	public void deveRetornarBufferedReaderQuandoPossuirArquivoCsvParaDataPassada(){
		try {
			BufferedReader bufferedReader = leitor.getBufferedReader("17/01/2017");
			assertNotNull(bufferedReader);
		} catch (ConverterException e) {
			fail("Teste falho: " + e.getMessage());
		}
	}
	
	@Test
	public void deveSubirConverterExceptionQuandoNãoEncontrarArquivoParaDataSolicitada(){
		try {
			BufferedReader bufferedReader = leitor.getBufferedReader("15/01/2017");
			fail("Teste falho");
		} catch (ConverterException ex) {
			assertTrue(ex.getMessage().contains("Não possui cotação para o dia solicitado!"));
		}
	}

}
