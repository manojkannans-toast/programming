import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
public class TestEmployee{
    public static void quicksort(ArrayList<Employee> employees,int start,int end,String param){
        if(start<end){
            int part = partition(employees,start,end,param);
            quicksort(employees,start,part-1,param);
            quicksort(employees,part+1,end,param);
        }
    }
    public static int partition(ArrayList<Employee> employees,int start,int end,String param){
        String pivot = "";
        double pivot_salary = 0.0;
        if(param == "salary"){
            pivot_salary = employees.get(end).getEmpSalary();
        }
        else{
            pivot = employees.get(end).getAttribute(param);
        }
        int pindex = start;
        for(int i=start;i<end;i++){
            System.out.println(pivot);
            if(param !="salary"){
                if(pivot.compareTo(employees.get(i).getAttribute(param)) > 0){
                    Employee temp = new Employee();
                    temp = employees.get(pindex);
                    employees.set(pindex,employees.get(i));
                    employees.set(i,temp);;
                    pindex++;
                }
            }
            else{
                if(pivot_salary > employees.get(i).getEmpSalary()){
                    Employee temp = new Employee();
                    temp = employees.get(pindex);
                    employees.set(pindex,employees.get(i));
                    employees.set(i,temp);
                    pindex++;
                }
            }
        }
        Employee temp = new Employee();
        temp = employees.get(pindex);
        employees.set(pindex,employees.get(end));
        employees.set(end,temp);
        return pindex;
    }
    public static void display(MasterData masterdata){
        for(int i=0;i<masterdata.getEmployeeList().size();i++){
            System.out.println("\n");
                masterdata.getEmployeeList().get(i).setAllowance();
                String emp_details = masterdata.getEmployeeList().get(i).toString();
                System.out.println(emp_details);
        }
    }
    public static void setAttendanceForEmployees(MasterData masterdata,AttendanceMaster attendancemaster){
        System.out.println("SET ATTENDANCE FOR ADDED EMPLOYEES"+"\n");
        int i=0;
        do{
            do{
                try{
                    if(!(attendancemaster.getEmployeeAttendance().containsKey(masterdata.getEmployeeList().get(i)))){
                        System.out.println("ENTER THE ATTENDANCE FOR AN EMPLOYEE ID "+masterdata.getEmployeeList().get(i).getEmpId());
                        Scanner input = new Scanner(System.in);
                        String days = input.nextLine();
                        int attendancedays = Integer.parseInt(days);
                        if(attendancedays >= 0){
                            attendancemaster.getEmployeeAttendance().put(masterdata.getEmployeeList().get(i),attendancedays);
                            break;
                        }
                        else{
                            System.out.println("!!WRONG ENTRY!!");
                        }
                    }
                    else{
                        i++;
                        continue;
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
        AttendanceMaster attendancemaster = new AttendanceMaster();
        MasterData masterdata = new MasterData(employees);
        System.out.println("------------------------------------");
        while(true){
            System.out.println("DO YOU WANT TO ADD THE EMPLOYEE? yes or no");
            Scanner option = new Scanner(System.in);
            String choice = option.next();
            if(choice.equals("yes")){
                System.out.println("ENTER THE CHOICE :"+"\n"+"1. ADD EMPLOYEE DETAILS"+"\n"+"2. SET EMPLOYEES ATTENDANCE"+"\n"+"3. UPDATE EMPLOYEE ATTENDANCE"+"\n"+"4. FILTER EMPLOYEES"+"\n"+"5. SORT EMPLOYEE DETAIL"+"\n"+"6. DISPLAY EMPLOYEE DETAILS");
                Scanner input = new Scanner(System.in);
                int employeechoice = input.nextInt();
                if(employeechoice == 1){
                    Employee employee = new Employee();
                    System.out.print("ENTER THE EMPLOYEE DETAILS"+"\n"+"ENTER THE NAME :");
                    input.nextLine();
                    String empname = input.nextLine();
                    employee.setEmpName(empname);
                    System.out.println("ENTER THE EMPLOYEE DEPARTMENT :"+"\n"+"1. HUMAN RESOURCE"+"\n"+"2. IT"+"\n"+"3. FINANCE"+"\n"+"4. MARKETING"+"\n"+"5. R&D"+"\n"+"6. PRODUCTION");
                    String empdepartment = input.nextLine();
                    employee.setEmpDepartment(empdepartment);
                    System.out.println("ENTER THE EMPLOYEE DESIGNATION"+"\n"+"1. TRAINEE ENGINEER"+"\n"+"2. SOFTWARE ENGINEER"+"\n"+"3. PROJECT LEAD"+"\n"+"4. PROJECT MANAGER"+"\n"+"5. PROGRAM MANAGER"+"\n"+"6. HR MANAGER");
                    String empdesignation = input.nextLine();
                    employee.setEmpDesignation(empdesignation);
                    System.out.println("ENTER THE EMPLOYEE SALARY :");
                    String empsalary = input.nextLine();
                    employee.setEmpSalary(empsalary);
                    employees.add(employee);
                }
                else if(employeechoice == 2){
                    masterdata.setEmployeeList(employees);
                    if(employees.size()==0){
                        System.out.println("!!There is no Employee found!!");
                    }
                    else{
                        setAttendanceForEmployees(masterdata,attendancemaster);
                    }
                }
                else if(employeechoice == 3){
                    masterdata.setEmployeeList(employees);
                    System.out.println("ENTER THE EMPLOYEE ID :");
                    input.nextLine();
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
                else if(employeechoice == 4){
                    masterdata.setEmployeeList(employees);
                    LinkedHashMap<Employee,Integer> temp_emp_attendance = attendancemaster.getEmployeeAttendance();
                    attendancemaster.filterEmployeeList(temp_emp_attendance);
                    attendancemaster.showEligibleList();
                }
                else if(employeechoice == 5){
                    do{
                        System.out.println("1. SORT BY EMPLOYEE NAME"+"\n"+"2. SORT BY EMPLOYEE DEPARTMENT"+"\n"+"3. SORT BY EMPLOYEE DESIGNATION"+"\n"+"4. SORT BY EMPLOYEE SALARY"+"\n"+"5. TO EXIT");
                        int sort_choice = input.nextInt();
                        int flag = 0;
                        switch(sort_choice){
                            case 1:
                                quicksort(masterdata.getEmployeeList(),0,masterdata.getEmployeeList().size()-1,"empname");
                                display(masterdata);
                                flag = 1;
                                break;
                            case 2:
                                quicksort(masterdata.getEmployeeList(),0,masterdata.getEmployeeList().size()-1,"empdepartment");
                                display(masterdata);
                                flag = 1;
                                break;
                            case 3:
                                quicksort(masterdata.getEmployeeList(),0,masterdata.getEmployeeList().size()-1,"empdesignation");
                                display(masterdata);
                                flag = 1;
                                break;
                            case 4:
                                quicksort(masterdata.getEmployeeList(),0,masterdata.getEmployeeList().size()-1,"salary");
                                display(masterdata);
                                flag = 1;
                                break;
                            case 5:
                                flag = 1;
                                break;
                            default:
                                System.out.println("!!YOU HAVE ENTERED WRONG OPTION!!");
                                break;
                        }
                        if(flag == 1){
                            break;
                        }
                    }while(true);
                }
                else if(employeechoice == 6){
                    System.out.println("\n"+"EMPLOYEE DETAILS :");
                    for(int i=0;i<masterdata.getEmployeeList().size();i++){
                        System.out.println("\n");
                        // String emp_details = masterdata.getEmployeeList().get(i).toString();
                        // System.out.println(emp_details);
                        //System.out.println("\n"+"\n"+"AFTER ALLOWANCE :");
                        employees.get(i).setAllowance();
                        String emp_details = masterdata.getEmployeeList().get(i).toString();
                        System.out.println(emp_details);
                    }
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
            System.out.println("ELIGIBLE EMPLOYEES ARE :");
            attendancemaster.showEligibleList();
        }
    }
}