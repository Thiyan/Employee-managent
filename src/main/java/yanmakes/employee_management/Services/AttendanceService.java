package yanmakes.employee_management.Services;

import org.joda.time.Duration;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
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
import yanmakes.employee_management.Utils.EMDate;
import yanmakes.employee_management.models.Attendance;
import yanmakes.employee_management.models.Employee;
import yanmakes.employee_management.models.Salry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private EMDate emDate;

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
                System.out.println("Hi222");
                attendance.setDate(emDate.currentDate());
                attendance.setArrival(emDate.currentTime());

            }
            else if (type== AttendaceTimeType.DP){


                if(!String.valueOf(attendance.getaId()).equals("") && !attendance.getDate().equals("")
                    && !attendance.getArrival().equals(""))
                        attendance.setDeparture(emDate.currentTime());
                else
                    throw new EMException(EMStatus.NO_ARRIVAL);
            }
        }
        else {
                attendance.setDate(emDate.currentTime());
                attendance.setAttType(AttendaceType.AB);
        }

        System.out.println(attendance.toString());

        try {
            attendance.setEmployee(employeeRepository.getOne(attendance.getEmployee().geteId()));

            System.out.println(attendance.toString());
            attendance = attendanceRepository.save(attendance);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(attendance.getDeparture()!=null){
            System.out.println("sSFSF");
            attendance= this.calculateAttendance(attendance);
            this.calculateSalary(attendance);
        }
        System.out.println(attendance);

        return attendance;

    }

    public Attendance calculateAttendance(Attendance attendance){
        System.out.println(attendance.getDeparture());
        System.out.println(attendance.getArrival());

        SimpleDateFormat f=new SimpleDateFormat("HH:mm");
        long a=0;
        try {

            a=(f.parse(attendance.getDeparture()).getTime()-f.parse(attendance.getArrival()).getTime())/(1000*60*60);
            System.out.println(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if( a >=5)
            attendance.setAttType(AttendaceType.FL);

        else
            attendance.setAttType(AttendaceType.HF);

        System.out.println(attendance.toString());

        return attendance;

    }

    public Attendance calculateSalary(Attendance attendance){

        String month =emDate.parse(attendance.getDate()).getMonthOfYear() + "-" + emDate.parse(attendance.getDate()).getYear();

        Salry salary=new Salry();

        try {
            salary=salaryRepository.findByEmployeeAndMonth(attendance.getEmployee(),month);
        }catch (Exception ex){

        }

        if (salary==null)
            salary=salaryService.createSalary(attendance.getEmployee(),month);

        double s=salary.getSalaryAmount();

        if( attendance.getAttType()==AttendaceType.FL)
            s=s+Double.parseDouble(attendance.getEmployee().getSalaryModel().getFullAmount());

        else if(attendance.getAttType()==AttendaceType.HF)
            s=s+Double.parseDouble(attendance.getEmployee().getSalaryModel().getHalfAmount());

        salary.setSalaryAmount(s);

        salaryRepository.save(salary);

        return attendance;

    }

    public List<Attendance> getAttendance(int id) throws EMException {

        List<Attendance> attendances=new ArrayList<>();

        System.out.println(id);
        try {
            Employee employee=employeeRepository.getOne(id);
            System.out.println(employee.toString());
            attendances=attendanceRepository.findByEmployee(employee);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(attendances.isEmpty())
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return attendances;
    }

    public List<Attendance> getAttendances() throws EMException {

        List<Attendance> attendances=new ArrayList<>();

        try {
            attendances=attendanceRepository.findAll();
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(attendances.isEmpty())
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return attendances;
    }



//    /**
//     * THIS METHOD IS FOR DETERMINE WHETHER THE DAY IS HALF OR FULL FOR  EMPLOYEES ACCORDING
//     * THEIR WORKING HOURS
//     * WORKING HOUR IS CALCULATE BY DEPARTURE TIME - ARRIVAL TIME
//     * INTERNAL PURPOSE
//     * AUTOMATED METHOD
//     */
//    public void calculateAttendances(){
//        List<Attendance> attendances=new ArrayList<>();
//        try {
//            attendances=attendanceRepository.findByDateAndAttendance(LocalDate.now(),true);
//        }
//        catch (Exception ex){
//            System.out.println(EMStatus.DB_ERROR);
//        }
//
//        for(Attendance attendance:attendances){
//            if( HOURS.between(attendance.getArrival(),attendance.getDeparture()) >=5)
//                attendance.setAttType(AttendaceType.FL);
//
//            else
//                attendance.setAttType(AttendaceType.HF);
//
//        }
//    }



//
//    /**
//     * THIS METHOD IS FOR CALCULTE THE MONTHLY SALARY FOR EMPLOYEES
//     * INTERNAL PURPOSE
//     * AUTOMATED METHOD
//     */
//    public void calculateSalary(){
//
//        List<Employee> employees=new ArrayList<>();
//        try {
//            employees=employeeRepository.findByActive(true);
//        }
//        catch (Exception ex){
//            System.out.println(EMStatus.DB_ERROR);
//        }
//
//        String month =LocalDate.now().getMonth().toString() + "-" + String.valueOf(LocalDate.now().getYear());
//
//        for (Employee employee:employees){
//
//            int fullDays = 0,halfDays=0;
//            try {
//                fullDays=attendanceRepository.countByEmployeeAndAttType(employee,AttendaceType.FL);
//
//                halfDays=attendanceRepository.countByEmployeeAndAttType(employee,AttendaceType.HF);
//            }
//            catch (Exception ex){
//                System.out.println(EMStatus.DB_ERROR);
//            }
//
//            double salary= Double.parseDouble(employee.getSalaryModel().getFullAmount()) * fullDays +
//
//                            Double.parseDouble(employee.getSalaryModel().getHalfAmount()) * halfDays;
//
//            Salry salry=new Salry(month,employee,String.valueOf(salary));
//
//            try {
//                if(!salaryRepository.findByEmployeeAndMonth(employee,month).isEmpty())
//                salaryRepository.save(salry);
//            }catch (Exception ex){
//                System.out.println(EMStatus.DB_ERROR);
//            }
//
//        }
//
//    }
}
