package ru.netology.local;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {

    LocalizationServiceImpl sut;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all");

    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each test");
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each test");
        sut = null;
    }

    @ParameterizedTest
    @MethodSource("ipWithLocale")
    public void localeTest(Country country, String expected) {
        System.out.println("Test LocaleTest with parameters");
        String actual = sut.locale(country);
        assertEquals(expected, actual);
}
    public static Stream<Arguments> ipWithLocale() {
        return Stream.of(Arguments.of(BRAZIL, "Welcome"),
                Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(GERMANY, "Welcome"),
                Arguments.of(USA, "Welcome"));
    }
}
