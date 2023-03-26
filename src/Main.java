public class Main {
    public static void main(String[] args)  {
        try {
            Employee employee = new Employee("qewrtqwrqwerqwerqwerqwer", "qwerqwerqewr", 123);
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            System.out.println(e.getMessage()); //NOT THROW NEW BECAUSE OF DEMONSTRATION
        }

        try {
            Employee employee = new Employee("sdf", "sfgsfdgdfgsdgffdgfgsdf", 123);
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            System.out.println(e.getMessage()); //NOT THROW NEW BECAUSE OF DEMONSTRATION
        }

        try {
            Employee employee = new Employee("dfgdsfg", "sfgsdfgfg", -123);
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            System.out.println(e.getMessage()); //NOT THROW NEW BECAUSE OF DEMONSTRATION
        }

        try {
            Employee employee = new Employee("dfgsdfg", "qwerqwerqewr", 123);
            employee.setSalary(-123);
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            System.out.println(e.getMessage()); //NOT THROW NEW BECAUSE OF DEMONSTRATION
        }
    }
}
