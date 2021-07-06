package manyPersonsProblem;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;
@JsonPropertyOrder({"Name","Surname","Phone","Email","Address","Age"})
public class Person {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String address;
    private String age;

    public Person() {
    }

    public Person(String name, String surname, String phone, String email, String address, String age) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(email, person.email) &&
                Objects.equals(address, person.address) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phone, email, address, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
