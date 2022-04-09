package src;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class CKafkaProducer {

    public static void publish(String topic,String key, Person value){
        System.out.println("****Starting basic producer");
        Properties settings=new Properties();
        settings.put(ProducerConfig.CLIENT_ID_CONFIG,"basic-producer-v0.1.0");
        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
//        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
       settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"src.CustomSerializer");
        final KafkaProducer<String, Person> producer=new KafkaProducer<>(settings);
        final ProducerRecord<String,Person> record=new ProducerRecord(topic,key,value);
        producer.send(record);
        producer.flush();
        producer.close();
    }
}
