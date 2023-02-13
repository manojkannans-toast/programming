import java.util.Scanner;
import java.util.ArrayList;
public class TestEmployee{
    public static String validateName(Scanner input){
        System.out.println("ENTER THE NAME :");
        String empName = input.nextLine();
        String regex = "^[a-zA-Z]([.](?![.])|[a-zA-Z\\s]){3,20}$";
        if(empName.matches(regex)){
            return empName;
        }
        else{
            System.out.println("!!ENTER THE VALID EMPLOYEE NAME!!");
            empName = validateName(input);
        }
        return empName;
    }
    public static String designationChoice(Scanner input){
        System.out.println("\n"+"ENTER THE DESIGNATION"+"\n"+"1. TRAINEE ENGINEER"+"\n"+"2. SOFTWARE ENGINEER"+"\n"+"3. PROJECT LEAD"+"\n"+"4. PROJECT MANAGER"+"\n"+"5. PROGRAM MANAGER"+"\n"+"6. HR MANAGER");
        String empDesignation = "";       
        try{
           int choice = input.nextInt();
           switch(choice){
                    case 1:
                        empDesignation = "TRAINEE ENGINEER";
                        break;
                    case 2:
                        empDesignation = "SOFTWARE ENGINEER";
                        break;
                    case 3:
                        empDesignation = "PROJECT LEAD";
                        break;
                    case 4:
                        empDesignation = "PROJECT MANAGER";
                        break;
                    case 5:
                        empDesignation = "PROGRAM MANAGER";
                        break;
                    case 6:
                        empDesignation = "HR MANAGER";
                    default:
                        System.out.println("!! WRONG ENTRY !!"+"\n");
                        empDesignation = designationChoice(input);
                        break;
           }
        }catch(Exception ex){
            System.out.println("!!WRONG ENTRY!!"+"\n");
            input.nextLine();
            empDesignation = designationChoice(input);
        }
        return empDesignation;
    }
    public static String departmentChoice(Scanner input){
            System.out.println("\n"+"ENTER THE DEPARTMENT"+"\n"+"1. HUMAN RESOURCE"+"\n"+"2.IT"+"\n"+"3.FINANCE"+"\n"+"4.MARKETING"+"\n"+"5.R&D"+"\n"+"6.PRODUCTION");
            String empDepartment = "";
            try{
                Scanner option = new Scanner(System.in);
                int choice = input.nextInt();
                switch(choice){
                    case 1:
                        empDepartment = "HUMAN RESOURCE";
                        break;
                    case 2:
                        empDepartment = "IT";
                        break;
                    case 3:
                        empDepartment = "FINANCE";
                        break;
                    case 4:
                        empDepartment = "MARKETING";
                        break;
                    case 5:
                        empDepartment = "R&D";
                        break;
                    case 6:
                        empDepartment = "PRODUCTION";
                        break;
                    default:
                        System.out.println("!! WRONG ENTRY !!"+"\n");
                        empDepartment = departmentChoice(input);
                        break;
                }
            }catch(Exception ex){
                System.out.println("!!WRONG ENTRY!!"+"\n");
                input.nextLine();
                empDepartment = departmentChoice(input);
            }
            return empDepartment;
    }
    public static double checkSalary(Scanner input){
        System.out.println("ENTER THE SALARY :");
        double salary = 0.0;
        try{
            salary = input.nextDouble();
            if(salary < 5000 || salary > 1000000){
                System.out.println("!!WRONG ENTRY!! PLEASE ENTER THE VALID SALARY");
                salary = checkSalary(input);
            }
        }catch(Exception ex){
            System.out.println("!!WRONG ENTRY!! PLEASE ENTER THE VALID SALARY");
            input.nextLine();
            salary = checkSalary(input);
        }
        return salary;
    }
    public static void main(String args[]){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        System.out.println("------------------------------------");
        while(true){
            System.out.println("Do you want to proceed? yes or no");
            Scanner option = new Scanner(System.in);
            String choice = option.next();
            if(choice.equals("yes")){
                Scanner input = new Scanner(System.in);
                System.out.print("ENTER THE EMPLOYEE DETAILS"+"\n");
                String empName = validateName(input);
                String empDepartment = departmentChoice(input);
                String empDesignation = designationChoice(input);
                double empSalary = checkSalary(input);
                Employee employee = new Employee(empName,empDepartment,empDesignation,empSalary);
                employees.add(employee);
            }
            else{
                break;
            }
        }
        for(int i=0;i<employees.size();i++){
            System.out.println("\n"+"EMPLOYEE DETAILS :");
            employees.get(i).ToString();
            employees.get(i).SetAllowance();
            System.out.println("\n"+"AFTER ALLOWANCE :");
            employees.get(i).ToString();
        }
    }
}