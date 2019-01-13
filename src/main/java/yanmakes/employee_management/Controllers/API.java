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

    @Autowired
    private SalaryService salaryService;


    @PutMapping("/update-employee")
    public EMResponse updateEmployee(@RequestBody Employee employee) throws EMException {
//        System.out.println(program.toString());
        return new EMResponse(employeeService.updateEmployee(employee));
    }


    @PostMapping("/attendance")
    public EMResponse addAttendance(@RequestBody Attendance attendance,@RequestParam("time") AttendaceTimeType time) throws EMException {

        System.out.println("In api");

        if (!attendance.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(attendanceService.addAttendance(attendance,time));
    }


    @GetMapping("my-salary")
    public EMResponse getSalary(@RequestParam("id") int id) throws EMException {

        return new EMResponse(salaryService.getSalary(id));
    }

    @GetMapping("my-attendance")
    public EMResponse getAttendance(@RequestParam("id") int id) throws EMException {

        return new EMResponse(attendanceService.getAttendance(id));
    }


//    @GetMapping("my-task")
//    public EMResponse getTasks(@RequestParam("id") int id) throws EMException {
//
//        return new EMResponse(taskService.getTask(id));
//    }
    
    @PostMapping("/add-request")
    public EMResponse addRequest(@RequestBody LeaveRequest request) throws EMException {

//        System.out.println(program.toString());

        if (!request.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(leaveRequestService.addLeaveRequest(request));
    }

}
