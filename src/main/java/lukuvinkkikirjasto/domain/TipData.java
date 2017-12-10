/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkkikirjasto.domain;

/**
 *
 * @author lmantyla
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TipData {

    protected String title;
    protected String creator;
    protected String description;
    protected String url;
    protected String ISBN;
    protected String tagString;
    protected String type;
    protected boolean read;
    protected int priority;

    public TipData(Tip tip) {
        if (tip.getType().equals("Book")) {
            createTipData((Book) tip);
        } else if (tip.getType().equals("YouTubeVideo")) {
            createTipData((YouTubeVideo) tip);
        }
    }

    public void createTipData(YouTubeVideo video) {
        this.title = video.getTitle();
        this.creator = video.getUploader();
        this.description = video.getDescription();
        this.url = video.getUrl();
        this.tagString = video.getTagString();
        this.type = video.getType();
        this.read = video.isWatched();
    }

    public void createTipData(Book book) {
        this.title = book.getTitle();
        this.creator = book.getAuthor();
        this.description = book.getDescription();
        this.url = book.getUrl();
        this.tagString = book.getTagString();
        this.type = book.getType();
        this.read = book.isRead();
    }
}
