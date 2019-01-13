package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.SalaryModelRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.SalaryModel;

import java.util.List;

@Service
public class SalaryModelService {

    @Autowired
    private SalaryModelRepository salaryModelRepository;

    /**
     * THIS METHOD IS FOR ADDING NEW SALARY MODELS BY ADMIN
     * @param salaryModel
     * @return
     * @throws EMException
     */
    public SalaryModel addSalaryModel(SalaryModel salaryModel) throws EMException {

        System.out.println(salaryModel.toString());

        try {
           salaryModel = salaryModelRepository.save(salaryModel);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return salaryModel;
    }

    /**
     * THIS METHOD IS FOR GET ALL SALARY MODEL BY ADMIN
     * @return
     * @throws EMException
     */
    public List<SalaryModel> getSalaryModels() throws EMException {

        List<SalaryModel> salaryModels;
        try {
            salaryModels=salaryModelRepository.findAll();
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if (salaryModels.isEmpty() || salaryModels==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return salaryModels;
    }


    /**
     * THIS METHOD IS FOR DELETING A SALARY MODEL BY ADMIN
     * @param id
     * @return
     * @throws EMException
     */
    public SalaryModel delete(String id) throws EMException {

        SalaryModel salaryModel;

        try {
            salaryModel=salaryModelRepository.getOne((int) (Integer.valueOf(id)));
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }


        if (salaryModel==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        try {
            salaryModelRepository.delete(salaryModel);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        System.out.println(salaryModel.toString());
        return salaryModel;

    }

    /**
     * THIS METHOD IS FOR ADDING A NEW SALARY MODEL BY ADMIN
     * @param salaryModel
     * @return
     * @throws EMException
     */
    public SalaryModel update(SalaryModel salaryModel) throws EMException {

        try {
            salaryModelRepository.getOne(salaryModel.getsId());
        }catch (Exception ex){
            throw new EMException(EMStatus.ID_IS_REQUIRED);
        }


        try {
            salaryModel=salaryModelRepository.save(salaryModel);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return salaryModel;
    }

}
