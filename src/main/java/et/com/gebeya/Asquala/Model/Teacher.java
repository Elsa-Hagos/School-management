package et.com.gebeya.Asquala.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "middle_name", length = 255)
    private String middleName;

    @Column(name = "last_name", length = 255)
    private String LastName;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "qualification", length = 255)
    private String qualification;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

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
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Subject> Subject;


}


