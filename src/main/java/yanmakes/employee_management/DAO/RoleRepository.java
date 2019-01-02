package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import yanmakes.employee_management.models.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
