import java.util.Scanner;
public class Employee{
    private static int empCounter = 1001;
    private int empId;
    private String empName;
    private String empDepartment;
    private String empDesignation;
    private double empSalary;
    public Employee(String empName,String empDepartment,String empDesignation,double empSalary){
        this.empName = empName;
        this.empDepartment = empDepartment;
        this.empDesignation = empDesignation;
        this.empSalary = empSalary;
        this.empId = empCounter++;
    }
    public int getempid(){
        return this.empId;
    }
    public void setempid(int empId){
        this.empId = empId;
    }
    public String getname(){
        return this.empName;
    }
    public void setName(String empName){
        this.empName = empName;
    }
    public String getDepartment(){
        return this.empDepartment;
    }
    public void setDepartment(String Department){
        this.empDepartment = Department;
    }
    public String getDesignation(){
        return this.empDesignation;
    }
    public void setDesignation(String Designation){
        this.empDesignation = Designation;
    }
    public double getSalary(){
        return this.empSalary;
    }
    public void setSalary(double Salary){
        this.empSalary = Salary;
    }
    public void SetAllowance(){
        double allow = 0.0;
        String designation = this.empDesignation;
        designation = designation.toUpperCase();
        if(designation.contains("MANAGER")){
            allow = (20 * this.empSalary)/100;
        }
        else {
            allow = (10 * this.empSalary)/100;
        }
        this.empSalary += allow;
    }

    public void ToString(){
        System.out.println();
        System.out.print("EMPLOYEE ID: ");
        System.out.println(this.empId);
        System.out.print("EMPLOYEE NAME: ");
        System.out.println(this.empName);
        System.out.print("EMPLOYEE DEPARTMENT: ");
        System.out.println(this.empDepartment);
        System.out.print("EMPLOYEE DESIGNATION: ");
        System.out.println(this.empDesignation);
        System.out.print("EMPLOYEE SALARY: ");
        System.out.println(this.empSalary);
    }
}