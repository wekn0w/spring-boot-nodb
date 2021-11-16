package web.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Author;
import web.repo.AuthorRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional//todo разобраться какую зависимость брать spring/javax
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo userRepository;

    @Override
    public List<Author> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Author getOneById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()  -> new NotFoundException("No record found by id="+id));
    }

    @Override
    public Author save(Author person) {
        Author author = userRepository.findById(person.getId()).orElse(new Author());
        if (person.getFullname() != null && !person.getFullname().isEmpty()) {
            author.setFullname(person.getFullname());
        }
        if (person.getAge() != null) {
            author.setAge(person.getAge());
        }
        return userRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
