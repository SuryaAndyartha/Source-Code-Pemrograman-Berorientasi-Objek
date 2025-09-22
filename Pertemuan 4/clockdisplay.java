import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClockDisplay
{
 
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayString;
    private String dateString;

    public ClockDisplay(){
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        updateFromSystemTime();
    }
    
    public ClockDisplay(int hour, int minute, int second){
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute, second);
    }
    
    public void updateFromSystemTime(){
        LocalDateTime now = LocalDateTime.now();
        hours.setValue(now.getHour());
        minutes.setValue(now.getMinute());
        seconds.setValue(now.getSecond());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateString = now.format(formatter);
        updateDisplay();
    }

    public void timeTick(){
        updateFromSystemTime();
    }
    
    public void setTime(int hour, int minute, int second){
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        updateDisplay();
    }
    
    public String getTime(){
        return displayString;
    }
    
    public String getDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(fmt) + "            86°F"; //Hard coded temperature
    }
    
    private void updateDisplay(){
        displayString = hours.getDisplayValue() + ":" + minutes.getDisplayValue() + ":" + seconds.getDisplayValue();
    }
}
