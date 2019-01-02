package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Leaves;
import yanmakes.employee_management.models.Task;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves,Integer> {

    Leaves findByEmployeeAndMonth(Employee employee, String month);
}
