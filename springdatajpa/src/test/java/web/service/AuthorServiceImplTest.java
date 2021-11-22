package web.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import web.domain.Author;
import web.dto.AuthorDto;
import web.repo.AuthorRepo;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl service;
    @Mock
    private AuthorRepo repository;
    @Captor
    ArgumentCaptor<String> fullNameCaptor;

    @DisplayName("Should call findAll method from repository when service call findAll method")
    @Test
    void shouldCallRepositoryOnFindAll() {
        when(repository.findAll()).thenReturn(emptyList());
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @DisplayName("Should throw exception when nothing was found on service getOneById")
    @Test
    void shouldThrowExceptionWhenNotFoundByBy() {
        when(repository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.getOneById(123L));
    }

    @DisplayName("Should call save method from repository when service call save method")
    @Test
    void shouldCallRepositoryOnSave() {
        when(repository.save(any())).thenReturn(new Author("fake_name", 11));
        AuthorDto p = new AuthorDto("Human_name", 18);
        AuthorDto saved = service.save(p);
        verify(repository, times(1)).save(any());
        assertThat(saved.getFullname()).isGreaterThanOrEqualTo("FAKE_NAME");
        //check captor
        verify(repository).save(new Author(fullNameCaptor.capture(), 11));
    }

    @DisplayName("Should attempt calling deleteById from repository 2 times on service deleteById")
    @Test
    void shouldCallRepositoryOnDeleteById() {
        service.deleteById(0L);
        service.deleteById(1L);
        verify(repository, times(2)).deleteById(any());
    }
}