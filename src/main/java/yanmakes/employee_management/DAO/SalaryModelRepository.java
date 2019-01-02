package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Attendance;
import yanmakes.employee_management.models.SalaryModel;

@Repository
public interface SalaryModelRepository extends JpaRepository<SalaryModel,Integer> {

}
