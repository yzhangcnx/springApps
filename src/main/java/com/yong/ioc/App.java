package com.yong.ioc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
		//@Autowired (required = false)
		private static Logger logger;
		//private static Logger logger = Logger.getLogger(App.class);;
		private static int counter;
	
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
    	
    	//class = org.log4j.apache.Logger, factory-method = getLogger; constructor-arg value = "rootlogger"
    	//logger = (Logger)context.getBean("logger");
    	
    	/*
    	//class="org.springframework.beans.factory.config.CommonsLogFactoryBean <Property name="logName" value="rootLogger" />
    	Log4JLogger log4JLogger  = (Log4JLogger) context.getBean("logger");
    	if(log4JLogger != null) {
    		logger = log4JLogger.getLogger();
    	}
    	if(logger == null) {
    		logger = Logger.getLogger(App.class);;
    	}
    	*/
    			
    	File f;
    	FileReader reader = null;
    	try {
    		URL url = App.class.getClassLoader().getResource("beans.xml");
    		f = new File(url.getFile());
    		reader = new FileReader(f);
    		BufferedReader bufferedReader = new BufferedReader(reader);
    		String line;
    		while((line =bufferedReader.readLine()) != null) {
    			logger.info(line);
    		}
    		/*
    		InputStreamReader consoleReader = new InputStreamReader(System.in);
    		String command = new BufferedReader(consoleReader).readLine();
    		if("q".equalsIgnoreCase(command))
    		{
    			return;
    		}
    		*/
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(reader != null) {
    			try {
    				logger.info("closing file reader...");
    				reader.close();
    			}
    			catch (Throwable t) {
    				logger.info("closing file reader error");
    			}
    		}
    		
    		logger.info("exiting program...");
    	}
    	counter = 1;
    	
    	logger.debug("counter = " + counter++);
    	//String s = null;
    	//logger.debug(s.toString());
    	Customer cust = (Customer)context.getBean("customer");
    	Customer cust2 = (Customer)context.getBean("customer");
    	logger.info("counter = " + counter);
    	System.out.println(cust);
    	System.out.println(cust2);
    }
}
