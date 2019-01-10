package yanmakes.employee_management.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import yanmakes.employee_management.Utils.EMResponse;

import javax.ws.rs.Produces;

//@RestController
@ControllerAdvice
@Produces("Application/json")
class ExceptionController {

    @ResponseBody
    @ExceptionHandler(value = EMException.class)
    public EMResponse customExceptionHandler(EMException ex){
        System.out.println("In Exception handler");
        EMResponse response=new EMResponse(ex.getStatus());
        return response;
    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public EMResponse ExceptionHandler(Exception ex){
        System.out.println("In Exception handler");

        EMResponse response=new EMResponse(EMStatus.ERROR);
        return response;
    }
}
