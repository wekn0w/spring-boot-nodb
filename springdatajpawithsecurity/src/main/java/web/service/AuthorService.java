package web.service;

import javassist.NotFoundException;
import web.dto.AuthorDto;

import javax.transaction.Transactional;
import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

    AuthorDto getOneById(Long id) throws NotFoundException;

    AuthorDto save(AuthorDto person);

    @Transactional
//for JpaRepository.deleteById
    void deleteById(Long id);
}