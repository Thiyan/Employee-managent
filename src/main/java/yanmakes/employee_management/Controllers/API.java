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


    @PostMapping("/arrival")
    public EMResponse arrival(@RequestBody Attendance attendance) throws EMException {

        System.out.println("In api");

        if (!attendance.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(attendanceService.addAttendance(attendance,AttendaceTimeType.AR));
    }

    @PostMapping("/departure")
    public EMResponse departure(@RequestBody Attendance attendance) throws EMException {

        System.out.println("In api");

        if (!attendance.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(attendanceService.addAttendance(attendance,AttendaceTimeType.DP));
    }


    @GetMapping("my-salary")
    public EMResponse getSalary(@RequestParam("id") String id) throws EMException {

        return new EMResponse(salaryService.getSalary(id));
    }

    @GetMapping("my-attendance")
    public EMResponse getAttendance(@RequestParam("id") int id) throws EMException {

        return new EMResponse(attendanceService.getAttendance(id));
    }

    @GetMapping("attendances")
    public EMResponse getAttendances() throws EMException {

        return new EMResponse(attendanceService.getAttendances());
    }

    @GetMapping("my-requests")
    public EMResponse getRequests(@RequestParam("id") String id) throws EMException {

        return new EMResponse(leaveRequestService.getRequests(id));
    }


    @GetMapping("my-task")
    public EMResponse getTasks(@RequestParam("id") String id) throws EMException {

        return new EMResponse(taskService.getTask(id));
    }

    @PostMapping("/add-request")
    public EMResponse addRequest(@RequestBody LeaveRequest request) throws EMException {

        if (!request.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(leaveRequestService.addLeaveRequest(request));
    }

    @PostMapping("/change-password")
    public EMResponse ChangePassword(@RequestParam("user") String userId,@RequestParam("cur") String cur, @RequestParam("password") String password) throws EMException {
        return new EMResponse(employeeService.changePassword(userId,cur,password));
    }


    @PostMapping("/login")
    public EMResponse login(@RequestParam("user") String userId, @RequestParam("password") String password) throws EMException {

        return new EMResponse(employeeService.login(userId,password));
    }



}
