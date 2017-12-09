package lukuvinkkikirjasto.controller;

import java.util.List;
import lukuvinkkikirjasto.domain.Tip;
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
public class TipControllerTest {

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
                .andExpect(model().attributeExists("readTips"))
                .andExpect(model().attributeExists("notReadTips"))
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
    public void videoPageHasVideo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("url", "http://testi"));

        this.mockMvc.perform(get("/videos/{id}", "video"))
                .andExpect(model().attributeExists("video"))
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/books/{id}", "video"));
    }

    @Test
    public void nonexistantVideoRedirectCausesErrorMessage() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/videos/{id}", "tuntematonVideo")).andReturn();

        assertTrue(result.getFlashMap().containsValue("Jokin meni vikaan... :("));

        //this.mockMvc.perform(delete("/books/{id}", "video"));
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
    public void addingBookFailsWhenNoTitle() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "")
                .param("author", "kirjoittaja"))
                .andReturn();
        assertTrue(result.getFlashMap().containsValue("Kirjan lisääminen epäonnistui. "
                + "Nimi ja tekijä ovat pakollisia kenttiä."));

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
    public void markingBookAsReadPutsBookOnReadList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("author", "kirjoittaja"));

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/books/{id}", "kirja")
                .param("title", "kirja")
                .param("author", "kirjoittaja")
                .param("description", "hyvä")
                .param("tags", "tagit")
                .param("read", "true"))
                .andReturn();

        MvcResult result3 = this.mockMvc.perform(get("/books")).andReturn();

        assertTrue(checkIfTipIsOnModelList("kirja", "readTips"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void editingVideoFailsWhenAuthorIsEmpty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("author", "kirjoittaja"));

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/books/{id}", "kirja")
                .param("title", "kirja")
                .param("author", "")
                .param("description", "hyvä")
                .param("tags", "tagit"))
                .andReturn();
        assertTrue(result2.getFlashMap().containsValue("Kirjan muokkaaminen epäonnistui. Nimi ja tekijä eivät voi olla tyhjiä."));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void addingVideoWorks() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("url", "http://testi"))
                .andReturn();
        assertTrue(result.getFlashMap().containsValue("Videon lisääminen onnistui!"));

        this.mockMvc.perform(delete("/books/{id}", "video"));
    }

    @Test
    public void addingVideoFailsWhenNoTitle() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "")
                .param("url", "http://testi"))
                .andReturn();
        assertTrue(result.getFlashMap().containsValue("Videon lisääminen epäonnistui. "
                + "Nimi ja linkki ovat pakollisia kenttiä."));

        this.mockMvc.perform(delete("/books/{id}", "video"));
    }

    @Test
    public void editingVideoWorks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("url", "http://testi"))
                .andReturn();

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos/{id}", "video")
                .param("title", "video")
                .param("url", "http://testi")
                .param("uploader", "youtuber")
                .param("description", "hyvä")
                .param("tags", "tagit"))
                .andReturn();
        assertTrue(result2.getFlashMap().containsValue("Videon muokkaaminen onnistui!"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void markingVideoAsReadPutsBookOnReadList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("url", "http://testi"))
                .andReturn();

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos/{id}", "video")
                .param("title", "video")
                .param("url", "http://testi")
                .param("uploader", "youtuber")
                .param("description", "hyvä")
                .param("tags", "tagit")
                .param("watched", "true"))
                .andReturn();
        assertTrue(checkIfTipIsOnModelList("video", "readTips"));

        this.mockMvc.perform(delete("/books/{id}", "video"));
    }

    @Test
    public void editingVideoFailsWhenUrlIsEmpty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/videos")
                .param("title", "video")
                .param("url", "http://testi"))
                .andReturn();

        MvcResult result2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/videos/{id}", "video")
                .param("title", "video")
                .param("url", "")
                .param("uploader", "youtuber")
                .param("description", "hyvä")
                .param("tags", "tagit"))
                .andReturn();
        assertTrue(result2.getFlashMap().containsValue("Videon muokkaaminen epäonnistui. Nimi ja linkki eivät voi olla tyhjiä."));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    @Test
    public void searchReturnsAList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .param("title", "kirja")
                .param("description", "Testikirja")
                .param("author", "kirjoittaja"));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/search")
                .param("keyword", "testi"))
                .andExpect(model().attributeExists("readTips"))
                .andExpect(model().attributeExists("notReadTips"));

        this.mockMvc.perform(delete("/books/{id}", "kirja"));
    }

    public boolean checkIfTipIsOnModelList(String title, String modelList) throws Exception {
        MvcResult result = this.mockMvc.perform(get("/books")).andReturn();

        List<Tip> tips = (List<Tip>) result.getModelAndView().getModel().get(modelList);

        boolean isOnList = false;
        for (Tip tip : tips) {
            if (tip.getTitle().equals(title)) {
                isOnList = true;
            }
        }
        return isOnList;
    }
}
