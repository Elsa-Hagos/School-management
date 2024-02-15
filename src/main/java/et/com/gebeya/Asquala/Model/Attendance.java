package et.com.gebeya.Asquala.Model;




import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.Date;
@Data
@NoArgsConstructor
@ToString
    @Entity
    @Table(name = "attendance")
    public class Attendance {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "student_id", referencedColumnName = "id")
        private Student student;

        @ManyToOne
        @JoinColumn(name = "teacher_id", referencedColumnName = "id")
        private Teacher teacher;

        @Column(name = "attendance_date")
        private Date attendanceDate;

        @Column(name = "status" ,length = 255)
        private String status;

        @Column(name = "remark",length = 255)
        private String remark;

        @Column(name = "created_on")
        private Timestamp createdOn;

        @Column(name = "updated_on")
        private Timestamp updatedOn;



}
