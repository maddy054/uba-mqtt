package com.smartflow.pubsub;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTSubscriber {

    private MqttClient mqttClient;

  
    public MQTTSubscriber() {
        String brokerURL = "tcp://mqtt.eclipseprojects.io:1883";

        try {
            mqttClient = new MqttClient(brokerURL, "ubaprojectaccet2024");
            System.out.println("uba");

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

   
    public void start() {
        try {

            mqttClient.setCallback(new SubscribeCallback());
            mqttClient.connect();
            
            final String topic = "accetubamnsv";
            mqttClient.subscribe(topic);

            System.out.println("The subscriber is now listening to " + topic + "...");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public static void main(String... args) {
    	MQTTSubscriber mqtt = new MQTTSubscriber();
		mqtt.start();
	}

}
