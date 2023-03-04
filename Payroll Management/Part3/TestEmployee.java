import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;
public class TestEmployee{
    public static class SortByName implements Comparator<Employee>{
        public boolean isascending;
        public SortByName(boolean isascending){
            this.isascending = isascending;
        }
        public int compare(Employee emp1,Employee emp2){
            if(isascending){
                return emp1.getEmpName().toUpperCase().compareTo(emp2.getEmpName().toUpperCase());
            }
            else{
                return emp2.getEmpName().toUpperCase().compareTo(emp1.getEmpName().toUpperCase());
            }
        }
    }
    public static class SortByDepartment implements Comparator<Employee>{
        public boolean isascending;
        public SortByDepartment(boolean isascending){
            this.isascending = isascending;
        }
        public int compare(Employee emp1,Employee emp2){
            if(isascending){
                return emp1.getEmpDepartment().compareTo(emp2.getEmpDepartment());
            }
            else{
                return emp2.getEmpDepartment().compareTo(emp1.getEmpDepartment());
            }
        }
    }
    public static class SortByDesignation implements Comparator<Employee>{
        public boolean isascending;
        public SortByDesignation(boolean isascending){
            this.isascending = isascending;
        }
        public int compare(Employee emp1,Employee emp2){
            if(isascending){
                return emp1.getEmpDesignation().compareTo(emp2.getEmpDesignation());
            }
            else{
                return emp2.getEmpDesignation().compareTo(emp1.getEmpDesignation());
            }
        }
    }
    public static class SortBySalary implements Comparator<Employee>{
        public boolean isascending;
        public SortBySalary(boolean isascending){
            this.isascending = isascending;
        }
        public int compare(Employee emp1,Employee emp2){
            if(isascending){
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
            else{
                if(emp2.getEmpSalary() == emp1.getEmpSalary()){
                    return 0;
                }
                else if(emp2.getEmpSalary() > emp1.getEmpSalary()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        }
    }
    public static void display(MasterData masterdata){
        System.out.format("%15s %18s %19s %23s %18s","ID","NAME","DEPARTMENT","DESIGNATION","SALARY"+"\n");
        for(Employee emp : masterdata.getEmployeeList()){
                String details = emp.toString();
                String mod_det[] = details.split(",");
                System.out.format("%15s %18s %19s %23s %18s",mod_det[0],mod_det[1],mod_det[2],mod_det[3],mod_det[4]+"\n");
        }
    }
    public static void setAttendanceForEmployees(MasterData masterdata,AttendanceMaster attendancemaster){
        System.out.println("SET ATTENDANCE FOR ADDED EMPLOYEES"+"\n");
        int i=0;
        do{
            do{
                try{
                    if(i == masterdata.getEmployeeList().size()){
                        System.out.println(i+"!!THERE WERE NO EMPLOYEES TO ADD THE ATTENDANCE!!");
                        break;
                    }
                    if(attendancemaster.getEmployeeAttendance().containsKey(masterdata.getEmployeeList().get(i)) && attendancemaster.getEmployeeAttendance().get(masterdata.getEmployeeList().get(i)) == 0){
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
                    System.out.println("!!NO SUCH EMPLOYEE FOUND!!"+"\n"+"ENTER THE EMPLOYEE ID :");
                    employeeid = input.nextLine();
                }
            }catch(Exception ex){
                System.out.println("!!WRONG ENTRY!!"+"\n"+"ENTER THE EMPLOYEE ID :");
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
                    System.out.println("!!WRONG ENTRY!!"+"\n"+"ENTER THE EMPLOYEE ATTENDANCE :");
                    days = input.nextLine();
                }
                return att_days;
            }catch(Exception ex){
                System.out.println("!!WRONG ENTRY!!"+"\n"+"ENTER THE EMPLOYEE ATTENDANCE :");
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
            System.out.println("!!ENTER THE VALID OPTION!!hihi");
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
        int count = 0;
        while(true){
            String choice = "";
            if(count > 0){
                System.out.println("DO YOU WANT TO CONTINUE? yes or no");
                Scanner option = new Scanner(System.in);
                choice = option.next();
            }
            int employeechoice = -1;
            if(choice.equals("yes") || count ==0){
                System.out.println("\n"+"1. ADD EMPLOYEE DETAILS"+"\n"+"2. SET EMPLOYEES ATTENDANCE"+"\n"+"3. UPDATE EMPLOYEE ATTENDANCE"+"\n"+"4. FILTER EMPLOYEES"+"\n"+"5. SORT EMPLOYEE DETAIL"+"\n"+"6. SHOW ELIGIBLE EMPLOYEES"+"\n"+"7. CALCULATE SALARY FOR EMPLOYEES"+"\n"+"8. DISPLAY EMPLOYEE DETAILS"+"\n"+"9.TO EXIT"+"\n"+"ENTER THE CHOICE :");
                Scanner input = new Scanner(System.in);
                try{
                    employeechoice = input.nextInt();
                    switch(employeechoice){
                        case 1:
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
                            attendancemaster.getEmployeeAttendance().put(employee,0);
                            break;
                        case 2:
                            masterdata.setEmployeeList(employees);
                            if(employees.size()==0){
                                System.out.println("!!There is no Employee found!!");
                            }
                            else{
                                setAttendanceForEmployees(masterdata,attendancemaster);
                            }
                            break;
                        case 3:
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
                            break;
                        case 4:
                            masterdata.setEmployeeList(employees);
                            LinkedHashMap<Employee,Integer> temp_emp_attendance = attendancemaster.getEmployeeAttendance();
                            attendancemaster.filterEmployeeList(temp_emp_attendance);
                            attendancemaster.showEligibleList();
                            //temp_emp_attendance.clear();
                            break;
                        case 5:
                            do{
                                System.out.println("1. SORT BY EMPLOYEE NAME"+"\n"+"2. SORT BY EMPLOYEE DEPARTMENT"+"\n"+"3. SORT BY EMPLOYEE DESIGNATION"+"\n"+"4. SORT BY EMPLOYEE SALARY"+"\n"+"5. TO EXIT");
                                int sort_choice = input.nextInt();
                                input.nextLine();
                                int flag = 0,asc_desc;
                                String asc_desc_choice;
                                switch(sort_choice){
                                    case 1:
                                        System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                        asc_desc_choice = input.nextLine();
                                        asc_desc = validateSortChoice(asc_desc_choice);
                                        if(asc_desc == 1){
                                            masterdata.getEmployeeList().sort(new SortByName(true));
                                        }
                                        else{
                                            masterdata.getEmployeeList().sort(new SortByName(false));
                                        }
                                        display(masterdata);
                                        flag = 1;
                                        break;
                                    case 2:
                                        System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                        asc_desc_choice = input.nextLine();
                                        asc_desc = validateSortChoice(asc_desc_choice);
                                        if(asc_desc == 1){
                                            masterdata.getEmployeeList().sort(new SortByDepartment(true));
                                        }
                                        else{
                                            masterdata.getEmployeeList().sort(new SortByDepartment(false));
                                        }
                                        display(masterdata);
                                        flag = 1;
                                        break;
                                    case 3:
                                        System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                        asc_desc_choice = input.nextLine();
                                        asc_desc = validateSortChoice(asc_desc_choice);
                                        if(asc_desc == 1){
                                            masterdata.getEmployeeList().sort(new SortByDesignation(true));
                                        }
                                        else{
                                            masterdata.getEmployeeList().sort(new SortByDesignation(false));
                                        }
                                        display(masterdata);
                                        flag = 1;
                                        break;
                                    case 4:
                                        System.out.println("1. SORT IN ASCENDING ORDER"+"\n"+"2. SORT IN DESCENDING ORDER");
                                        asc_desc_choice = input.nextLine();
                                        asc_desc = validateSortChoice(asc_desc_choice);
                                        if(asc_desc == 1){
                                            masterdata.getEmployeeList().sort(new SortBySalary(true));
                                        }
                                        else{
                                            masterdata.getEmployeeList().sort(new SortBySalary(false));
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
                            break;
                        case 6:
                            System.out.println("ELIGIBLE EMPLOYEES ARE :");
                            attendancemaster.showEligibleList();
                            break;
                        case 7:
                            SalCalculator sal_cal = new SalCalculator();
                        sal_cal.calculateSalary(attendancemaster.getEmployeeAttendance());
                        break;
                        case 8:
                            System.out.println("\n"+"EMPLOYEE DETAILS :");
                            display(masterdata);
                            break;
                        case 9:
                            break;
                        default:
                            System.out.println("!!ENTER THE CORRECT OPTION!!");
                            break;
                    }
                }catch(Exception ex){
                    System.out.println("!!INVALID OPTION!! RE-ENTER");
                }
            }
            else{
                break;
            }
            count++;
        }
    }
}