package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.time.temporal.ChronoUnit.*;
import yanmakes.employee_management.DAO.AttendanceRepository;
import yanmakes.employee_management.DAO.EmployeeRepository;
import yanmakes.employee_management.DAO.SalaryRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.Utils.AttendaceTimeType;
import yanmakes.employee_management.Utils.AttendaceType;
import yanmakes.employee_management.models.Attendance;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Salry;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    /**
     * THIS METHOD IS FOR ADDING A DAILY ATTENDANCE
     * @param attendance
     * @param type(ARRIVAL,DEPARTURE)
     * @return
     * @throws EMException
     */
    public Attendance addAttendance(Attendance attendance, AttendaceTimeType type) throws EMException {

        if(attendance.isAttendance()){

            if(type== AttendaceTimeType.AR){
                attendance.setDate(java.time.LocalDate.now());
                attendance.setArrival(java.time.LocalTime.now());
            }
            else if (type== AttendaceTimeType.DP){
                if(String.valueOf(attendance.getaId()) != null && attendance.getDate() != null
                    && attendance.getArrival() != null)
                        attendance.setDeparture(java.time.LocalTime.now());
                else
                    throw new EMException(EMStatus.NO_ARRIVAL);
            }

        }
        else {
            if(String.valueOf(attendance.getaId()) == null && attendance.getDate() == null)
                attendance.setDate(java.time.LocalDate.now());
        }

        try {
           attendance = attendanceRepository.save(attendance);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return attendance;
    }


    /**
     * THIS METHOD IS FOR DETERMINE WHETHER THE DAY IS HALF OR FULL FOR  EMPLOYEES ACCORDING
     * THEIR WORKING HOURS
     * WORKING HOUR IS CALCULATE BY DEPARTURE TIME - ARRIVAL TIME
     * INTERNAL PURPOSE
     * AUTOMATED METHOD
     */
    public void calculateAttendance(){
        List<Attendance> attendances=new ArrayList<>();
        try {
            attendances=attendanceRepository.findByDateAndAttendance(LocalDate.now(),true);
        }
        catch (Exception ex){
            System.out.println(EMStatus.DB_ERROR);
        }

        for(Attendance attendance:attendances){
            if( HOURS.between(attendance.getArrival(),attendance.getDeparture()) >=5)
                attendance.setAttType(AttendaceType.FL);

            else
                attendance.setAttType(AttendaceType.HF);

        }

    }


    /**
     * THIS METHOD IS FOR CALCULTE THE MONTHLY SALARY FOR EMPLOYEES
     * INTERNAL PURPOSE
     * AUTOMATED METHOD
     */
    public void calculateSalary(){

        List<Employee> employees=new ArrayList<>();
        try {
            employees=employeeRepository.findByActive(true);
        }
        catch (Exception ex){
            System.out.println(EMStatus.DB_ERROR);
        }

        String month =LocalDate.now().getMonth().toString() + "-" + String.valueOf(LocalDate.now().getYear());

        for (Employee employee:employees){

            int fullDays = 0,halfDays=0;
            try {
                fullDays=attendanceRepository.countByEmployeeAndAttType(employee,AttendaceType.FL);

                halfDays=attendanceRepository.countByEmployeeAndAttType(employee,AttendaceType.HF);
            }
            catch (Exception ex){
                System.out.println(EMStatus.DB_ERROR);
            }

            double salary= Double.parseDouble(employee.getSalaryModel().getFullAmount()) * fullDays +

                            Double.parseDouble(employee.getSalaryModel().getHalfAmount()) * halfDays;

            Salry salry=new Salry(month,employee,String.valueOf(salary));

            try {
                salaryRepository.save(salry);
            }catch (Exception ex){
                System.out.println(EMStatus.DB_ERROR);
            }

        }

    }
}
