package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @Test
    public void testLocale() {
        LocalizationService localizationService = new LocalizationServiceImpl();

        LocalizationService mockedLocalizationService = Mockito.mock(LocalizationService.class);

        when(mockedLocalizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        when(mockedLocalizationService.locale(Country.USA)).thenReturn("Welcome");
        when(mockedLocalizationService.locale(Country.BRAZIL)).thenReturn("Welcome");

        String russianText = mockedLocalizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", russianText);

        String usaText = mockedLocalizationService.locale(Country.USA);
        assertEquals("Welcome", usaText);

        String franceText = mockedLocalizationService.locale(Country.BRAZIL);
        assertEquals("Welcome", franceText);
    }
}
