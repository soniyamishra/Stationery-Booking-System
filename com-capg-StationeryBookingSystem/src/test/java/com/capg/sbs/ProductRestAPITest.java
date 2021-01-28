package com.capg.sbs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.capg.sbs.pdf.PdfGenerator;

@WebMvcTest(PdfGenerator.class)
class ProductRestAPITest {

	@MockBean
	PdfGenerator pdfGenerator;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		RestTemplate restTemplate = new RestTemplate();
		String url="http://localhost:8080/api/pdf/products";
		try
		{
			restTemplate.getForEntity(url, String.class);
		}
		catch(HttpClientErrorException e)
		{
			assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
		}

	}
}