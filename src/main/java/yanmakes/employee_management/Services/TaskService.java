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

        System.out.println(task.toString());
//        List<Employee> employees=new ArrayList<>();

        try {

            task.setEmployee(employeeRepository.getOne(task.getEmployee().geteId()));

            task.setCreatedBy(employeeRepository.getOne(task.getCreatedBy().geteId()));
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        System.out.println(task.toString());

        if (!task.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        try {
           task = taskRepository.save(task);
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new EMException(EMStatus.DB_ERROR);
        }

        return task;
    }

    public List<Task> getTask(String id) throws EMException {

        List<Task> task;
        try {
            List<Employee> employee=new ArrayList<>();
            employee.add(employeeRepository.findByUserId(id));

            task=taskRepository.findByEmployee(employee);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(task.isEmpty())
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return task;
    }
}
