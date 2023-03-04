import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class AttendanceMaster{
    private LinkedHashMap<Employee,Integer> empattendance;
    public AttendanceMaster(){
        this.empattendance = new LinkedHashMap<Employee,Integer>();
    }
    public LinkedHashMap<Employee,Integer> getEmployeeAttendance(){
        return this.empattendance;
    }
    public int updateAttendance(int employeeid,int employeeattendance){
        for(Map.Entry<Employee,Integer> attendance:this.empattendance.entrySet()){
            if(attendance.getKey().getEmpId() == employeeid){
                this.empattendance.replace(attendance.getKey(),employeeattendance);
                return 1;
            }
        }
        return 0;
    }
    public void filterEmployeeList(LinkedHashMap<Employee,Integer> temp_emp_attendance){
        for(Map.Entry<Employee,Integer> attendance:temp_emp_attendance.entrySet()){
            Integer num_of_days = attendance.getValue();
            if(num_of_days <= 10){
                this.empattendance.remove(attendance.getKey());
            }
        }
    }
    public void setEmployeeAttendance(LinkedHashMap<Employee,Integer> emp_attendance){
        this.empattendance = emp_attendance;
    }
    public void showEligibleList(){
        if(this.empattendance.size() == 0){
            System.out.println("NO ELIGIBLE EMPLOYEES WERE THERE");
        }
        else{
            System.out.format("%15s %18s %19s %23s %18s","ID","NAME","DEPARTMENT","DESIGNATION","SALARY"+"\n");
            for(Map.Entry<Employee,Integer> attendance:this.empattendance.entrySet()){
                Integer num_of_days = attendance.getValue();
                if(num_of_days > 10){
                    String details = attendance.getKey().toString();
                    String mod_det[] = details.split(",");
                    System.out.format("%15s %18s %19s %23s %18s",mod_det[0],mod_det[1],mod_det[2],mod_det[3],mod_det[4]+"\n");
                }
            }
        }
    }
}