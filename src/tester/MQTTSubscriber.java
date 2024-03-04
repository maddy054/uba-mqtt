package tester;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;



/**
 * Sample subscriber for MQTT. It uses the Eclipse Paho library and Mosquitto as a broker.
 * Mosquitto is expected to be installed and launched locally
 * (public test servers are available, however).
 *
 * It connects to the Mosquitto broker, and get all the messages pertaining to the "homestation" topic.
 *
 * @author <a href="mailto:luigi.derussis@uniupo.it">Luigi De Russis</a>
 * @version 1.1 (21/05/2019)
 */
public class MQTTSubscriber {

    // init the client
    private MqttClient mqttClient;

    /**
     * Constructor. It generates a client id and instantiate the MQTT client.
     */
    public MQTTSubscriber() {

        // the broker URL
        String brokerURL = "tcp://mqtt.eclipseprojects.io:1883";

        try {
            mqttClient = new MqttClient(brokerURL, "ubaprojectaccet2024");
            System.out.println("ubaprojectaccet2024");

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    /**
     * The method to start the subscriber. It listen to all the homestation-related topics.
     */
    public void start() {
        try {

            // set a callback and connect to the broker
            mqttClient.setCallback(new SubscribeCallback());
            mqttClient.connect();

            //Subscribe to all subtopics of home
            final String topic = "uba/Level";
            mqttClient.subscribe(topic);

            System.out.println("The subscriber is now listening to " + topic + "...");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main
     */
public static void main(String[] args) {
	MQTTSubscriber mqtt = new MQTTSubscriber();
	mqtt.start();
}
}
