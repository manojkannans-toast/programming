public class EmployeeAttendance<K,V>{
    private K employee;
    private V attendance;
    public EmployeeAttendance(){
        this.employee = null;
        this.attendance = null;
    }
    public EmployeeAttendance(K employee,V value){
        this.employee = employee;
        this.attendance = value;
    }
    public K getKey(){
        return this.employee;
    }
    public V getValue(){
        return this.attendance;
    }
    public void setKey(K employee){
        this.employee = employee;
    }
    public void setValue(V attendance){
        this.attendance = attendance;
    }
}