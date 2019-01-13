package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.LeaveModelRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.LeaveModel;

import java.util.List;

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

    public List<LeaveModel> getLeaveModels() throws EMException {

        List<LeaveModel> leaveModels;
        try {
            leaveModels=leaveModelRepository.findAll();
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(leaveModels.isEmpty() || leaveModels==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return leaveModels;
    }

    /**
     * THIS METHOD IS FOR DELETING A LEAVE MODEL BY ADMIN
     * @param id
     * @return
     * @throws EMException
     */
    public LeaveModel delete(String id) throws EMException {

        LeaveModel leaveModel;

        try {
            leaveModel=leaveModelRepository.getOne((int) (Integer.valueOf(id)));
        }catch (Exception ex){
            System.out.println(ex);
            throw new EMException(EMStatus.DB_ERROR);
        }


        if (leaveModel==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        try {
            leaveModelRepository.delete(leaveModel);
        }catch (Exception ex){
            System.out.println(ex);
            throw new EMException(EMStatus.DB_ERROR);
        }

        return leaveModel;
    }

    /**
     * THIS METHOD IS FOR UPDATING NEW LEAVE MODEL BY ADMIN
     * @param leaveModel
     * @return
     * @throws EMException
     */
    public LeaveModel update(LeaveModel leaveModel) throws EMException {

        try {
            leaveModelRepository.getOne(leaveModel.getlId());
        }catch (Exception ex){
            throw new EMException(EMStatus.ID_IS_REQUIRED);
        }


        try {
            leaveModel=leaveModelRepository.save(leaveModel);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return leaveModel;
    }
}
