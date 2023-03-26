public class Main {

    public static void main(String[] args) {
        final Bank bank = new Bank();

        Thread2 thread2 = new Thread2(bank);
        Thread1 thread1 = new Thread1(bank);

        thread2.run();
        thread1.run();
    }
}
