package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.LeaveModelRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.LeaveModel;

@Service
public class LeaveModelService {

    @Autowired
    private LeaveModelRepository leaveModelRepository;


    /**
     * THIS METHOD IS FOR ADDING A NEW LEAVE MODEL BY ADMIN
     * @param leaveModel
     * @return
     * @throws EMException
     */
    public LeaveModel addLeaveModel(LeaveModel leaveModel) throws EMException {

        try {
           leaveModel = leaveModelRepository.save(leaveModel);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return leaveModel;
    }

}
