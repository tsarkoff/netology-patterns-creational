import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age = -1;
    protected String city;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public boolean hasAge() {
        return age > -1;
    }

    public boolean hasAddress() {
        return city != null && !city.isEmpty() && !city.isBlank();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return OptionalInt.of(age);
    }

    public Person setAge(int age) {
        if (age > -1) {
            this.age = age;
        }
        return this;
    }

    public String getAddress() {
        return city;
    }

    public Person setAddress(String address) {
        if (address != null && !address.isBlank() && !address.isEmpty()) {
            this.city = address;
        }
        return this;
    }

    public Person happyBirthday() {
        age++;
        return this;
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder().setSurname(getSurname()).setAddress(getAddress()).setAge(1);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}