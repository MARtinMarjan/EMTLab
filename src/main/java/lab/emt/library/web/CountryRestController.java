package lab.emt.library.web;

import lab.emt.library.model.Country;
import lab.emt.library.model.dto.CountryDto;
import lab.emt.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.findCountryById(id);
        if (country == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @GetMapping()
    public List<Country> listAllCountries() {
        return this.countryService.listAllCountries();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto countryDto) {
        Country country = this.countryService.addCountry(countryDto);
        if (country == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        Country country = this.countryService.editCountry(id, countryDto);
        if (country == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete (@PathVariable Long id){
        Country country = this.countryService.findCountryById(id);
        if(country == null ){
            return ResponseEntity.notFound().build();
        }else{
            this.countryService.deleteCountry(id);
            return ResponseEntity.ok().build();
        }
    }

}
