package model;

import exception.MobileBankException;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {

    private long id;
    private String login;
    private String password;
    private String address;
    private String phone;

    public User() {}

    public User(long id, String login, String password, String address, String phone)
            throws MobileBankException {
        setId(id);
        setLogin(login);
        setPassword(password);
        setAddress(address);
        setPhone(phone);
    }

    public User(String login, String password, String address, String phone)
            throws MobileBankException {
        setLogin(login);
        setPassword(password);
        setAddress(address);
        setPhone(phone);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws MobileBankException {
        if (null == login || login.trim().isEmpty()) {
            throw new MobileBankException("Login is invalid");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws MobileBankException {
        if (null == password || password.trim().isEmpty()) {
            throw new MobileBankException("Password is invalid");
        }
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws MobileBankException {
        if (null == address || address.trim().isEmpty()) {
            throw new MobileBankException("Address is invalid!");
        }
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws MobileBankException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new MobileBankException("\nTelephone number is required!\n");
        }
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new MobileBankException("\nTelephone is invalid!\n");
        }
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(address, user.address) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, address, phone);
    }
}
