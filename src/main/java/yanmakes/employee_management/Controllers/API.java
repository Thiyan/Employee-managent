package yanmakes.employee_management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.Services.*;
import yanmakes.employee_management.Utils.AttendaceTimeType;
import yanmakes.employee_management.Utils.EMResponse;
import yanmakes.employee_management.models.*;

import javax.ws.rs.Produces;

@RestController
@CrossOrigin(value = "*")
@Produces("Application/json")
@RequestMapping(value = "/")
public class API {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private SalaryModelService salaryModelService;

    @Autowired
    private LeaveModelService leaveModelService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private LeaveRequestService leaveRequestService;


    @PutMapping("/update-employee")
    public EMResponse updateEmployee(@RequestBody Employee employee) throws EMException {
//        System.out.println(program.toString());
        return new EMResponse(employeeService.updateEmployee(employee));
    }


    @PostMapping("/attendance/{time}")
    public EMResponse addAttendance(@RequestBody Attendance attendance,@PathVariable AttendaceTimeType time) throws EMException {

//        System.out.println(program.toString());

        if (!attendance.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(attendanceService.addAttendance(attendance,time));
    }

    @PostMapping("/add-task")
    public EMResponse addTask(@RequestBody Task task) throws EMException {

//        System.out.println(program.toString());

        if (!task.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(taskService.addTask(task));
    }

    @PostMapping("/add-request")
    public EMResponse addRequest(@RequestBody LeaveRequest request) throws EMException {

//        System.out.println(program.toString());

        if (!request.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(leaveRequestService.addLeaveRequest(request));
    }

}
