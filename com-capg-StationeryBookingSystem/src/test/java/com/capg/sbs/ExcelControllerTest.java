package com.capg.sbs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.excel.ExcelService;

class ExcelControllerTest {
	
	@MockBean
	ExcelService excelService;
	
	@Test
	void Exceltest(){
		RestTemplate restTemplate = new RestTemplate();
		String url="http://localhost:2211/api/excel/download";
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