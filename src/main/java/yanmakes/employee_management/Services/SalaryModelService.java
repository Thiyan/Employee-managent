package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.SalaryModelRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.SalaryModel;

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

}
