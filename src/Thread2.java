public class Thread2 implements Runnable {

    private Bank bank;

    public Thread2(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        //bank.inc();
        synchronized (bank) {
            for (; bank.getAccount() < 20000; bank.setAccount(bank.getAccount() + 2))
                System.out.println("inc: " + bank.getAccount());
        }
    }
}
