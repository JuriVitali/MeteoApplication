package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;
import model.Record;
import model.Metadata;
import repository.DataManagement;

@RestController
public class Controller {
	
	@RequestMapping(value = "metadata", method=RequestMethod.GET)
	public Vector<Metadata> getVectorMetadata() {
		return DataManagement.getMetadata();
	}
	
	@RequestMapping(value = "data", method=RequestMethod.GET)
	public Vector<Record> getAllData() {
		return DataManagement.getData();
	}
	
	
	
	
}
