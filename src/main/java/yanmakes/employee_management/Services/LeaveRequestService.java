package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.LeaveRequestRepository;
import yanmakes.employee_management.DAO.LeavesRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.Utils.EMDate;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.LeaveRequest;
import yanmakes.employee_management.models.Leaves;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeavesRepository leavesRepository;

    @Autowired
    private EMDate emDate;

    /**
     * THIS METHOD IS FOR ADDING A NEW LEAVE REQUEST BY EMPLOYEE
     * @param leaveRequest
     * @return
     * @throws EMException
     */
    public LeaveRequest addLeaveRequest(LeaveRequest leaveRequest) throws EMException {

        System.out.println(leaveRequest.toString());
        String month=LocalDate.parse(leaveRequest.getDate()).getMonth().toString() +"-"+ String.valueOf(LocalDate.parse(leaveRequest.getDate()).getYear());

        System.out.println(month);
        leaveRequest.setRequestedBy(employeeRepository.getOne(leaveRequest.getRequestedBy().geteId()));
        leaveRequest.setRequestedDate(emDate.currentTimestamp());

        System.out.println(leaveRequest.toString());
        int a=leaveService.canMakeLeave(leaveRequest.getRequestedBy(),month);
        System.out.println(a);
        System.out.println(a);

        if(a>0){
            leaveRequest.setChecked(false);

            try {
                 return leaveRequestRepository.save(leaveRequest);
            }
            catch (Exception ex){
                throw new EMException(EMStatus.DB_ERROR);
            }
        }

        else
            throw new EMException(EMStatus.EXCEEDED_REQUESTE);
    }

    /**
     * THIS METHOD IS FOR GIVING A  REPLY FOR LEAVE REQUEST BY MANAGERS
     * @param leaveRequest
     * @return
     * @throws EMException
     */
    public LeaveRequest addLeaveReply(LeaveRequest leaveRequest) throws EMException {

//        System.out.println(leaveRequest.toString());
        leaveRequest.setChecked(true);
        leaveRequest.setDate(leaveRequest.getDate());

        leaveRequest.setApprovedBy(employeeRepository.getOne(leaveRequest.getApprovedBy().geteId()));

        System.out.println(leaveRequest.toString());

        if (leaveRequest.getApprovedBy()==null || leaveRequest.isChecked()==false)
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        String month=LocalDate.parse(leaveRequest.getDate()).getMonth().toString() +"-"+
                String.valueOf(LocalDate.parse(leaveRequest.getDate()).getYear());

        System.out.println(month);
        try {
            leaveRequest=leaveRequestRepository.save(leaveRequest);

            System.out.println(leaveRequest);
            System.out.println("ghfd");
            Leaves leave=leavesRepository.findByEmployeeAndMonth(leaveRequest.getRequestedBy(),month);

            leave.setTaken(leave.getTaken()+1);

            leavesRepository.save(leave);

        }catch (Exception ex){
//            throw new EMException(EMStatus.DB_ERROR);
            ex.printStackTrace();
        }

        return leaveRequest;
    }


    public List<LeaveRequest> getRequests(String id) throws EMException {


        List<LeaveRequest> leaveRequests=new ArrayList<>();
        try{
            Employee employee=employeeRepository.findByUserId(id);
            System.out.println(employee.toString());
            leaveRequests=leaveRequestRepository.findAllByRequestedByOrderByRequestIdDesc(employee);
        }catch(Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
//            ex.printStackTrace();
        }

        return leaveRequests;
    }

    public List<LeaveRequest> getRequestsByChecked() throws EMException {

        try {
            return leaveRequestRepository.findByChecked(false);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }
    }

    public List<LeaveRequest> getReply(String id) throws EMException {

        try {
            Employee employee=employeeRepository.findByUserId(id);
            return leaveRequestRepository.findAllByApprovedByOrderByRequestIdDesc(employee);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }
    }
}
