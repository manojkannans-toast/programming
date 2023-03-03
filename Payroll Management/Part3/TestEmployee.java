import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;
public class TestEmployee{
    public static class SortByNameAsc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            return emp1.getEmpName().compareTo(emp2.getEmpName());
        }
    }
    public static class SortByNameDesc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            return emp2.getEmpName().compareTo(emp1.getEmpName());
        }
    }
    public static class SortByDepartmentAsc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            return emp1.getEmpDepartment().compareTo(emp2.getEmpDepartment());
        }
    }
    public static class SortByDepartmentDesc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            return emp2.getEmpDepartment().compareTo(emp1.getEmpDepartment());
        }
    }
    public static class SortByDesignationAsc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            return emp1.getEmpDesignation().compareTo(emp2.getEmpDesignation());
        }
    }
    public static class SortByDesignationDesc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            return emp2.getEmpDesignation().compareTo(emp1.getEmpDesignation());
        }
    }
    public static class SortBySalaryAsc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            if(emp1.getEmpSalary() == emp2.getEmpSalary()){
                return 0;
            }
            else if(emp1.getEmpSalary() > emp2.getEmpSalary()){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
    public static class SortBySalaryDesc implements Comparator<Employee>{
        public int compare(Employee emp1,Employee emp2){
            if(emp1.getEmpSalary() == emp2.getEmpSalary()){
                return 0;
            }
            else if(emp1.getEmpSalary() < emp2.getEmpSalary()){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
    public static void display(MasterData masterdata){
        System.out.format("%15s %18s %19s %23s %18s","ID","NAME","DEPARTMENT","DESIGNATION","SALARY"+"\n");
        for(Employee emp : masterdata.getEmployeeList()){
                System.out.format("-----------------------------------------------------------------------------------------------------"+"\n"+"%15s %18s %18s %27s %15s",emp.getEmpId(),emp.getEmpName(),emp.getEmpDepartment(),emp.getEmpDesignation(),emp.getEmpSalary()+"\n");
        }
    }
    public static void setAttendanceForEmployees(MasterData masterdata,AttendanceMaster attendancemaster){
        System.out.println("SET ATTENDANCE FOR ADDED EMPLOYEES"+"\n");
        int i=0;
        do{
            do{
                try{
                    if(i == masterdata.getEmployeeList().size()){
                        System.out.println("!!THERE WERE NO EMPLOYEES TO ADD THE ATTENDANCE!!");
                        break;
                    }
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
    public static int validateEmployeeId(String employeeid,AttendanceMaster attendancemaster){
        Scanner input = new Scanner(System.in);
        do{
            try{
                int flag = 0;
                int emp_id = Integer.parseInt(employeeid);
                for(Map.Entry<Employee,Integer> emp : attendancemaster.getEmployeeAttendance().entrySet()){
                    if(emp.getKey().getEmpId() == emp_id){
                        flag = 1;   
                    }
                }
                if(flag == 1){
                    return emp_id;
                }
                else{
                    System.out.println("!!NO SUCH EMPLOYEE FOUND!!");
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
                if(att_days < 0){
                    System.out.println("!!WRONG ENTRY!!");
                    System.out.println("ENTER THE EMPLOYEE ATTENDANCE :");
                    days = input.nextLine();
                }
                return att_days;
            }catch(Exception ex){
                System.out.println("!!WRONG ENTRY!!");
                System.out.println("ENTER THE EMPLOYEE ATTENDANCE :");
                days = input.nextLine();
            }
        }while(true);
    }
    public static int validateSortChoice(String asc_desc_choice){
        Scanner input = new Scanner(System.in);
        int asc_desc = 0;
        try{
            asc_desc = Integer.parseInt(asc_desc_choice);
            if(asc_desc > 2 || asc_desc < 1){
                System.out.println("!!ENTER THE VALID OPTION!!");
                asc_desc_choice = input.nextLine();
                asc_desc = validateSortChoice(asc_desc_choice);
            }
        }catch(Exception ex){
            System.out.println("!!ENTER THE VALID OPTION!!");
            asc_desc_choice = input.nextLine();
            asc_desc = validateSortChoice(asc_desc_choice);
        }
        return asc_desc;
    }
    public static void main(String args[]){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        AttendanceMaster attendancemaster = new AttendanceMaster();
        MasterData masterdata = new MasterData(employees);
        System.out.println("------------------------------------");
        while(true){
            System.out.println("DO YOU WANT TO CONTINUE? yes or no");
            Scanner option = new Scanner(System.in);
            String choice = option.next();
            if(choice.equals("yes")){
                System.out.println("ENTER THE CHOICE :"+"\n"+"1. ADD EMPLOYEE DETAILS"+"\n"+"2. SET EMPLOYEES ATTENDANCE"+"\n"+"3. UPDATE EMPLOYEE ATTENDANCE"+"\n"+"4. FILTER EMPLOYEES"+"\n"+"5. SORT EMPLOYEE DETAIL"+"\n"+"6. SHOW ELIGIBLE EMPLOYEES"+"\n"+"7. CALCULATE SALARY FOR EMPLOYEES"+"\n"+"8. DISPLAY EMPLOYEE DETAILS");
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
                    int emp_id = validateEmployeeId(empid,attendancemaster);
                    System.out.println("ENTER THE EMPLOYEE ATTENDANCE :");
                    String empattendance = input.nextLine();
                    int emp_attendance = validateEmployeeAttendance(empattendance);
                    if(attendancemaster.getEmployeeAttendance().size()!=0){
                        int result = attendancemaster.updateAttendance(emp_id,emp_attendance);
                        if(result == 1){
                            System.out.println("ATTENDANCE UPDATED SUCCESFULLY");
                        }
                        else{
                            System.out.println("!!NO SUCH EMPLOYEE FOUND!!");
                        }
                    }else{
                        System.out.println("!!NO EMPLOYEES WERE FOUND!!");
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
                        int flag = 0,asc_desc;
                        String asc_desc_choice;
                        switch(sort_choice){
                            case 1:
                                System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                asc_desc_choice = input.nextLine();
                                asc_desc = validateSortChoice(asc_desc_choice);
                                if(asc_desc == 1){
                                    masterdata.getEmployeeList().sort(new SortByNameAsc());
                                }
                                else{
                                    masterdata.getEmployeeList().sort(new SortByNameDesc());
                                }
                                display(masterdata);
                                flag = 1;
                                break;
                            case 2:
                                System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                asc_desc_choice = input.nextLine();
                                asc_desc = validateSortChoice(asc_desc_choice);
                                if(asc_desc == 1){
                                    masterdata.getEmployeeList().sort(new SortByDepartmentAsc());
                                }
                                else{
                                    masterdata.getEmployeeList().sort(new SortByDepartmentDesc());
                                }
                                display(masterdata);
                                flag = 1;
                                break;
                            case 3:
                                System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                asc_desc_choice = input.nextLine();
                                asc_desc = validateSortChoice(asc_desc_choice);
                                if(asc_desc == 1){
                                    masterdata.getEmployeeList().sort(new SortByDesignationAsc());
                                }
                                else{
                                    masterdata.getEmployeeList().sort(new SortByDesignationDesc());
                                }
                                display(masterdata);
                                flag = 1;
                                break;
                            case 4:
                                System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                asc_desc_choice = input.nextLine();
                                asc_desc = validateSortChoice(asc_desc_choice);
                                if(asc_desc == 1){
                                    masterdata.getEmployeeList().sort(new SortBySalaryAsc());
                                }
                                else{
                                    masterdata.getEmployeeList().sort(new SortBySalaryDesc());
                                }
                                display(masterdata);
                                flag = 1;
                                break;
                            case 5:
                                flag = 1;
                                break;
                            default:
                                System.out.println("!!YOU HAVE ENTERED THE WRONG OPTION!!");
                                break;
                        }
                        if(flag == 1){
                            break;
                        }
                    }while(true);
                }
                else if(employeechoice == 6){
                    if(attendancemaster.getEmployeeAttendance().size() == 0){
                        System.out.println("NO ATTENDANCE WERE ADDED TO THE EMPLOYEES");
                    }
                    else{
                        System.out.println("ELIGIBLE EMPLOYEES ARE :");
                        attendancemaster.showEligibleList();
                    }
                }
                else if(employeechoice == 7){
                    SalCalculator sal_cal = new SalCalculator();
                    sal_cal.calculateSalary(attendancemaster.getEmployeeAttendance());
                }
                else if(employeechoice == 8){
                    System.out.println("\n"+"EMPLOYEE DETAILS :");
                    display(masterdata);
                }
            }
            else{
                break;
            }
        }
    }
}