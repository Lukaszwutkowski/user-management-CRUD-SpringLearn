package com.sda.userSda.model;


import com.sda.userSda.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "uzytkownicy")
@NamedQuery(name = "dateBetween", query = "select u from User u where u.birthDate >= :minDate and u.birthDate <= :maxDate ")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "identyfikator")
    private int userId;

    @Size(min = 2, message = "Za krotkie imie")
    @Size(max = 20, message = "Za dlugie imie")
    @Column(name = "imie")
    private String firstName;

    @Size(min = 2, message = "Za krotkie nazwisko")
    @Size(max = 20, message = "Za dlugie nazwisko")
    @Column(name = "nazwisko")
    private String lastName;

    @Column(name = "data_urodzenia")
    private LocalDate birthDate;

    @Pattern(regexp = "[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "Nieprawidlowy format daty")
    @Transient
    private String birthDateTemp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateTemp() {
        if (birthDate != null) {
            birthDateTemp = birthDate.format(Utils.formatter);
        }
        return birthDateTemp;
    }

    public void setBirthDateTemp(String birthDateTemp) {
        this.birthDateTemp = birthDateTemp;
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.birthDate = LocalDate.parse(birthDateTemp, Utils.formatter);
        } catch (DateTimeParseException e) {
            // normal when you choose nothing
        }
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }


}
