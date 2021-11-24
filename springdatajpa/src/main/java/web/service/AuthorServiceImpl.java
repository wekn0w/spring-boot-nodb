package web.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import web.domain.Author;
import web.dto.AuthorDto;
import web.repo.AuthorRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo userRepository;

    public AuthorServiceImpl(AuthorRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = userRepository.findAll();
        List<AuthorDto> resultList = new ArrayList<>();
        for (Author record : authors) {
            resultList.add(new AuthorDto(record.getId(), record.getFullname(), record.getAge()));
        }
        return resultList;
    }

    @Override
    public AuthorDto getOneById(Long id) throws NotFoundException {
        Author stored = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No record found by id=" + id));
        return new AuthorDto(stored.getFullname(), stored.getAge());
    }

    @Transactional
    @Override
    public AuthorDto save(AuthorDto person) {
        Author author = userRepository.findById(person.getId()).orElse(new Author());
        if (person.getFullname() != null && !person.getFullname().isEmpty()) {
            author.setFullname(person.getFullname());
        }
        if (person.getAge() != null) {
            author.setAge(person.getAge());
        }
        Author saved = userRepository.save(author);
        return new AuthorDto(saved.getId(), saved.getFullname(), saved.getAge());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
