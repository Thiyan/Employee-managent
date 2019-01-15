package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.LeaveRequestRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.LeaveRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveService leaveService;

    /**
     * THIS METHOD IS FOR ADDING A NEW LEAVE REQUEST BY EMPLOYEE
     * @param leaveRequest
     * @return
     * @throws EMException
     */
    public LeaveRequest addLeaveRequest(LeaveRequest leaveRequest) throws EMException {

        String month=LocalDate.parse(leaveRequest.getDates().get(0)).getMonth().toString() +"-"+ String.valueOf(LocalDate.parse(leaveRequest.getDates().get(0)).getYear());

        leaveRequest.setRequestedBy(employeeRepository.getOne(leaveRequest.getRequestedBy().geteId()));
        leaveRequest.setRequestedDate(LocalDateTime.now());

        int a=leaveService.canMakeLeave(leaveRequest.getRequestedBy(),month);

        if(a>=leaveRequest.getDates().size()){
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

        LeaveRequest one;
        try {
            one = leaveRequestRepository.getOne(leaveRequest.getRequestId());
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        System.out.println(leaveRequest.toString());
        if(leaveRequest.getRequestId() ==one.getRequestId()){

            leaveRequest.setChecked(true);
        }
        else
            throw  new EMException(EMStatus.NO_REQUEST);

        leaveRequest.setApprovedBy(employeeRepository.getOne(leaveRequest.getApprovedBy().geteId()));

        if (leaveRequest.getApprovedBy()==null || leaveRequest.isChecked()==false
                || String.valueOf(leaveRequest.getDays()).equals(""))
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        try {
            leaveRequest=leaveRequestRepository.save(leaveRequest);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return leaveRequest;
    }
}
