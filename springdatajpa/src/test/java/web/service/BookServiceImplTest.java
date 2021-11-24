package web.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import web.domain.Author;
import web.domain.Book;
import web.domain.Genre;
import web.dto.AuthorDto;
import web.dto.BookDto;
import web.dto.GenreDto;
import web.repo.AuthorRepo;
import web.repo.BookRepo;
import web.repo.GenreRepo;
import web.utils.BookAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl service;
    @Mock
    private BookRepo bookRepository;
    @Mock
    private AuthorRepo authorRepository;
    @Mock
    private GenreRepo genreRepository;
    @Spy
    private BookAdapter bookAdapter;

    @DisplayName("Should call save method from BookRepo and findAllById from AuthorRepo and GenreRepo when service call save method")
    @Test
    void shouldCallRepositoryOnSave() {
        when(bookRepository.save(any())).thenReturn(new Book("fake_book",
                new Genre("fake_genre"),
                new HashSet<>(Collections.singleton(new Author("fake_name", 11)))));
        BookDto p = new BookDto("Lukomorie",
                new GenreDto("Fiction"),
                new HashSet<>(Collections.singleton(new AuthorDto("Pushkin", 55)))
        );
        BookDto saved = service.save(p);
        verify(bookRepository, times(1)).save(any());
        verify(authorRepository, times(1)).findAllById(any());
        verify(genreRepository, times(1)).findById(any());
        assertAll(() -> assertThat(saved.getName()).isGreaterThanOrEqualTo("FAKE_BOOK"),
                () -> assertThat(saved.getBookGenre()).isNotNull(),
                () -> assertThat(saved.getBookGenre().getName()).isGreaterThanOrEqualTo("FAKE_GENRE"),
                () -> assertThat(saved.getBookAuthors()).isNotNull(),
                () -> assertThat(saved.getBookAuthors().size()).isOne()
        );
    }

    @DisplayName("Should call findAll method from BookRepo and return records when service call save method")
    @Test
    void shouldCallRepositoryOnFindAll() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(new Book("fake_book",
                new Genre("fake_genre"),
                new HashSet<>(Collections.singleton(new Author("fake_name", 11)))))));

        List<BookDto> savedList = service.findAll();
        verify(bookRepository, times(1)).findAll();
        assertAll(() -> assertThat(savedList).isNotNull(),
                () -> assertThat(savedList.size()).isOne(),
                () -> assertThat(savedList.get(0).getName()).isGreaterThanOrEqualTo("FAKE_BOOK"),
                () -> assertThat(savedList.get(0).getBookGenre()).isNotNull(),
                () -> assertThat(savedList.get(0).getBookGenre().getName()).isGreaterThanOrEqualTo("FAKE_GENRE"),
                () -> assertThat(savedList.get(0).getBookAuthors()).isNotNull(),
                () -> assertThat(savedList.get(0).getBookAuthors().size()).isOne()
        );
    }
}