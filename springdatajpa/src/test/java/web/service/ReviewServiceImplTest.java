package web.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import web.domain.Book;
import web.domain.Review;
import web.dto.BookDto;
import web.dto.ReviewDto;
import web.repo.BookRepo;
import web.repo.ReviewRepo;
import web.utils.BookAdapter;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {
    @InjectMocks
    private ReviewServiceImpl service;
    @Mock
    private BookRepo bookRepository;
    @Mock
    private ReviewRepo reviewRepository;
    @Spy
    private BookAdapter bookAdapter;

    @DisplayName("Should call save method from ReviewRepo and findById from BookRepo when service call save method")
    @Test
    void shouldCallRepositoryOnSave() {
        when(reviewRepository.save(any())).thenReturn(new Review("Definitely a masterpiece",
                new Book("Lukomorie")));
        ReviewDto p = new ReviewDto(1L, "Lukomorie",
                new BookDto("Fiction"));
        ReviewDto saved = service.save(p);
        verify(reviewRepository, times(1)).save(any());
        verify(bookRepository, times(1)).findById(any());
        assertAll(() -> assertThat(saved.getComment()).isGreaterThanOrEqualTo("Definitely a masterpiece"),
                () -> assertThat(saved.getBook()).isNotNull(),
                () -> assertThat(saved.getBook().getName()).isGreaterThanOrEqualTo("Lukomorie"),
                () -> assertThat(saved.getBook().getBookAuthors()).isEqualTo(new HashSet<>()),
                () -> assertThat(saved.getBook().getBookGenre()).isEqualTo(null)
        );
    }

    @DisplayName("Should call find method from ReviewRepo and findById from BookRepo when service call save method")
    @Test
    void shouldCallRepositoryOnFindAll() {
        when(reviewRepository.findAll()).thenReturn(Collections.singletonList(new Review("Definitely a masterpiece",
                new Book("Lukomorie"))));
        ReviewDto p = new ReviewDto(1L, "Lukomorie",
                new BookDto("Fiction"));
        List<ReviewDto> savedList = service.findAll();
        verify(reviewRepository, times(1)).findAll();
        assertAll(() -> assertThat(savedList).isNotNull(),
                () -> assertThat(savedList.get(0).getComment()).isGreaterThanOrEqualTo("Definitely a masterpiece"),
                () -> assertThat(savedList.get(0).getBook()).isNotNull(),
                () -> assertThat(savedList.get(0).getBook().getName()).isGreaterThanOrEqualTo("Lukomorie")
        );
    }
}