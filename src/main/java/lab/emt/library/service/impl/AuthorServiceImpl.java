package lab.emt.library.service.impl;

import lab.emt.library.model.Author;
import lab.emt.library.model.Country;
import lab.emt.library.model.dto.AuthorDto;
import lab.emt.library.repository.AuthorRepository;
import lab.emt.library.service.AuthorService;
import lab.emt.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> listAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author addAuthor(AuthorDto authorDto) {
        Author author = new Author();
        return saveAuthor(authorDto, author);
    }


    @Override
    public Author editAuthor(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElse(null);
        return saveAuthor(authorDto, author);
    }

    @Override
    public void deleteAuthor(Long id) {
    }

    private Author saveAuthor(AuthorDto author, Author a) {
        Country country = countryService.findCountryById(author.getCountry());

        if (country == null) {
            return null;
        }

        a.setName(author.getName());
        a.setSurname(author.getSurname());
        a.setCountry(country);

        return authorRepository.save(a);
    }
}
