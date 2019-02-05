package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.LeaveRequest;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {

    List<LeaveRequest> findAllByRequestedByOrderByRequestIdDesc(Employee employee);


    List<LeaveRequest> findByChecked(boolean b);

    List<LeaveRequest> findAllByApprovedByOrderByRequestIdDesc(Employee employee);
}
