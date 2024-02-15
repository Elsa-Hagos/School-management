package et.com.gebeya.Asquala.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import lombok.*;
@Data
@NoArgsConstructor
@ToString
@Entity
    @Table(name = "guardian")
    public class Guardian {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name" , length = 255)
        private String Name;

    @Column(name = "middle_name" , length = 255)
    private String MiddleName;

    @Column(name = "last_name" , length = 255)
    private String LastName;


    @Column(name = "gender" , length = 1)
        private String gender;

        @Column(name = "is_active")
        private boolean isActive;
        @JsonIgnore
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address address;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_number_id", referencedColumnName = "id")
    private PhoneNumber phoneNumber;


    @Column(name = "created_on")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createdOn;

    @Column(name = "updated_on")
    //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant updatedOn;


    @JsonIgnore
    @ManyToMany(mappedBy = "guardians",fetch = FetchType.LAZY)
        private Set<Student> students;




    }
