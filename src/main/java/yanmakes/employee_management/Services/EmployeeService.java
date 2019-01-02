package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.LeaveModelRepository;
import yanmakes.employee_management.DAO.RoleRepository;
import yanmakes.employee_management.DAO.SalaryModelRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.Utils.UserCredentials;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SalaryModelRepository salaryModelRepository;

    @Autowired
    private LeaveModelRepository leaveModelRepository;

    /**
     * THIS METHOD IS FOR ADDING A NEW EMPLOYEE BY ADMIN
     * @param employee
     * @return
     * @throws EMException
     */
    public Employee addEmployee(Employee employee) throws EMException {

        Random rn = new Random();

        employee.setUserId(employee.getlName()+rn.nextInt(500) + 1);
        employee.setPassword(passwordEncoder.encode(UserCredentials.password));
        employee.setActive(true);

        Set<Role> roles=new HashSet<Role>();

        for(Role r:employee.getRoles()){
            roles.add(roleRepository.getOne(r.getrId()));
        }
//        roles.add(roleRepository.getOne(1));
//        roles.add(roleRepository.getOne(2));
//        roles.add(roleRepository.getOne(3));

        employee.setRoles(roles);

        employee.setSalaryModel(salaryModelRepository.getOne(employee.getSalaryModel().getsId()));
        employee.setLeaveModel(leaveModelRepository.getOne(employee.getLeaveModel().getlId()));

        System.out.println(employee.toString());
        try {
           employee = employeeRepository.save(employee);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return employee;
    }


    /**
     * THIS METHOD IS FOR GET THE ALL ACTIVE AND NON-ACTIVE EMPLOYEES BY ADMIN
     * @param active
     * @return
     * @throws EMException
     */
    public List<Employee> getAllEmployee(boolean active) throws EMException{

        List<Employee> employees;
        try{
            employees= employeeRepository.findByActive(active);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if (employees.isEmpty() || employees==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return employees;
    }


    /**
     * THIS METHOD IS FOR UPDATING A EMPLOYEE
     * @param employee
     * @return
     * @throws EMException
     */
    public Employee updateEmployee(Employee employee) throws EMException {
        try {
            employee=employeeRepository.save(employee);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return employee;

    }
}