package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByActive(boolean b);

//    boolean findByUserId(String userId);

    Employee findByUserId(String userId);
}
