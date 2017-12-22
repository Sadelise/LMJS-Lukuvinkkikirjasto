package lukuvinkkikirjasto;

import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.dao.FireBaseTipDao;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.dao.TipDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    private TipDao tipDao;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
