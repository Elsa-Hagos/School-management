package et.com.gebeya.Asquala.Model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;
import java.sql.Timestamp;
import java.util.Set;
@Data
@NoArgsConstructor
@ToString
@Entity
    @Table(name = "grade_section")
    public class GradeSection {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "grade",length = 10)
        private String grade;

        @Column(name = "section",length = 10)
        private String section;
        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "home_room_teacher", referencedColumnName = "id", nullable = true)
        private Teacher homeRoomTeacher;

        @Column(name = "is_active" ,  nullable = false)
        private boolean isActive;

        @Column(name = "created_on")
        private Timestamp createdOn;

        @Column(name = "updated_on")
        private Timestamp updatedOn;

    @JsonIgnore
    @ManyToMany
    private Set<Student> students;



       @JsonIgnore
        @ManyToMany(fetch = FetchType.LAZY)
        private Set<Subject> subjects;

}
