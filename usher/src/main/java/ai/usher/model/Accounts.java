package ai.usher.model;

import ai.usher.exceptions.BadRequestException;
import ai.usher.exceptions.Xception;
import ai.usher.utils.StringHelper;

public class Accounts {
    private static Accounts instance;

    public static Accounts get() {
        if (instance == null) {
            instance = new Accounts();
        }
        return instance;
    }

    private Accounts() {
    }

    public Account getOrCreateAccount(String email, String name, String picture, String phone) throws Xception {
        if (StringHelper.isBlank(email) || StringHelper.isBlank(name)) {
            throw new BadRequestException("Invalid email or name");
        }
        Account a = Account.get(email);
        if (a != null) {
            return a;
        }
        a = new Account(email, phone, 0, name, picture);
        a.save();
        return a;
    }
}
