package catalog.model;

import catalog.exceptions.SubscriberException;

import java.util.Objects;

public class Subscriber {

    private String firstName;
    private String lastName;
    private String telephone;
    private String address;

    public Subscriber(String firstName, String lastName, String telephone, String address)
            throws SubscriberException {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setTelephone(telephone);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws SubscriberException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new SubscriberException("\nFirst name is required!\n");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws SubscriberException {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new SubscriberException("\nLast name is required!\n");
        }
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws SubscriberException {
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new SubscriberException("\nTelephone is required!\n");
        }
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws SubscriberException {
        if (address == null || address.trim().isEmpty()) {
            throw new SubscriberException("\nAddress is required!\n");
        }
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber subscriber = (Subscriber) o;
        return Objects.equals(firstName, subscriber.firstName) &&
                Objects.equals(lastName, subscriber.lastName) &&
                Objects.equals(telephone, subscriber.telephone) &&
                Objects.equals(address, subscriber.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, telephone, address);
    }

    @Override
    public String toString() {
        return "FirstName: '" + firstName + '\'' +
                ", LastName: '" + lastName + '\'' +
                ", telephone: '+7" + telephone + '\'' +
                ", address: '" + address;
    }
}
