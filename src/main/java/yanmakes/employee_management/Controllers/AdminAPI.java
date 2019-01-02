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
@RequestMapping(value = "/admin")
public class AdminAPI {

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

    @PostMapping("/add-employee")
    public EMResponse addEmployee(@RequestBody Employee employee) throws EMException {

//        System.out.println(program.toString());

//        if (!employee.isValid())
//            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(employeeService.addEmployee(employee));
    }

    @GetMapping("/employees")
    public EMResponse getAllEmployee() throws EMException {

//        System.out.println(program.toString());


        return new EMResponse(employeeService.getAllEmployee(true || false));
    }

//    @PutMapping("/update-employee")
//    public EMResponse updateEmployee(@RequestBody Employee employee) throws EMException {
////        System.out.println(program.toString());
//
//        if(String.valueOf(employee.geteId()) == null)
//            throw new EMException(EMStatus.ID_IS_REQUIRED);
//
//        return new EMResponse(employeeService.updateEmployee(employee));
//    }

    @PostMapping("/salary-model")
    public EMResponse addSalaryModel(@RequestBody SalaryModel salaryModel) throws EMException {

//        System.out.println(program.toString());

        if (!salaryModel.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(salaryModelService.addSalaryModel(salaryModel));
    }


    @PostMapping("/leave-model")
    public EMResponse addLeaveModel(@RequestBody LeaveModel leaveModel) throws EMException {

//        System.out.println(program.toString());

        if (!leaveModel.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(leaveModelService.addLeaveModel(leaveModel));
    }


}
