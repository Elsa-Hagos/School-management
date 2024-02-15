package et.com.gebeya.Asquala.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;



import java.security.Timestamp;
import java.time.Instant;
import java.util.*;


@Data
@NoArgsConstructor
@ToString

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "middle_name" , length = 255)
    private String middleName;

    @Column(name = "last_name" , length = 255)
    private String LastName;


    @Column(name = "dob")
    private Date dob;

    @Column(name = "student_id", length = 50)
    private String studentId;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "is_active")
    private Boolean isActive;


    @Column(name = "created_on")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createdOn;

    @Column(name = "updated_on")
    //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant updatedOn;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Guardian> guardians;

    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentStatus" )
    private PaymentStatus paymentStatus;

  //  private PaymentStatus paymentStatus''



    //  @JsonIgnore
  //  @ManyToMany(fetch = FetchType.LAZY)
  //  private Set<GradeSection> gradeSections;


}


