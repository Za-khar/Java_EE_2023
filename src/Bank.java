public class Bank {

    private Integer account;

    public Bank() {
        account = 0;
    }

    public void inc() {
        for (; account < 20000; account += 2) {
            System.out.println("inc: " + account);
        }
    }

    public void dec() {
        for (; account > 1; account--) {
            System.out.println("dec: " + account);
        }
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getAccount() {
        return account;
    }
}
