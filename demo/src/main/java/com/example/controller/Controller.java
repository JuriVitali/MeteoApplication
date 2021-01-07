package com.example.controller;

import java.util.Vector;

import com.example.database.ManageData;
import com.example.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/metadata")
	public Vector<Metadata> getMeta () {
		return  ManageData.getMetadata();
	}

}
