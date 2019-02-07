package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.Utils.AttendaceType;
import yanmakes.employee_management.models.Attendance;
import yanmakes.employee_management.models.Employee;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

    List<Attendance> findByDateAndAttendance(LocalDate date, boolean b);

    List<Attendance> findByEmployeeAndAttType(Employee employee, AttendaceType type);

    int countByEmployeeAndAttType(Employee employee, AttendaceType type);

    List<Attendance> findByEmployee(Employee one);

}
