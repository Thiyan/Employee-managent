package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.SalaryRepository;
import yanmakes.employee_management.DAO.TaskRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Salry;
import yanmakes.employee_management.models.Task;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;




  /*  public List<Employee> getAllEmployee() throws EMException{

        List<Employee> employees;
        try{
            employees= employeeRepository.findByActive(true || false);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if (employees.isEmpty() || employees==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return employees;
    }

    public Employee updateEmployee(Employee employee) throws EMException {
        try {
            employee=employeeRepository.save(employee);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return employee;

    }*/
}
