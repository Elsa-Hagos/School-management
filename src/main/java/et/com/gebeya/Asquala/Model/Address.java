package et.com.gebeya.Asquala.Model;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "sub_city", length = 255)
    private String subCity;

    @Column(name = "wereda", length = 255)
    private String wereda;

    @Column(name = "house_number", length = 255)
    private String houseNumber;

    @Column(name = "is_active")
    private Boolean isActive;


    @Column(name = "created_on")
   // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createdOn;

    @Column(name = "updated_on")
  //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant updatedOn;


}
