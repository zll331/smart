package com.example.mqttdemo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTTestPublish {

    public static void main(String[] args) throws MqttException {
        String broker = "tcp://localhost:1883";
        String clientId = "1705c_zll";
        //String userName = "mqtt-test";
       // String password = "mqtt-test";
        //发布的内容
        String content = "hellomqtt";
        //发布的主题
        String topic = "yjs/1705c/zll";
        int qos = 1;

        //创建客户端
        MqttClient mqttClient = new MqttClient(broker,clientId,new MemoryPersistence());


        //它是一个创建连接的参数
        MqttConnectOptions connectOptions = new MqttConnectOptions();

        connectOptions.setCleanSession(true);//在重新启动或重新连接记住该状态
        //connectOptions.setUserName(userName);
       //connectOptions.setPassword(password.toCharArray());
        mqttClient.connect(connectOptions);

        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        mqttClient.publish(topic,message);

        mqttClient.disconnect();
        mqttClient.close();

    }
}
