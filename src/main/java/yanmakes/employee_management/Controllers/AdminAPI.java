package yanmakes.employee_management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.Services.*;
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

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private PositionService positionService;

    @PostMapping("/add-employee")
    public EMResponse addEmployee(@RequestBody Employee employee) throws EMException {

       System.out.println(employee.toString());

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

    @GetMapping("/leave-models")
    public EMResponse getLeaveModels() throws EMException {

//        System.out.println(program.toString());


        return new EMResponse(leaveModelService.getLeaveModels());
    }


    @GetMapping("/salry-models")
    public EMResponse getSalaryModels() throws EMException {

//        System.out.println(program.toString());


        return new EMResponse(salaryModelService.getSalaryModels());
    }

    @PostMapping("/new-notice")
    public EMResponse addNotice(@RequestBody Notice notice) throws EMException {

//        System.out.println(program.toString());

        if (!notice.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(noticeService.addNotice(notice));
    }

    @GetMapping("/notices")
    public EMResponse getNotices() throws EMException {

//        System.out.println(program.toString());


        return new EMResponse(noticeService.getNotices());
    }

    @PostMapping("/new-position")
    public EMResponse addPosition(@RequestBody Position position) throws EMException {

//        System.out.println(program.toString());

        if (!position.isValid())
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        return new EMResponse(positionService.addPosition(position));
    }

    @GetMapping("/positions")
    public EMResponse getPositions() throws EMException {

//        System.out.println(program.toString());


        return new EMResponse(positionService.getPostions());
    }

    @DeleteMapping("/delete-employees")
    public EMResponse deleteEmployee(@RequestParam("id") String id) throws EMException {

        System.out.println(id);


        return new EMResponse(employeeService.deleteEmployee(id));
    }

    @DeleteMapping("/delete-salary-model")
    public EMResponse deleteSalaryModel(@RequestParam("id") String id) throws EMException {

        System.out.println(id);


        return new EMResponse(salaryModelService.delete(id));
    }

    @DeleteMapping("/delete-leave-model")
    public EMResponse deleteLeaveModel(@RequestParam("id") String id) throws EMException {

        System.out.println(id);


        return new EMResponse(leaveModelService.delete(id));
    }

    @DeleteMapping("/delete-notice")
    public EMResponse deleteNotice(@RequestParam("id") String id) throws EMException {

        System.out.println(id);


        return new EMResponse(noticeService.delete(id));
    }

    @DeleteMapping("/delete-position")
    public EMResponse deletePosition(@RequestParam("id") String id) throws EMException {

        System.out.println(id);


        return new EMResponse(positionService.delete(id));
    }


    @PutMapping("/update-salary-model")
    public EMResponse updateSalary(@RequestBody SalaryModel salaryModel) throws EMException {
        System.out.println(salaryModel.toString());
        return new EMResponse(salaryModelService.update(salaryModel));
    }

    @PutMapping("/update-leave-model")
    public EMResponse updateLeaveModel(@RequestBody LeaveModel leaveModel) throws EMException {
        System.out.println(leaveModel.toString());
        return new EMResponse(leaveModelService.update(leaveModel));
    }

    @PutMapping("/update-positions")
    public EMResponse updatepositions(@RequestBody Position position) throws EMException {
        System.out.println(position.toString());
        return new EMResponse(positionService.update(position));
    }

    @PutMapping("/update-notice")
    public EMResponse updateNotice(@RequestBody Notice notice) throws EMException {
        System.out.println(notice.toString());
        return new EMResponse(noticeService.update(notice));
    }

}
