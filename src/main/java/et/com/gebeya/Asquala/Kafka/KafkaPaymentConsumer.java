package et.com.gebeya.Asquala.Kafka;

import et.com.gebeya.Asquala.Model.PaymentStatus;
import et.com.gebeya.Asquala.Model.Student;
import et.com.gebeya.Asquala.Repo.StudentRepo;

import et.com.gebeya.Asqualapayment.Dto.Request.PaymentRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;


//  CONFIGURATION ON THE APPLICATION.PROPERTIES

@Service
public class KafkaPaymentConsumer {
    @Autowired
    private  StudentRepo studentRepo;

    public static final Logger LOGGER = LoggerFactory.getLogger(KafkaPaymentConsumer.class);
    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )

    public void consume(PaymentRequestDto paymentRequestDto) {
        LOGGER.info(String.format("payment info recived > %s" ,paymentRequestDto.toString() ));



        Optional<Student> optionalStudent = Optional.ofNullable(studentRepo.findByNameAndMiddleName(paymentRequestDto.getName(), paymentRequestDto.getMiddleName()));

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            LOGGER.info(String.format("Student found in the database: %s", student.toString()));
            student.setPaymentStatus(PaymentStatus. SUCCESSFUL);
            studentRepo.save(student);
            LOGGER.info("Payment status updated to SUCCESS");
        } else {
            LOGGER.error("Student not found in the database. Aborting payment system.");



        }
    }
}


