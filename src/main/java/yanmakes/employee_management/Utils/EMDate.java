package yanmakes.employee_management.Utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import static org.joda.time.Days.daysBetween;

@Service
public class EMDate {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZone(DateTimeZone.forID("Asia/Colombo"));

    public DateTime parse(String date){
        return formatter.parseDateTime(date);
    }

    public String format(DateTime dateTime){
        return dateTime.toString(formatter);
    }

//
//    public LocalDate currentTime(){
//        Date dateTime= Date ;
////        return dateTime.toString(DateTimeFormat.forPattern("HH:mm").withZone(DateTimeZone.forID("Asia/Colombo")));
//       return formatter.
//    }

    public String dateDiff(String date){

        DateTime d=new DateTime();
        return String.valueOf(daysBetween(this.parse(date).toLocalDate(),
                d.toLocalDate()).getDays());
    }

    public String currentDate(){
        DateTime localDate = DateTime.now();
        return localDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd").withZone(DateTimeZone.forID("Asia/Colombo")));
    }

    public String currentTimestamp(){
        DateTime localDate = DateTime.now();
        return localDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm").withZone(DateTimeZone.forID("Asia/Colombo")));
    }

    public String currentTime(){
        DateTime localDate = DateTime.now();
        return localDate.toString(DateTimeFormat.forPattern("hh:mm").withZone(DateTimeZone.forID("Asia/Colombo")));
    }

}

