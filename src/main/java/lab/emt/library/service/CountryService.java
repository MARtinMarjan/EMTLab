package lab.emt.library.service;

import lab.emt.library.model.Country;
import lab.emt.library.model.dto.CountryDto;

import java.util.List;

public interface CountryService {
    Country findCountryById(Long country);

    List<Country> listAllCountries();

    Country addCountry(CountryDto country);

    Country editCountry(Long id,CountryDto countryDto);

    void deleteCountry(Long id);
}
