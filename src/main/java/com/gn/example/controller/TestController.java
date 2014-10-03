package com.gn.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gn.example.dao.entity.TableOne;
import com.gn.example.dao.entity.TableTwo;
import com.gn.example.dao.service.TransactionalService;

@Controller

public class TestController {
	
	@Autowired
	private TransactionalService transactionalService;
	
	@RequestMapping("/Test")
	public ModelAndView helloWorld(){
		
		TableOne tableOne = new TableOne();
		tableOne.setValue("value1");
		
		TableTwo tableTwo = new TableTwo();
		tableTwo.setValue("value2");
		
		try {
			transactionalService.persist(tableOne, tableTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		
		return model;
	}

	
}
