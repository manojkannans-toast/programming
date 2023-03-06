import java.util.LinkedHashMap;
import java.util.Map; 
import java.util.ArrayList;
public class AttendanceMaster{
    private LinkedHashMap<Employee,Integer> empattendance;
    public AttendanceMaster(){
        this.empattendance = new LinkedHashMap<Employee,Integer>();
    }
    public LinkedHashMap<Employee,Integer> getEmployeeAttendance(){
        return this.empattendance;
    }
    public int updateAttendance(int employeeid,int employeeattendance,MasterData masterdata){
        if(masterdata.getEmployeeList().size()==0){
            return -1;
        }
        for(Employee emp: masterdata.getEmployeeList()){
            if(emp.getEmpId() == employeeid){
                this.empattendance.put(emp,employeeattendance);
                return 1;
            }
        }
        return 0;
    }
    public ArrayList<Employee> filterEmployeeList(MasterData masterdata,LinkedHashMap<Employee,Integer> temp_emp_attendance,ArrayList<Employee> delete_emp){
        for(Employee emp :  masterdata.getEmployeeList()){
            if(!(temp_emp_attendance.containsKey(emp)) && !(delete_emp.contains(emp))){
                System.out.println("YOU HAVE NOT ADDED ATTENDANCE TO ALL THE EMPLOYEES");
                return delete_emp;
            }
        }
        for(Map.Entry<Employee,Integer> attendance:temp_emp_attendance.entrySet()){
            Integer num_of_days = attendance.getValue();
            if(num_of_days <= 10){
                this.empattendance.remove(attendance.getKey());
                delete_emp.add(attendance.getKey());
            }
        }
        this.showEligibleList(masterdata);
        return delete_emp;
    }
    public void setEmployeeAttendance(LinkedHashMap<Employee,Integer> emp_attendance){
        this.empattendance = emp_attendance;
    }
    public void showEligibleList(MasterData masterdata){
        if(masterdata.getEmployeeList().size() == 0){
            System.out.println("!!NO EMPLOYEES FOUND!!");
            return;
        }
        if(this.empattendance.size() == 0){
            System.out.println("!!NO ELIGIBLE EMPLOYEES ARE THERE!!");
            return;
        }
        else{
            System.out.format("%15s %18s %19s %23s %18s","ID","NAME","DEPARTMENT","DESIGNATION","SALARY"+"\n"+"--------------------------------------------------------------------------------------------"+"\n");
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