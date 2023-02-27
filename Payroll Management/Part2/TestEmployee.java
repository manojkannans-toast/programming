import java.util.Scanner;
import java.util.ArrayList;
public class TestEmployee{
    public static void setAttendanceForEmployees(ArrayList<Employee> employees,MasterData masterdata,AttendanceMaster attendancemaster){
        System.out.println("SET ATTENDANCE FOR EACH EMPLOYEE"+"\n");
        int i=0;
        do{
            do{
                try{
                    System.out.println("ENTER THE ATTENDANCE FOR AN EMPLOYEE ID "+masterdata.getEmployeeList().get(i).getEmpId());
                    Scanner input = new Scanner(System.in);
                    String days = input.nextLine();
                    int attendancedays = Integer.parseInt(days);
                    if(attendancedays >= 0){
                        attendancemaster.getEmployeeAttendance().put(employees.get(i),attendancedays);
                        break;
                    }
                    else{
                        System.out.println("!!WRONG ENTRY!!");
                    }
                }catch(Exception ex){
                    System.out.println("!!WRONG ENTRY!!");
                }
            }while(true);
            i++;
        }while(i<masterdata.getEmployeeList().size());
    }
    public static int validateEmployeeId(String employeeid){
        Scanner input = new Scanner(System.in);
        do{
            try{
                int emp_id = Integer.parseInt(employeeid);
                if(emp_id > 1000){
                    return emp_id;
                }
                else{
                    System.out.println("!!WRONG ENTRY!!");
                    System.out.println("ENTER THE EMPLOYEE ID :");
                    employeeid = input.nextLine();
                }
            }catch(Exception ex){
                System.out.println("!!WRONG ENTRY!!");
                System.out.println("ENTER THE EMPLOYEE ID :");
                employeeid = input.nextLine();
            }
        }while(true);
    }
    public static int validateEmployeeAttendance(String days){
        Scanner input = new Scanner(System.in);
        do{
            try{
                int att_days = Integer.parseInt(days);
                return att_days;
            }catch(Exception ex){
                System.out.println("!!WRONG ENTRY!!");
                System.out.println("ENTER THE EMPLOYEE ATTENDANCE :");
                days = input.nextLine();
            }
        }while(true);
    }
    public static void main(String args[]){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        MasterData masterdata = new MasterData(employees);
        AttendanceMaster attendancemaster = new AttendanceMaster();
        System.out.println("------------------------------------");
        while(true){
            System.out.println("DO YOU WANT TO ADD THE EMPLOYEE? yes or no");
            Scanner option = new Scanner(System.in);
            String choice = option.next();
            if(choice.equals("yes")){
                System.out.println("ENTER THE CHOICE :"+"\n"+"1. ENTER EMPLOYEE DETAILS"+"\n"+"2. SET EMPLOYEES ATTENDANCE"+"\n"+"3. UPDATE EMPLOYEE ATTENDANCE");
                Scanner input = new Scanner(System.in);
                int employeechoice = input.nextInt();
                if(employeechoice == 1){
                    Employee employee = new Employee();
                    System.out.print("ENTER THE EMPLOYEE DETAILS"+"\n"+"ENTER THE NAME :");
                    String empname = input.nextLine();
                    employee.setName(empname);
                    System.out.println("ENTER THE EMPLOYEE DEPARTMENT :"+"\n"+"1. HUMAN RESOURCE"+"\n"+"2. IT"+"\n"+"3. FINANCE"+"\n"+"4. MARKETING"+"\n"+"5. R&D"+"\n"+"6. PRODUCTION");
                    String empdepartment = input.nextLine();
                    employee.setDepartment(empdepartment);
                    System.out.println("ENTER THE EMPLOYEE DESIGNATION"+"\n"+"1. TRAINEE ENGINEER"+"\n"+"2. SOFTWARE ENGINEER"+"\n"+"3. PROJECT LEAD"+"\n"+"4. PROJECT MANAGER"+"\n"+"5. PROGRAM MANAGER"+"\n"+"6. HR MANAGER");
                    String empdesignation = input.nextLine();
                    employee.setDesignation(empdesignation);
                    System.out.println("ENTER THE EMPLOYEE SALARY :");
                    String empsalary = input.nextLine();
                    employee.setSalary(empsalary);
                    employees.add(employee);
                }
                else if(employeechoice == 2){
                    if(employees.size()==0){
                        System.out.println("!!There is no Employee found!!");
                    }
                    else{
                        setAttendanceForEmployees(employees,masterdata,attendancemaster);
                    }
                }
                else if(employeechoice == 3){
                    System.out.println("ENTER THE EMPLOYEE ID :");
                    String empid = input.nextLine();
                    int emp_id = validateEmployeeId(empid);
                    System.out.println("ENTER THE EMPLOYEE ATTENDANCE :");
                    String empattendance = input.nextLine();
                    int emp_attendance = validateEmployeeAttendance(empattendance);
                    int result = attendancemaster.updateAttendance(emp_id,emp_attendance);
                    if(result == 1){
                        System.out.println("ATTENDANCE UPDATED SUCCESFULLY");
                    }
                    else{
                        System.out.println("!!NO SUCH EMPLOYEE FOUND!!");
                    }
                }
                else{
                    break;
                }
            }
            else{
                break;
            }
        }
        if(employees.size() == 0){
            System.out.println("!!No employee were added!!");
        }
        else{
            System.out.println("\n"+"EMPLOYEE DETAILS :");
            for(int i=0;i<masterdata.getEmployeeList().size();i++){
                System.out.println("\n");
                String emp_details = masterdata.getEmployeeList().get(i).toString();
                System.out.println(emp_details);
                employees.get(i).setAllowance();
                System.out.println("\n"+"\n"+"AFTER ALLOWANCE :");
                emp_details = masterdata.getEmployeeList().get(i).toString();
                System.out.println(emp_details);
            }
            System.out.println("ELIGIBLE EMPLOYEES ARE :");
            attendancemaster.showEligibleList();
        }
    }
}
