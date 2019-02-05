package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByEmployee(List<Employee> employee);

//    List<Task> findByEmployees(List<Employee> employee);
}
