package lukuvinkkikirjasto.controller;

import static org.apache.http.client.methods.RequestBuilder.post;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addTest() throws Exception {
        this.mockMvc.perform(get("/test"))
                .andExpect(content().string("Hello!"));
    }

    @Test
    public void bookListHasBooks() throws Exception {
        ResultActions res = this.mockMvc.perform(get("/books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(status().isOk());
    }

    @Test
    public void bookPageHasBook() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("author", "kirjoittaja"));

        this.mockMvc.perform(get("/books/{id}", "kirja"))
                .andExpect(model().attributeExists("book"))
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void addingBookWorks() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("author", "kirjoittaja"))
                .andReturn();
        assertTrue(result.getFlashMap().containsValue("Kirjan lisääminen onnistui!"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void deletingBookWorks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("author", "kirjoittaja"));

        MvcResult result2 = this.mockMvc.perform(delete("/books/{id}", "kirja"))
                .andReturn();
        assertTrue(result2.getFlashMap().containsValue("Vinkin poistaminen onnistui!"));
    }

    @Test
    public void editingBookWorks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("author", "kirjoittaja"));

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/books/{id}", "kirja")
                .param("title", "kirja")
                .param("author", "kirjoittaja")
                .param("description", "hyvä")
                .param("tags", "tagit"))
                .andReturn();
        assertTrue(result2.getFlashMap().containsValue("Kirjan muokkaaminen onnistui!"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void addingVideoWorks() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("link", "https://www.youtube.com/watch?testi"))
                .andReturn();
        assertTrue(result.getFlashMap().containsValue("Videon lisääminen onnistui!"));

        this.mockMvc.perform(delete("/books/{id}", "video"));
    }

    /*@Test
    public void editingVideoWorks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("link", "http://testi"))
                .andReturn();

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos/{id}", "video")
                .param("title", "video")
                .param("link", "http://testi")
                .param("uploader", "youtuber")
                .param("description", "hyvä")
                .param("tags", "tagit"))
                .andReturn();
        assertTrue(result2.getFlashMap().containsValue("Videon muokkaaminen onnistui!"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }*/
    
    @Test
    public void searchReturnsAList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("description", "Testikirja")
                .param("author", "kirjoittaja"));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/search")
                .param("keyword", "testi"))
                .andExpect(model().attributeExists("books"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }
}
