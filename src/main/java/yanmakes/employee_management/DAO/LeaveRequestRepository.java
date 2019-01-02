package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.LeaveRequest;
import yanmakes.employee_management.models.Leaves;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {

}
