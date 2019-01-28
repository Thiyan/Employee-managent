package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.LeaveRequest;

import javax.validation.constraints.Null;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {

    List<LeaveRequest> findByRequestedBy(Employee employee);
}
