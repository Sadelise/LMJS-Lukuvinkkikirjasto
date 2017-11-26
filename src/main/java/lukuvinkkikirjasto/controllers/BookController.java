package lukuvinkkikirjasto.controllers;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    TipDao tipDao = new FireBaseTipDao();

    // This is a test method
    @GetMapping("/test")
    @ResponseBody //returns plain object, does not try to find a template
    public String test(Model model) {
        return "Hello!";
    }

    @GetMapping("/books")
    public String listBooks(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("books", tipDao.getAllTips());
        return "books";
    }

    @GetMapping("/books/{id}")
    public String viewBook(Model model, @PathVariable String id) {
        Tip tip = tipDao.getTip(id);
        if (tip != null) {
            model.addAttribute("book", tip);
            return "book";
        }
        return "redirect:/fail";
    }

    @PostMapping("/books/{id}")
    public String viewBook(Model model,RedirectAttributes redirectAttributes, @PathVariable String id, @RequestParam String author, @RequestParam String title,
            @RequestParam String description, @RequestParam String ISBN) {
        tipDao.editTipByTitle(title, "title", id);
        tipDao.editTipByTitle(title, "author", author);
        tipDao.editTipByTitle(title, "description", description);
        tipDao.editTipByTitle(title, "ISBN", ISBN);
        return "redirect:/books";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam String author, @RequestParam String title,
            @RequestParam String description, @RequestParam String ISBN) {
        if (author.trim().isEmpty() || title.trim().isEmpty()) {
            return "redirect:/fail";
        }
        tipDao.addTip(new Book(title, author));

        return "redirect:/books";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("message", "fail"); // jos haluaa viestin täältä... 
        return "fail";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(Model model, @PathVariable String id) {
        tipDao.removeTip(id);
        return "redirect:/books";
    }
}
