package lukuvinkkikirjasto.controllers;

import java.util.List;
import lukuvinkkikirjasto.dao.BasicTipDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lukuvinkkikirjasto.dao.FireBaseTipDao;
import lukuvinkkikirjasto.dao.TipDao;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    TipDao tipDao = new FireBaseTipDao();

    public void setTipDao(TipDao tipDao) {
        this.tipDao = tipDao;
    }

    // This is a test method
    @GetMapping("/test")
    @ResponseBody //returns plain object, does not try to find a template
    public String test(Model model) {
        return "Hello!";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", tipDao.getAllTips());
        return "books";
    }

    @GetMapping("/books/{id}")
    public String viewBook(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        Tip tip = tipDao.getTip(id);
        if (tip != null) {
            model.addAttribute("book", tip);
            return "book";
        }
        redirectAttributes.addFlashAttribute("message", "Jokin meni vikaan... :(");
        return "redirect:/fail";
    }

    @PostMapping("/books/{id}")
    public String editBook(Model model, RedirectAttributes redirectAttributes, @PathVariable String id, 
            @RequestParam String author, @RequestParam String title,
            String description, String ISBN, boolean read) {
        if (author.trim().isEmpty() || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Kirjan muokkaaminen epäonnistui. Nimi ja tekijä eivät voi olla tyhjiä.");
            return "redirect:/books";
        }
        tipDao.editTipByTitle(title, "title", id);
        tipDao.editTipByTitle(title, "author", author);
        tipDao.editTipByTitle(title, "description", description);
        tipDao.editTipByTitle(title, "ISBN", ISBN);

        tipDao.markTip(id);

        redirectAttributes.addFlashAttribute("message", "Kirjan muokkaaminen onnistui!");
        return "redirect:/books";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam String author, @RequestParam String title,
            String description, String ISBN, RedirectAttributes redirectAttributes) {
        if (author.trim().isEmpty() || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Kirjan lisääminen epäonnistui. Nimi ja tekijä ovat pakollisia kenttiä.");
            return "redirect:/books";
        }
        tipDao.addTip(new Book(title, author, description, ISBN));
        redirectAttributes.addFlashAttribute("message", "Kirjan lisääminen onnistui!");
        return "redirect:/books";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        return "fail";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        if (tipDao.getTip(id) != null) {
            tipDao.removeTip(id);
            redirectAttributes.addFlashAttribute("message", "Kirjan poistaminen onnistui!");
            return "redirect:/books";
        }
        redirectAttributes.addFlashAttribute("message", "Kirjan poistaminen epäonnistui!");
        return "redirect:/books";
    }

    @PostMapping("/search")
    public String searchTips(Model model, @RequestParam String keyword) {
        List<Tip> results = tipDao.searchByKeyword(keyword);
        if (results.isEmpty()) {
            model.addAttribute("message", "Mitään ei löytynyt. Hae tyhjällä kentällä jos haluat nähdä kaikki vinkit.");
        }
        model.addAttribute("books", results);
        return "books";
    }
}
