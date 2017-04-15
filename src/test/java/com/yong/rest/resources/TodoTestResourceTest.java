package com.yong.rest.resources;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TodoTestResourceTest {
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass");
	}
	
	@Before
	public void before() {
		System.out.println("before");
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		System.out.println("test");
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass");
	}
}
