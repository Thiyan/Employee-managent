package yanmakes.employee_management.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yanmakes.employee_management.models.Notice;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

}
