package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.TaskRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Task;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * THIS METHOD IS FOR ADDING A NEW TASK BY MANAGERIAL PERSONS
     * @param task
     * @return
     * @throws EMException
     */
    public Task addTask(Task task) throws EMException {

        task.setActive(true);
        task.setCreatedDate(java.time.LocalDateTime.now());

        System.out.println(java.time.LocalDateTime.now());

        System.out.println(task.toString());
        List<Employee> employees=new ArrayList<>();

        try {

            for (Employee e:task.getEmployess()){
                employees.add(employeeRepository.getOne(e.geteId()));
            }

            task.setCreatedBy(employeeRepository.getOne(task.getCreatedBy().geteId()));


        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        task.setEmployess(employees);

        System.out.println(task.toString());

        if (!task.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        try {
           task = taskRepository.save(task);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return task;
    }

}
