import static java.lang.String.format;

public class PersonBuilder {
    private String name = "";
    private String surname = "";
    private int age = -1;
    private String city = "";
    private final String errMsg = "\nError. IllegalArgumentException. Person's %s (%s) is incorrect";

    public PersonBuilder setName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(format(errMsg, "name", name));
        }
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        if (surname == null || surname.isEmpty() || surname.isBlank()) {
            throw new IllegalArgumentException(format(errMsg, "surname", surname));
        }
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(format(errMsg, "age", age));
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.city = address;
        return this;
    }

    public Person build() {
        String msg = "";
        if (name.isEmpty()) {
            msg += "\n - name";
        }
        if (surname.isEmpty()) {
            msg += "\n - surname";
        }
        if (age == -1) {
            msg += "\n - age";
        }
        if (!msg.isEmpty()) {
            msg = "\nError. Impossible to build a Person due to the next unset parameter(s):" + msg;
            throw new IllegalStateException(msg);
        }
        return new Person(name, surname, age).setAddress(city);
    }
}
