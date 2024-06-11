package com.smartflow.pubsub;

import org.eclipse.paho.client.mqttv3.*;


public class MQTTPublisher {

  
    private static final String TOPIC = "Sensor/Receive";

    private MqttClient client;
    
    public MQTTPublisher() {

        String brokerURL = "tcp://mqtt.eclipseprojects.io:1883";

    
        String clientId = "ubaprojectaccet2024";

        try {
            client = new MqttClient(brokerURL, clientId);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

  
    public void start() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            
            options.setCleanSession(false);
            options.setWill(client.getTopic("home/LWT"), "I'm gone. Bye.".getBytes(), 0, false);

            client.connect(options);

            // publish something...
            publishTemperature();
            publishTemperature();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    private void publishTemperature() throws MqttException {
      
        MqttTopic temperatureTopic = client.getTopic(TOPIC);

        String temperature = "20 °C";

       
        temperatureTopic.publish(new MqttMessage(temperature.getBytes()));

        // debug
        System.out.println("Published message on topic '" + temperatureTopic.getName() + "': " + temperature);
    }

    /**
     * The main
     */
    public static void main(String[] args) {
        MQTTPublisher publisher = new MQTTPublisher();
        publisher.start();
    }

}
