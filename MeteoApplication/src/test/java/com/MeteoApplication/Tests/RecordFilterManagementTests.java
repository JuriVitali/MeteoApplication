package com.MeteoApplication.Tests;

import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.MeteoApplication.Exception.IllegalFieldException;
import com.MeteoApplication.Exception.IllegalOperatorException;
import com.MeteoApplication.model.Data;
import com.MeteoApplication.model.Record;
import com.MeteoApplication.filters.RecordFilterManagement;

@SpringBootTest
class RecordFilterManagementTests {

	private Vector<Record> v;
	private String filtro2;
	private String filtro3;
	private String filtro4;
	private String filtro5;
	private String filtro6;
	
	@BeforeEach
	void setUp() throws Exception {
		
		v = new Vector<Record>();
		
		v.add(new Record(12345, "Ancona", new Data(11, 01, 2021), 15.0, 13.0, 18.0, 11.0));
		v.add(new Record(12345, "Senigallia", new Data(12, 01, 2021), 17.0, 13.0, 18.0, 12.0));
		v.add(new Record(12345, "Milano", new Data(13, 01, 2021), 16.0, 15.0, 20.0, 12.0));
		v.add(new Record(12345, "Fano", new Data(01, 01, 2021), 15.0, 14.0, 18.0, 11.0));
		v.add(new Record(12345, "Roma", new Data(03, 01, 2021), 14.0, 13.0, 19.0, 13.0));
		v.add(new Record(12345, "Venezia", new Data(04, 01, 2021), 15.0, 13.0, 18.0, 11.0));
		v.add(new Record(12345, "Firenze", new Data(07, 01, 2021), 16.0, 14.0, 20.0, 13.0));
		
		filtro2 = "{\"temperature\" : {\"$gt\" : 2.1}}";
		filtro3 = "{\"period\" : {\"$lt\" : \"2021-01-18\"}}";
		filtro4 = "{\"id\" : {\"$gt\" : 524178}}";
		filtro5 = "{\"ReTempAvg\" : {\"$gte\" : 7.1}}";
		filtro6 = "{\"temperature\" : {\"$eq\" : 2.1}}";
		
		
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	
	@Test
	void test1() {
		assertThrows(IllegalOperatorException.class, () -> {RecordFilterManagement.parseBody(filtro3,v,4);});
	}
	
	@Test
	void test2() {
		assertThrows(IllegalFieldException.class, () -> {RecordFilterManagement.parseBody(filtro2,v,4);});
	}
	
	
	@Test
	void test3() {
		assertThrows(IllegalOperatorException.class, () -> {RecordFilterManagement.parseBody(filtro4,v,5);});
	}
	
	@Test
	void test4() {
		assertThrows(IllegalOperatorException.class, () -> {RecordFilterManagement.parseBody(filtro4,v,5);});
		
	}
	
        @Test
	void test5() {
		assertThrows(IllegalOperatorException.class, () -> {RecordFilterManagement.parseBody(filtro6,v,6);});
	}
	
	@Test
	void test6() {
		assertThrows(IllegalFieldException.class, () -> {RecordFilterManagement.parseBody(filtro5,v,6);});
	}
	
	@Test
	void test7() {
		IllegalFieldException exception = assertThrows(IllegalFieldException.class, () -> {RecordFilterManagement.parseBody(filtro5,v,6);});
		
		assertEquals("Campo non valido: questa rotta ammette solo filtri con "+ "campo : temperature, tempPer e period.", exception.getMessage());
		
	}
	
}
