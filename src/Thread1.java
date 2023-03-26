public class Thread1 implements Runnable {

    private Bank bank;

    public Thread1(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        //bank.dec();
        synchronized (bank) {
            for (; bank.getAccount() > 1; bank.setAccount(bank.getAccount() - 1))
                System.out.println("dec: " + bank.getAccount());
        }
    }
}
