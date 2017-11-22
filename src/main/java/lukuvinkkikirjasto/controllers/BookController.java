package lukuvinkkikirjasto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    // This is a test method
    @GetMapping("/")
    @ResponseBody //returns plain object, does not try to find a template
    public String test(Model model) {
        return "Hello!";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
//        model.addAttribute("books", getAllTheBooks()); //give a list of book to thymeleaf
        return "books"; // refers to books.html
    }

    @GetMapping("/books/{id}")
    public String viewBook(Model model, @PathVariable Long id) {
//        model.addAttribute("book", getABook()); //give one book to thymeleaf
        return "book";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam String author, @RequestParam String title,
            @RequestParam String description, @RequestParam String ISBN) {
        if (author.trim().isEmpty() || title.trim().isEmpty()) {
            return "redirect:/fail";
        }
// to do: save book to database
        return "redirect:/books"; // tai kirjan sivulle tai johonkin muualle?
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("message", "fail"); // jos haluaa viestin täältä... 
        return "fail";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(Model model, @PathVariable Long id) {
        // to do: delete book from database
        model.addAttribute("message", "success"); //tms viesti
        return "redirect:/books";
    }
}
