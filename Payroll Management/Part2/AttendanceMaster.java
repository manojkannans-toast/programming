import java.util.LinkedHashSet;
import java.util.Scanner;
public class AttendanceMaster{
    private LinkedHashSet<EmployeeAttendance<Employee,Integer>> empattendance;
    public AttendanceMaster(){
        this.empattendance = new LinkedHashSet<EmployeeAttendance<Employee,Integer>>();
    }
    public LinkedHashSet<EmployeeAttendance<Employee,Integer>> getEmployeeAttendance(){
        return this.empattendance;
    }
    public void showEligibleList(){
        if(this.empattendance.size() == 0){
            System.out.println("NO ELIGIBLE EMPLOYEES WERE THERE");
        }
        for(EmployeeAttendance<Employee,Integer> attendance : this.empattendance){
            Integer num_of_days = attendance.getValue();
            if(num_of_days > 10){
                String details = attendance.getKey().toString();
                System.out.println(details);
            }
        }
    }
}