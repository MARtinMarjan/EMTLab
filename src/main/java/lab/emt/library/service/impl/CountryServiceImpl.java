package lab.emt.library.service.impl;

import lab.emt.library.model.Country;
import lab.emt.library.model.dto.CountryDto;
import lab.emt.library.repository.CountryRepository;
import lab.emt.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findCountryById(Long country) {
        return countryRepository.findById(country).orElse(null);
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country addCountry(CountryDto country) {
        Country newCountry = new Country();
        newCountry.setName(country.getName());

        return this.countryRepository.save(newCountry);
    }

    @Override
    public Country editCountry(Long id, CountryDto countryDto) {
        Country newCountry = this.countryRepository.findById(id).orElse(null);
        if (newCountry == null) {
            return null;
        }
        return this.countryRepository.save(newCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        this.countryRepository.deleteById(id);
    }
}
