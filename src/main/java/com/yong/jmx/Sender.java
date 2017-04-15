package com.yong.jmx;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	public static ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	public static Destination destination = null;
	private MessageProducer producer = null;

	public Sender() {

	}

	public void sendMessage() throws NamingException {

		try {
			
			//factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//destination = session.createQueue("example.MyQueue");
			producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();
			message.setText("Hello ...This is a sample message..sending from FirstClient");
			producer.send(message);
			System.out.println("Sent: " + message.getText());

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void listJNDINameType (InitialContext jndi) throws NamingException {
		//InitialContext jndi = new InitialContext();
    NamingEnumeration<NameClassPair> list = jndi.list("");
    while (list.hasMoreElements()) {
        NameClassPair nameClassPair = list.nextElement();
        String name = nameClassPair.getName();
        String type = nameClassPair.getClassName();
        System.out.println("Name " + name + ": Type " + type);
    }
	}

	public static void main(String[] args){
		try {
			InitialContext jndi = new InitialContext();
			listJNDINameType(jndi);
			
			factory = (ConnectionFactory) jndi.lookup("QueueConnectionFactory");
			destination = (Destination) jndi.lookup("MyQueue");
		
			Sender sender = new Sender();
		
			sender.sendMessage();
			
		}
		catch(NamingException ne) {
			ne.printStackTrace();
		}
		
	}
}
