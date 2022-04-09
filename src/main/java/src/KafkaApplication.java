package src;
public class KafkaApplication {

    public static void main(String[] args) {
       Person person=new Person("Modibo","Sanogo",36);
       CKafkaProducer.publish("modibo",person.getFirst(),person);
       CKafkaConsumer.consume("modibo");

    }
}
