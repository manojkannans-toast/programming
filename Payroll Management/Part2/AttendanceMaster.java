import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
public class AttendanceMaster{
    private LinkedHashSet<EmployeeAttendance<Employee,Integer>> empattendance;
    public AttendanceMaster(){
        this.empattendance = new LinkedHashSet<EmployeeAttendance<Employee,Integer>>();
    }
    public void setAttendance(Employee employee, String days){
        Scanner input = new Scanner(System.in);
        try{
            int attendancedays = Integer.parseInt(days);
            if(attendancedays > 0){
                EmployeeAttendance<Employee,Integer> emp_attendance = new EmployeeAttendance<Employee,Integer>(employee,attendancedays);
                this.empattendance.add(emp_attendance);
            }
            else{
                System.out.println("WRONG ENTRY!! PLEASE ENTER THE VALID ATTENDANCE DAYS");
                days = input.nextLine();
                setAttendance(employee,days);
                input.close();
            }
        }catch(Exception ex){
            System.out.println("WRONG ENTRY!! PLEASE ENTER THE VALID ATTENDANCE DAYS ");
            days = input.nextLine();
            setAttendance(employee,days);
            input.close();
        }
    }
    public void showEligibleList(){
        for(EmployeeAttendance<Employee,Integer> attendance : this.empattendance){
            Integer num_of_days = attendance.getValue();
            if(num_of_days > 10){
                String details = attendance.getKey().toString();
                System.out.println(details);
            }
        }
    }
}