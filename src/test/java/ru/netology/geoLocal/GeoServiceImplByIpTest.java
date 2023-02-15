package ru.netology.geoLocal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.geoLocal.GeoServiceImpl.*;

import java.util.stream.Stream;

public class GeoServiceImplByIpTest {
    GeoServiceImpl sut;

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
        sut = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each test");
        sut = null;
    }

    @ParameterizedTest
    @MethodSource("byIp")
    public void byIpTest(String ip, Location expected) {
        System.out.println("Test byIp with parameters");
        Location result = sut.byIp(ip);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> byIp() {
        return Stream.of(Arguments.of(LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", "New York", Country.USA, null, 0));
    }

    @Test
    public void byCoordinates() {
        RuntimeException exception = assertThrows(
                RuntimeException.class, () -> {
                    throw new RuntimeException("Not implemented");
                }
        );
        assertEquals("Not implemented", exception.getMessage());
    }
}
