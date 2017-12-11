package lukuvinkkikirjasto.controllers;

import lukuvinkkikirjasto.domain.Tip;

import java.util.Comparator;

public class TipComparator implements Comparator<Tip> {
    @Override
    public int compare(Tip tip, Tip t1) {
        return tip.getPriority()-t1.getPriority();
    }
}
