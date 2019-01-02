package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.LeaveRequestRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.LeaveRequest;


@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveService leaveService;


    public LeaveRequest addLeaveRequest(LeaveRequest leaveRequest) throws EMException {

        String month=leaveRequest.getDates().get(0).getMonth().toString() +"-"+ String.valueOf(leaveRequest.getDates().get(0).getYear());

        if(leaveService.canMakeLeave(leaveRequest.getRequestedBy(),month)>=leaveRequest.getDates().size()){
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

    public LeaveRequest addLeaveReply(LeaveRequest leaveRequest) throws EMException {

        LeaveRequest one;
        try {
            one = leaveRequestRepository.getOne(leaveRequest.getRequestId());
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(leaveRequest.getRequestedDate()==one.getRequestedDate() && leaveRequest.getRequestedDate()==one.getRequestedDate()
                && leaveRequest.getReason() == one.getReason() &&leaveRequest.getRequestedBy()==one.getRequestedBy()
                &&leaveRequest.getRequestId() ==one.getRequestId()){

            leaveRequest.setChecked(true);
        }
        else
            throw  new EMException(EMStatus.NO_REQUEST);

        if (leaveRequest.getApprovedBy() == null || leaveRequest.isChecked()==false
                || String.valueOf(leaveRequest.getDays()) == null)
            throw new EMException(EMStatus.MISSING_REQUIRED_PARAMS);

        try {
            leaveRequest=leaveRequestRepository.save(leaveRequest);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return leaveRequest;
    }
}
