package et.com.gebeya.Asquala.Model;


import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@ToString

@Entity
    @Table(name = "admin")
    public class Admin {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name", length = 255)
        private String firstName;

        @Column(name = "middle_name", length = 255)
        private String middleName;

        @Column(name = "last_name", length = 255)
        private String lastName;

        @Column(name = "gender", length = 1)
        private String gender;

        @Column(name = "is_active")
        private Boolean isActive;

        @OneToOne
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address address;

        @Column(name = "created_on")
        private Timestamp createdOn;

        @Column(name = "updated_on")
        private Timestamp updatedOn;
}
