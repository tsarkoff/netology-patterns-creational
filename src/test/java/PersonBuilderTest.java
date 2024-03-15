import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.OptionalInt;

public class PersonBuilderTest {
    PersonBuilder personBuilder;

    @BeforeEach
    public void beforeAll() {
        personBuilder = new PersonBuilder();
    }

    @ParameterizedTest(name = "#{index} Build Person by PersonBuilder args = {0}")
    @NullSource
    @ValueSource(strings = {
            " , ,18, ",
            "Анна,Вольф,31,Сидней",
            "Антошка,Вольф,0,Сидней",
            "Иваныч,Петров,131,Москва",
            "Алекс,Смит,-100,Берн",
            "Питер, ,10,Лион",
            " ,Браун,45,Ницца",
            "Том,Браун,45, ",
    })
    public void build(String personData) {
        String name;
        String surname;
        OptionalInt age;
        String address;
        if (personData != null) {
            String[] attrs = personData.split(",");
            name = attrs[0];
            surname = attrs[1];
            age = OptionalInt.of(Integer.parseInt(attrs[2]));
            address = attrs[3];
        } else {
            Assertions.assertThrows(
                    IllegalStateException.class,
                    () -> personBuilder.build()
            );
            return;
        }

        if (name.isBlank() || surname.isBlank() || age.orElse(-1) < 0 || age.orElse(-1) > 130) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> personBuilder.setName(name).setSurname(surname).setAge(age.orElse(-1)).setAddress(address).build()
            );
            return;
        }
        Assertions.assertNotNull(personBuilder.setName(name).setSurname(surname).setAge(age.orElse(-1)).setAddress(address).build());
    }

    @ParameterizedTest(name = "#{index} Setup PersonBuilder name = {0}")
    @NullSource
    @ValueSource(strings = {
            "",
            "Alexander",
            "'Alex' 'Ivanov'",
    })
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> personBuilder.setName(name)
            );
        } else {
            Assertions.assertEquals(personBuilder.setName(name), personBuilder);
        }
    }

    @ParameterizedTest(name = "#{index} Setup PersonBuilder surname = {0}")
    @NullSource
    @ValueSource(strings = {
            "",
            "Ivanov",
            "'Ivanov' 'Alex'",
    })
    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> personBuilder.setSurname(surname)
            );
        } else {
            Assertions.assertEquals(personBuilder.setSurname(surname), personBuilder);
        }
    }

    @ParameterizedTest(name = "#{index} Setup PersonBuilder age = {0}")
    @ValueSource(ints = {
            -1,
            0,
            100,
            131
    })
    public void setAge(int age) {
        if (age < 0 || age > 130) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> personBuilder.setAge(age)
            );
        } else {
            Assertions.assertEquals(personBuilder.setAge(age), personBuilder);
        }
    }

    @ParameterizedTest(name = "#{index} Setup PersonBuilder address = {0}")
    @NullSource
    @ValueSource(strings = {
            "",
            "Сидней",
            "Moscow"
    })
    public void setAddress(String address) {
        Assertions.assertEquals(personBuilder.setAddress(address), personBuilder);
    }
}