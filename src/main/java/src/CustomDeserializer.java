package src;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class CustomDeserializer implements Deserializer<Person> {
    private final Logger logger= LoggerFactory.getLogger(CustomDeserializer.class);
    private final ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public Person deserialize(String topic, byte[] data) {
        if(data==null) logger.warn("Yous should implement the serializer for");
        try {
            return this.objectMapper.readValue(new String(data,"UTF-8"),Person.class);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
