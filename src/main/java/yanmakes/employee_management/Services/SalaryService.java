package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.SalaryRepository;


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
