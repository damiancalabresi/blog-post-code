package com.dcalabresi.configuration;

import com.dcalabresi.utils.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfiguration {

    @Bean
    public ConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        connectionFactory.setObjectMessageSerializationDefered(true);
        connectionFactory.setCopyMessageOnSend(false);

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        return cachingConnectionFactory;
    }

    @Bean
    public JmsTemplate JmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsConnectionFactory());
        jmsTemplate.setDefaultDestination(new ActiveMQQueue(Constants.MAIL_QUEUE));
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.setReceiveTimeout(2000);
        return jmsTemplate;
    }


	@Bean(name = Constants.TRANSACTION_MANAGER_JMS)
	public JmsTransactionManager jmsTransactionManager() {
		return new JmsTransactionManager(jmsConnectionFactory());
	}

}
