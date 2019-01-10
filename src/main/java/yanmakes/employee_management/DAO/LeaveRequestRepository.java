package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {

}
