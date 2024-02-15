package et.com.gebeya.Asquala.Model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@NoArgsConstructor
@ToString
    @Entity
    @Table(name = "phone_number")
    public class PhoneNumber {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "phone_number" , length = 255)
        private Long phoneNumber;

       // @ManyToOne
       // @JsonIgnore
        //@JoinColumn(name = "address_id", referencedColumnName = "id")
        //private Address address;


    @Column(name = "is_active")
        private boolean isActive;

        @Column(name = "created_on")
       // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Instant createdOn;

        @Column(name = "updated_on")
      //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Instant updatedOn;





    public boolean getIsActive() {
        return false;
    }

    public void setIsActive(boolean b) {
    }
}


