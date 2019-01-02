package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Attendance;
import yanmakes.employee_management.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

}
