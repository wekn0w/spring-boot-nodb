package web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import web.domain.Author;
import web.service.AuthorService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorService authorService;

    @DisplayName("Should call AuthourService findAll and return 200 OK")
    @Test
    void shouldReturnOkWhenFindAll() throws Exception {
        when(authorService.findAll())
                .thenReturn(Collections.singletonList(new Author("IamReal", 1)));
        mvc.perform(get("/author")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"));

        verify(authorService, times(1)).findAll();
    }

    @DisplayName("Should call AuthourService getOneById and return 200 OK")
    @Test
    void shouldReturnOkWhenGetById() throws Exception {
        when(authorService.getOneById(any()))
                .thenReturn(new Author("IamReal", 2));
        mvc.perform(get("/author/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(2));

        verify(authorService, times(1)).getOneById(1L);
    }

    @DisplayName("Should call AuthourService save and return 200 OK")
    @Test
    void shouldReturnOkWhenSave() throws Exception {
        when(authorService.save(any()))
                .thenReturn(new Author("IamReal", 2));
        mvc.perform(post("/author/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString((new Author("IamReal", 2))))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(2));

        verify(authorService, times(1)).save(any());
    }

    @DisplayName("Should call AuthourService deleteById and return 200 OK")
    @Test
    void shouldReturnOkWhenDeleteById() throws Exception {
        doNothing().when(authorService).deleteById(any());
        mvc.perform(delete("/author/{id}", 1L))
                .andExpect(status().isAccepted());

        verify(authorService, times(1)).deleteById(any());
    }
}