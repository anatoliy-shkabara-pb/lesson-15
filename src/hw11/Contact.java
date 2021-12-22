package hw11;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private List<String> phoneNumbers;
    private String address;
    private LocalDateTime changeTime;

    public Contact() {
    }

    public Contact(int id, String name, LocalDate dateOfBirth,
                   List<String> phoneNumbers, String address) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.changeTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(name, contact.name) && Objects.equals(dateOfBirth, contact.dateOfBirth) && Objects.equals(phoneNumbers, contact.phoneNumbers) && Objects.equals(address, contact.address) && Objects.equals(changeTime, contact.changeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, phoneNumbers, address, changeTime);
    }
}