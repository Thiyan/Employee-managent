package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.LeaveModelRepository;
import yanmakes.employee_management.DAO.LeavesRepository;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Leaves;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeavesRepository leavesRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveModelRepository leaveModelRepository;


    /**
     * THIS METHOD IS FOR CREATING A LEAVE RECORD/TUPLE FOR EMPLOYEES AT THE 1ST OF EVERY MONTH
     * INTERNAL PURPOSE
     * AUTOMATED METHOD
     */

    public String createLeaves(){

        List<Employee> employees=new ArrayList<>();

        String month =LocalDate.now().getMonth().toString() + "-" + String.valueOf(LocalDate.now().getYear());

        System.out.println(month);

        try {
            employees=employeeRepository.findByActive(true);
        }catch (Exception e){
            System.out.println(EMStatus.DB_ERROR);
        }
        System.out.println(employees);


        for (Employee employee:employees){
            Leaves leave = new Leaves(month,employee,0);

            try {
                if(leavesRepository.findByEmployeeAndMonth(employee,month)!=null)
                    leavesRepository.save(leave);
            }catch (Exception e){
                System.out.println(EMStatus.DB_ERROR);
            }
        }

        return "Success";
    }


    /**
     * THIS METHOD IS FOR CHECK THE REMAINING LEAVES FOR EMPLOYEE WHILE HE/SHE IS REQUESTING FOR A LEAVE
     * USED BY LeaveRequestService
     * @param employee
     * @param month
     * @return
     */
    public int canMakeLeave(Employee employee,String month){

        int taken=0,allowed = 0;

        try {
            taken=leavesRepository.findByEmployeeAndMonth(employee,month).getTaken();
            allowed=leaveModelRepository.getOne(employee.getLeaveModel().getlId()).getDaysPerMonth();

        }catch (Exception e){
            System.out.println(EMStatus.DB_ERROR);
        }


        return allowed-taken;
    }

}
