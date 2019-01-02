package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.LeaveModel;
import yanmakes.employee_management.models.SalaryModel;

@Repository
public interface LeaveModelRepository extends JpaRepository<LeaveModel,Integer> {

}
