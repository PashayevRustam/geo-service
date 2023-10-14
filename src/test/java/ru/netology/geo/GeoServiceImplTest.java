package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeoServiceImplTest {
    @Test
    public void testByIp() {
        GeoService geoService = new GeoServiceImpl();

        GeoService mockedGeoService = mock(GeoService.class);

        when(mockedGeoService.byIp("127.0.0.1")).thenReturn(null);
        when(mockedGeoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(mockedGeoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        when(mockedGeoService.byIp("172.10.20.30")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(mockedGeoService.byIp("96.50.60.70")).thenReturn(new Location("New York", Country.USA, null, 0));
        when(mockedGeoService.byIp("1.2.3.4")).thenReturn(null);

        Location localhostLocation = mockedGeoService.byIp("127.0.0.1");
        assertNull(localhostLocation);

        Location moscowLocation = mockedGeoService.byIp("172.0.32.11");
        assertNotNull(moscowLocation);
        assertEquals("Moscow", moscowLocation.getCity());
        assertEquals(Country.RUSSIA, moscowLocation.getCountry());
        assertEquals("Lenina", moscowLocation.getStreet());
        assertEquals(15, moscowLocation.getBuiling());

        Location newYorkLocation = mockedGeoService.byIp("96.44.183.149");
        assertNotNull(newYorkLocation);
        assertEquals("New York", newYorkLocation.getCity());
        assertEquals(Country.USA, newYorkLocation.getCountry());
        assertEquals("10th Avenue", newYorkLocation.getStreet());
        assertEquals(32, newYorkLocation.getBuiling());

        Location anotherMoscowLocation = mockedGeoService.byIp("172.10.20.30");
        assertNotNull(anotherMoscowLocation);
        assertEquals("Moscow", anotherMoscowLocation.getCity());
        assertEquals(Country.RUSSIA, anotherMoscowLocation.getCountry());
        assertNull(anotherMoscowLocation.getStreet());
        assertEquals(0, anotherMoscowLocation.getBuiling());

        Location anotherNewYorkLocation = mockedGeoService.byIp("96.50.60.70");
        assertNotNull(anotherNewYorkLocation);
        assertEquals("New York", anotherNewYorkLocation.getCity());
        assertEquals(Country.USA, anotherNewYorkLocation.getCountry());
        assertNull(anotherNewYorkLocation.getStreet());
        assertEquals(0, anotherNewYorkLocation.getBuiling());

        Location unknownLocation = mockedGeoService.byIp("1.2.3.4");
        assertNull(unknownLocation);
    }

}