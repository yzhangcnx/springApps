package com.yong.jmx;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	private static ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private static Destination destination = null;
	private MessageConsumer consumer = null;

	public Receiver() {

	}

	public void receiveMessage() throws NamingException {
		try {
			//factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//destination = session.createQueue("example.MyQueue");
			consumer = session.createConsumer(destination);
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage text = (TextMessage) message;
				System.out.println("Message is : " + text.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			InitialContext jndi = new InitialContext();
			Sender.listJNDINameType(jndi);
			
			factory = (ConnectionFactory) jndi.lookup("QueueConnectionFactory");
			destination = (Destination) jndi.lookup("MyQueue");
		
			Receiver receiver = new Receiver();
			receiver.receiveMessage();
		}
		catch (NamingException ne) {
			System.out.println(ne.getStackTrace());
		}
	}
}
