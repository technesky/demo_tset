package com.sky.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: ConnectionUtil
 * @Package: com.sky.rabbitmq
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/5/27 9:58
 */
public class ConnectionUtil {
    private static Connection connection;
    private ConnectionUtil(){}

    public static Connection getConnection() throws IOException, TimeoutException {
        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost("39.108.7.26");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/VHost_test");

        //getting a connection
        if(connection==null){
            connection = factory.newConnection();
        }
        return connection;
    }


}