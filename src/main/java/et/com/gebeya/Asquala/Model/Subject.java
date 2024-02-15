package et.com.gebeya.Asquala.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import lombok.*;
@Data
@NoArgsConstructor
@ToString
@Entity
    @Table(name = "subject")
    public class Subject {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name" , length = 255)
        private String name;

        @Column(name = "is_active" ,  nullable = false)
        private boolean isActive;

        @Column(name = "created_on")
        private Timestamp createdOn;

        @Column(name = "updated_on")
        private Timestamp updatedOn;


        @JsonIgnore
        @ManyToMany(mappedBy = "subjects" ,fetch = FetchType.LAZY)
        private Set<GradeSection> gradeSections;

    }
