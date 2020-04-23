package com.iamuse.kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Properties;

public class Producer {

	public static void kafkaProducer(Object obj) throws JsonGenerationException, JsonMappingException, IOException
	{
		
		String bootstarpservers = "localhost:9092";

		// Create Producer properties
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstarpservers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// Create the Producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
				
		//String jsonString =  "{\"success\": false,  \"payload\": \"Demo chal raha hai bhai\"  }";
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(obj); 
		// Create a producer record
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("twitter", jsonString);

		// Send data - asynchronus
		producer.send(record);

		/*Basically we need flush because send method doesn't produce any data it will execute and exit.So flush comes into
		play to wait until it is produced.*/
		producer.flush();
		producer.close();

	}
}