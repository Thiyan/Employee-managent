package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.SalaryRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Salry;

import java.util.List;


@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


//    /**
//     * THIS METHOD IS FOR CREATING A SALARY RECORD/TUPLE FOR EMPLOYEES AT THE 1ST OF EVERY MONTH
//     * INTERNAL PURPOSE
//     * AUTOMATED METHOD
//     */
//    public String createSalarys(){
//
//        List<Employee> employees=new ArrayList<>();
//
//        String month = LocalDate.now().getMonth().toString() + "-" + String.valueOf(LocalDate.now().getYear());
//
//
//        try {
//            employees=employeeRepository.findByActive(true);
//        }catch (Exception e){
//            System.out.println(EMStatus.DB_ERROR);
//        }
//
//
//        for (Employee employee:employees){
//            Salry salary = new Salry(month,employee,"0");
//
//            try {
//                if(salaryRepository.findByEmployeeAndMonth(employee,month)!=null)
//                    salaryRepository.save(salary);
//            }catch (Exception e){
//                System.out.println(EMStatus.DB_ERROR);
//            }
//        }
//
//        return "Success";
//    }

    public Salry createSalary(Employee employee,String month){
        Salry salary = new Salry(month,employee,0);
            try {
                if(salaryRepository.findByEmployeeAndMonth(employee,month)!=null)
                    salary=salaryRepository.save(salary);
            }catch (Exception e){
                System.out.println(EMStatus.DB_ERROR);
            }

        return salary;
        }



    public List<Salry> getSalary(String id) throws EMException {

        List<Salry> salries;
        try {
            Employee employee=employeeRepository.findByUserId(id);
            salries=salaryRepository.findByEmployeeOrderBySalaryIdDesc(employee);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(salries.isEmpty())
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return salries;

    }
}


