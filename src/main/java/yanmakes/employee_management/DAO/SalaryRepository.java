package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Salry;
import yanmakes.employee_management.models.Task;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salry,Integer> {

    Salry findByEmployeeAndMonth(Employee employee, String month);

    List<Salry> findByEmployeeOrderBySalaryIdDesc(Employee employee);
}
