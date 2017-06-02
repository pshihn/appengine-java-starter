package ai.usher;

import ai.usher.model.Account;

public class ExecutionContext {
    private Account account;

    public ExecutionContext() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
