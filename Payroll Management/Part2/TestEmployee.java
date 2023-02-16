import java.util.Scanner;
import java.util.ArrayList;
public class TestEmployee{
    public static void main(String args[]){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        System.out.println("------------------------------------");
        while(true){
            System.out.println("DO YOU WANT TO ADD THE EMPLOYEE? yes or no");
            Scanner option = new Scanner(System.in);
            String choice = option.next();
            if(choice.equals("yes")){
                Scanner input = new Scanner(System.in);
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
            else{
                break;
            }
        }
        if(employees.size() == 0){
            System.out.println("!!No employee were added!!");
        }
        else{
            MasterData masterdata = new MasterData(employees);
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
            AttendanceMaster attendancemaster = new AttendanceMaster();
            System.out.println("SET ATTENDANCE FOR EACH EMPLOYEE"+"\n");
            int i=0;
            do{
                do{
                    try{
                        System.out.println("ENTER THE ATTENDANCE FOR AN EMPLOYEE ID "+masterdata.getEmployeeList().get(i).getEmpId());
                        Scanner input = new Scanner(System.in);
                        String days = input.nextLine();
                        int attendancedays = Integer.parseInt(days);
                        if(attendancedays > 0){
                            EmployeeAttendance<Employee,Integer> emp_attendance = new EmployeeAttendance<Employee,Integer>(employees.get(i),attendancedays);
                            attendancemaster.getEmployeeAttendance().add(emp_attendance);
                            break;
                        }
                        else{
                            System.out.println("!!WRONG ENTRY!!");
                        }
                    }catch(Exception ex){
                        System.out.println("!!WRONG ENTRY!!hihi");
                    }
                }while(true);
                i++;
            }while(i<masterdata.getEmployeeList().size());
            System.out.println("ELIGIBLE EMPLOYEES ARE :");
            attendancemaster.showEligibleList();
        }
    }
}