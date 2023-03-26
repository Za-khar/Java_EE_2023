public class Employee {

    private int id;
    private static Integer nextId = -1;
    private String name;
    private String surname;
    private double salary;

    public Employee(String name, String surname, double salary)
            throws FieldLengthLimitException, IncorrectSalaryException {
        if(name.length() > 20) throw new FieldLengthLimitException("Incorrect name length, < 20");
        if(surname.length() > 20) throw new FieldLengthLimitException("Incorrect surname length, < 20");
        if(salary < 0) throw new IncorrectSalaryException("Incorrect salary,must be > 0");
        this.id = ++nextId;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public void setName(String name) throws FieldLengthLimitException {
        if(name.length() > 20) throw new FieldLengthLimitException("Incorrect name length, < 20");
        this.name = name;
    }

    public void setSurname(String surname) throws FieldLengthLimitException {
        if(surname.length() > 20) throw new FieldLengthLimitException("Incorrect surname length, < 20");
        this.surname = surname;
    }

    public void setSalary(double salary) throws IncorrectSalaryException {
        if(salary < 0) throw new IncorrectSalaryException("Incorrect salary,must be > 0");
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Integer getNextId() {
        return nextId;
    }

    public static void setNextId(Integer nextId) {
        Employee.nextId = nextId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

}
