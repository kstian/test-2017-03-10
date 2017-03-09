package com.github.ktarou;

import java.time.LocalDate;

/**
 *
 */
public class User {
    private UserType type;
    private LocalDate registeredDate;

    public User(UserType type, LocalDate registeredDate) {
        this.type = type;
        this.registeredDate = registeredDate;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}
