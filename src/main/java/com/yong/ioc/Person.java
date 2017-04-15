package com.yong.ioc;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private String name;
	private String address;
	private int age;
	
	private static int id = 0;
	
	public Person () {
		System.out.println("inside person constructot " + (id++));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString () {
		return id + " " + name + " " + address + " " + age;
	}
}
