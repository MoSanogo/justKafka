package src;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class CKafkaConsumer {
    public static void consume(String topic){
        Logger logger= LoggerFactory.getLogger(CKafkaConsumer.class);
        Properties settings=new Properties();
        settings.put(ConsumerConfig.CLIENT_ID_CONFIG,"basic-producer-v0.1.0");
        settings.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//        settings.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
//        settings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        settings.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        settings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"src.CustomDeserializer");
        settings.put(ConsumerConfig.GROUP_ID_CONFIG,"christine");
        settings.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        KafkaConsumer consumer=new KafkaConsumer(settings);
        consumer.subscribe(Arrays.asList(topic));
        while(true){
            ConsumerRecords<String,Person> records=consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String,Person> record:records){
                logger.info(record.value().getFirst());
                logger.info(record.value().getLast());
                logger.info( String.valueOf(record.value().getAge()));
            }
        }
    }
}
