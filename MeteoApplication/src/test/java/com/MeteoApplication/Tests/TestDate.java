package com.MeteoApplication.Tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.MeteoApplication.Exception.InvalidDateFormatException;
import com.MeteoApplication.util.*;
import com.MeteoApplication.model.Data;


class TestDate {

    private String time;
	private String time2;
    private Data data1;
	private Data data2;
	private Data data3;
	private Data data4;

	// testati metodi confrontaDate, getAnno, getMese, getGiorno, DateUguali, ottieniDataAtt
	@BeforeEach
	void setUp() throws Exception {
		time = "2021-01-21";
		time2 ="abcd-ef-gh"; 
		
		//metodo confrontaDate
		data1 = new Data(21, 01, 2021);
		data2 = new Data(21, 01, 2021); 
		data3 = new Data(22, 01, 2021);
	    data4 = new Data(20, 01, 2021);
			
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testconfrontaDate1() {
		assertEquals(0,DateOperations.confrontaDate(data1, data2));
	}
	
	@Test
	void testconfrontaDate2() {
		assertEquals(1,DateOperations.confrontaDate(data2, data3));
	}
	
	@Test
	void testconfrontaDate3() {
		assertEquals(-1,DateOperations.confrontaDate(data2, data4));
	}
	
	@Test
	void testget() {
		assertEquals(21,DateOperations.getGiorno(time));
		assertEquals(01,DateOperations.getMese(time));
		assertEquals(2021,DateOperations.getAnno(time));
	}
	
	@Test
	void testexceptiongetGiorno() {
		InvalidDateFormatException exception = assertThrows(InvalidDateFormatException.class, () -> {DateOperations.getGiorno(time2);});
		
		assertEquals("Il formato della data non è valido. Deve rispettare il seguente formato: aaaa-mm-gg.", exception.getMessage());
		
	}
	@Test
	void testexceptiongetGiorno2() {
		 assertThrows(InvalidDateFormatException.class, () -> {DateOperations.getGiorno(time2);});
		
	}
	
	@Test
	void testexceptiongetMese() {
        InvalidDateFormatException exception = assertThrows(InvalidDateFormatException.class, () -> {DateOperations.getMese(time2);});
		
		assertEquals("Il formato della data non è valido. Deve rispettare il seguente formato: aaaa-mm-gg.", exception.getMessage());
		
	}
	
	@Test
	void testexceptiongetAnno() {
        InvalidDateFormatException exception = assertThrows(InvalidDateFormatException.class, () -> {DateOperations.getAnno(time2);});
		
		assertEquals("Il formato della data non è valido. Deve rispettare il seguente formato: aaaa-mm-gg.", exception.getMessage());
		
	}
	
	@Test
	void testDateUguali() {
		assertEquals(true,DateOperations.DateUguali(data1, data2));
	}
	
	@Test
	void testDateUguali2() {
		assertEquals(false,DateOperations.DateUguali(data1, data3));
	}

	@Test
	void testottieniDataAtt() {
		long time = 1611103902;
		assertEquals(20,DateOperations.ottieniDataAtt(time).getGiorno());
		assertEquals(01,DateOperations.ottieniDataAtt(time).getMese());
		assertEquals(2021,DateOperations.ottieniDataAtt(time).getAnno());
	}
	
	
}
