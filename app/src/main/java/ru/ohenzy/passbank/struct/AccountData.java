package ru.ohenzy.passbank.struct;

import java.io.Serializable;

public class AccountData implements Serializable {
    private final String name;
    private final String login;
    private final String password;

    public AccountData() {
        this.name = "";
        this.login = "";
        this.password = "";
    }

    public AccountData(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
