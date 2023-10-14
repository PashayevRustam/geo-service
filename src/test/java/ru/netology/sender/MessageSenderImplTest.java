package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSender messageSender;

    @Test
    public void testSendRussianMessage() {

        String ipAddress = "172.123.12.19";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        when(geoService.byIp(ipAddress)).thenReturn(new Location("Moscow", Country.RUSSIA, "Moscow", 50));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String russianResult = messageSender.send(headers);

        assertEquals("Добро пожаловать", russianResult);
    }

    @Test
    public void testSendEnglishMessage() {
        String ipAddress = "96.2.3.4";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        when(geoService.byIp(ipAddress)).thenReturn(new Location("USA", Country.USA, "USA", 50));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String usaResult = messageSender.send(headers);

        assertEquals("Welcome", usaResult);
    }
}