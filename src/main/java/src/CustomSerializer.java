package src;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSerializer implements Serializer<Person> {
    private final Logger logger= LoggerFactory.getLogger(CustomSerializer.class);
    private final ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public byte[] serialize(String topic, Person data) {
       if(data==null) logger.warn("Yous should implement the serializer for" + data.getClass().getTypeName());
        try {
            return this.objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
