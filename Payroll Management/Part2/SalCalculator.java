public class SalCalculator{
    public void calculateSalary(LinkedHashSet<EmployeeAttendance<Employee,Integer>> eligiblelist){
        for(EmployeeAttendance<Employee,Integer> employee : )
        {
            double pf = (10 * (eligiblelist.getKey().getSalary()))/100;
            double net_salary = eligiblelist.getKey().getSalary() - pf;
            return net_salary;
        }
    }       
}