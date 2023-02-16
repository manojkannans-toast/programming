import java.util.HashMap;
public class AttendanceMaster{
    HashMap<Employee,Integer> empattendance = new HashMap<Employee,Integer>();
    public AttendanceMaster(HashMap<Employee,Integer> empattendance){
        this.empattendance = empattendance;
    }
}