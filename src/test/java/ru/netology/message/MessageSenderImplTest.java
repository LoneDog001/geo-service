package ru.netology.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geoLocal.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;


public class MessageSenderImplTest {

    @Test
    public void messageSenderImplTestUsa() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        LocalizationServiceImpl locService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(locService.locale(USA)).thenReturn("Welcome");

        MessageSenderImpl mess = new MessageSenderImpl(geoService, locService);
        String expected = "Welcome";
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String result = mess.send(map);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void messageSenderImplTestRussia() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        LocalizationServiceImpl locService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(locService.locale(RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl mess = new MessageSenderImpl(geoService, locService);
        String expected = "Добро пожаловать";
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String result = mess.send(map);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void messageSenderImplTestOtherCountrie() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("127.0.0.1")).thenReturn(new Location(null, null, null, 0));
        LocalizationServiceImpl locService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(locService.locale(RUSSIA)).thenReturn("Welcome");

        MessageSenderImpl mess = new MessageSenderImpl(geoService, locService);
        String expected = null;
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "127.0.0.1");
        String result = mess.send(map);
        Assertions.assertEquals(expected, result);
    }
}


