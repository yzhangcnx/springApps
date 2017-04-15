package com.yong.ioc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Customer
{
	//you want autowired this field.
	@Autowired
	private Person person;
	private int type;
	private String action;
	
	@Autowired(required = false)
	private Logger logger;

	public Customer () {
		System.out.println("inside customer constructot");
	}
	//getter and setter method
	public Person getPerson() {
		return person;
	}
	
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public String toString () {
		if(logger != null) {
			logger.info("inside customer toString.");
		}
		return person + " " + type + " " + action;
	}
	
}