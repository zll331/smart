package com.example.mqttdemo;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqTTTestSubscribe {

    public static void main(String[] args) throws MqttException {
        String broker = "tcp://localhost:1883";
        String clientId = "1705c_zll";
        //String userName = "mqtt-test";
        // String password = "mqtt-test";

        String topic = "yjs/1705c/#";//发布的主题
        int qos = 1;

        //创建客户端
        MqttClient mqttClient = new MqttClient(broker,clientId,new MemoryPersistence());


        //它是一个创建连接的参数
        MqttConnectOptions connectOptions = new MqttConnectOptions();

        connectOptions.setCleanSession(true);//在重新启动或重新连接记住该状态
        //connectOptions.setUserName(userName);
        //connectOptions.setPassword(password.toCharArray());
        connectOptions.setConnectionTimeout(10);//设置超时时间
        connectOptions.setKeepAliveInterval(20);//设置回话心跳时间




        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("connectionLost");
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("topic"+s);
                System.out.println("Qos"+mqttMessage.getQos());
                System.out.println("message content"+new String(mqttMessage.getPayload()));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("发送完之后----->>>"+iMqttDeliveryToken.isComplete());
            }
        });
        mqttClient.connect(connectOptions);
        mqttClient.subscribe(topic,1);
    }
}
