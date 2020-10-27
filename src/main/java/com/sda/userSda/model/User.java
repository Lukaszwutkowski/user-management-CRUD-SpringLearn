package com.sda.userSda.model;


import com.sda.userSda.utils.Utils;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class User {
    private int userId;

    @Size(min = 2, message = "Za krotkie imie")
    @Size(max = 20, message = "Za dlugie imie")
    private String firstName;

    @Size(min = 2, message = "Za krotkie nazwisko")
    @Size(max = 20, message = "Za dlugie nazwisko")
    private String lastName;
    private LocalDate birthDate;

    @Pattern(regexp = "[\\d]{4}-[\\d]{2}-[\\d]{2}", message = "Nieprawidlowy format daty")
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
