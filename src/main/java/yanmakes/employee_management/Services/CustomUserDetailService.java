package yanmakes.employee_management.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.CustomUserDetails;
import yanmakes.employee_management.models.Employee;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger LOGGER= LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        LOGGER.info("LoadByUser method called {}"+userId);
        Employee employee = new Employee();

        try {
            employee=employeeRepository.findByUserId(userId);
            System.out.println(employee.toString());
        }catch (Exception ex){
            LOGGER.error(EMStatus.DB_ERROR.getStatusDescription());
        }
//        user
//                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
//        return user.
//                map(CustomUserDetails::new).get();

        if(employee!=null)
            return new CustomUserDetails(employee);
        else
            throw new UsernameNotFoundException("User name not found");
    }
}
