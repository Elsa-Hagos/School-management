package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance , Long> {
}
