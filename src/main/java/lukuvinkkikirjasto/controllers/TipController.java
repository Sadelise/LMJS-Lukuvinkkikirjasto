package lukuvinkkikirjasto.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
import lukuvinkkikirjasto.domain.YouTubeVideo;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TipController {

    TipDao tipDao = new FireBaseTipDao();
    //TipDao tipDao = new lukuvinkkikirjasto.dao.BasicTipDao();
    
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
        List<Tip> allTips = tipDao.getAllTips();
        List<Tip> read = new ArrayList<>();
        List<Tip> notRead = new ArrayList<>();
        for (Tip tip : allTips) {
            if (tip.isRead()) {
                read.add(tip);
            } else {
                notRead.add(tip);
            }
        }
        model.addAttribute("readTips", read);
        model.addAttribute("notReadTips", notRead);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String viewBook(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        Tip tip = tipDao.getTip(id);
        if (tip != null) {
            if (tip.getType().equals("Book")) {
                model.addAttribute("book", tip);
                return "book";
            } else {
                model.addAttribute("video", tip);
                return "redirect:/videos/{id}";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Jokin meni vikaan... :(");
        return "redirect:/fail";
    }

    @GetMapping("/videos/{id}")
    public String viewVideo(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        Tip tip = tipDao.getTip(id);
        if (tip != null) {
            model.addAttribute("video", tip);
            return "video";
        }
        redirectAttributes.addFlashAttribute("message", "Jokin meni vikaan... :(");
        return "redirect:/fail";
    }

    @PostMapping("/books/{id}")
    public String editBook(Model model, RedirectAttributes redirectAttributes,
            @PathVariable String id, @RequestParam String author, @RequestParam String title,
            String description, String ISBN, String tagString, int priority, boolean read) {
        if (author.trim().isEmpty() || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Kirjan muokkaaminen epäonnistui. Nimi ja tekijä eivät voi olla tyhjiä.");
            return "redirect:/books";
        }
        tipDao.editTipByTitle(title, "title", id);
        tipDao.editTipByTitle(title, "author", author);
        tipDao.editTipByTitle(title, "description", description);
        tipDao.editTipByTitle(title, "isbn", ISBN);
        tipDao.editTipByTitle(title, "tags", tagString);
        tipDao.setPriority(title, priority);
        if (tipDao.getTip(title).isRead() != read) {
            tipDao.markTip(id);
        }

        redirectAttributes.addFlashAttribute("message", "Kirjan muokkaaminen onnistui!");
        return "redirect:/books";
    }

    @PostMapping("/videos/{id}")
    public String editVideo(Model model, RedirectAttributes redirectAttributes,
            @PathVariable String id, @RequestParam String title, @RequestParam String url,
            String description, String uploader, String tagString, int priority, boolean watched) {
        if (title.trim().isEmpty() || url.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Videon muokkaaminen epäonnistui. Nimi ja linkki eivät voi olla tyhjiä.");
            return "redirect:/books";
        }
        tipDao.editTipByTitle(title, "title", id);
        tipDao.editTipByTitle(title, "url", url);
        tipDao.editTipByTitle(title, "description", description);
        tipDao.editTipByTitle(title, "uploader", uploader);
        tipDao.editTipByTitle(title, "tags", tagString);
        tipDao.setPriority(title, priority);
        if (tipDao.getTip(title).isRead() != watched) {
            tipDao.markTip(id);
        }
        redirectAttributes.addFlashAttribute("message", "Videon muokkaaminen onnistui!");
        return "redirect:/books";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam String author, @RequestParam String title,
            String description, String ISBN, String tags, RedirectAttributes redirectAttributes) {
        if (author.trim().isEmpty() || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Kirjan lisääminen epäonnistui. Nimi ja tekijä ovat pakollisia kenttiä.");
            return "redirect:/books";
        }
        tipDao.addTip(new Book(title, author, description, tags, ISBN));
        redirectAttributes.addFlashAttribute("message", "Kirjan lisääminen onnistui!");
        return "redirect:/books";
    }

    @PostMapping("/videos")
    public String addVideo(@RequestParam String title, @RequestParam String url,
            String description, String uploader, String tags, RedirectAttributes redirectAttributes) {
        if (url.trim().isEmpty() || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Videon lisääminen epäonnistui. Nimi ja linkki ovat pakollisia kenttiä.");
            return "redirect:/books";
        }
        tipDao.addTip(new YouTubeVideo(title, url, uploader, description, tags));
        redirectAttributes.addFlashAttribute("message", "Videon lisääminen onnistui!");
        return "redirect:/books";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        return "fail";
    }

    @DeleteMapping("/books/{id}")
    public String deleteTip(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        if (tipDao.getTip(id) != null) {
            tipDao.removeTip(id);
            redirectAttributes.addFlashAttribute("message", "Vinkin poistaminen onnistui!");
            return "redirect:/books";
        }
        redirectAttributes.addFlashAttribute("message", "Vinkin poistaminen epäonnistui!");
        return "redirect:/books";
    }

    @PostMapping("/search")
    public String searchTips(Model model, @RequestParam String keyword) {
        List<Tip> results = tipDao.searchByKeyword(keyword.toLowerCase().trim());
        List<Tip> read = new ArrayList<>();
        List<Tip> notRead = new ArrayList<>();
        if(results.isEmpty()) {
            model.addAttribute("message", "Valitettavasti hakuehdoillasi ei löytynyt mitään.");
        }
        for (Tip tip : results) {
            if (tip.isRead()) {
                read.add(tip);
            } else {
                notRead.add(tip);
            }
        }
        model.addAttribute("readTips", read);
        model.addAttribute("notReadTips", notRead);
        return "books";
    }


    @PostMapping("/sort")
    public String sortTips(Model model){
        List<Tip> read = new ArrayList<>();
        List<Tip> notRead = new ArrayList<>();
        List<Tip> allTips = tipDao.getAllTips();
        for (Tip tip : allTips) {
            if (tip.isRead()) {
                read.add(tip);
            } else {
                notRead.add(tip);
            }
        }
        TipComparator comparator = new TipComparator();
        read.sort(comparator);
        notRead.sort(comparator);
        model.addAttribute("readTips", read);
        model.addAttribute("notReadTips", notRead);
        return "books";
    }



}


