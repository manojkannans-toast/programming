using System;
using System.Collections.Generic;
using EMP;
public class TestEmployee{
    public static void Main(string[] args){
        List<Employee> employees = new List<Employee>();
        while(true){
            string choice = Console.ReadLine();
            if(choice.Equals("yes")){
                Employee employee = new Employee();
                string name = Console.ReadLine();
                employee.EmpName = name;
                string department = Console.ReadLine();
                employee.Department = department;
                string designation = Console.ReadLine();
                employee.Designation = designation;
                double salary = Double.Parse(Console.ReadLine());
                employee.Salary = salary;
                employees.add(employee);
            }
            else{
                break;
            }
        }
    }
}