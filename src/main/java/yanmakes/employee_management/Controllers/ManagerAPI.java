package yanmakes.employee_management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.Services.*;
import yanmakes.employee_management.Utils.EMResponse;
import yanmakes.employee_management.models.LeaveRequest;
import yanmakes.employee_management.models.Task;

import javax.ws.rs.Produces;

@RestController
@CrossOrigin(value = "*")
@Produces("Application/json")
@RequestMapping(value = "/manager")
public class ManagerAPI {

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
    private LeaveService leaveService;

    @Autowired
    private SalaryService salaryService;


    @PostMapping("/add-task")
    public EMResponse addTask(@RequestBody Task task) throws EMException {

//        System.out.println(program.toString());
        if(!task.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        System.out.println("hhgJ");

        return new EMResponse(taskService.addTask(task));
    }



    @PutMapping("/add-reply")
    public EMResponse addReply(@RequestBody LeaveRequest leaveRequest) throws EMException {
//        System.out.println(leaveRequest.toString());
        return new EMResponse(leaveRequestService.addLeaveReply(leaveRequest));
    }

//    @GetMapping("/leaves")
//    public EMResponse createLeaves() throws EMException {
////        System.out.println(program.toString());
//        return new EMResponse(leaveService.createLeaves());
//    }

//    @GetMapping("/salary")
//    public EMResponse createsalary() throws EMException {
////        System.out.println(program.toString());
//        return new EMResponse(salaryService.createSalary());
//    }

    @GetMapping("/requests")
    public EMResponse getRequests() throws EMException {
        return new EMResponse(leaveRequestService.getRequestsByChecked());
    }

    @GetMapping("/my-reply")
    public EMResponse getReply(@RequestParam("id") String id) throws EMException {

        return new EMResponse(leaveRequestService.getReply(id));
    }


}
