package et.com.gebeya.Asquala.Dto;

import et.com.gebeya.Asquala.Model.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class StudentCreationDTO {
    private Student student;
    private Guardian guardian;
    private Address address;
    private PhoneNumber phoneNumber;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
