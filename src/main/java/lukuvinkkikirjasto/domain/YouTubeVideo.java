
package lukuvinkkikirjasto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class YouTubeVideo implements Tip {

    private String url;
    private String title;
    private String uploader;
    private String description;
    private boolean watched;

    public YouTubeVideo(String url, String title) {
        this(title, url, "", "", false);
    }

    @Override
    public boolean markRead() {
        if (this.watched)
            return false;
        this.watched = true;
        return true;
    }

    @Override
    public boolean markNotRead() {
        if (!this.watched)
            return false;
        this.watched = false;
        return true;
    }

    @Override
    public boolean isRead() {
        return watched;
    }

    @Override
    public String identify() {
        return title;
    }

    @Override
    public boolean edit(String element, String edit) {
        switch (element) {
            case "title":
                changeTitle(edit);
                break;
            case "url":
                changeUrl(edit);
                break;
            case "description":
                changeDescription(edit);
                break;
            case "uploader":
                changeUploader(edit);
                break;
            default:
                return false;
        }
        return true;
    }

    private void changeTitle(String edit) {
        this.title = edit;
    }

    private void changeUrl(String edit) {
        this.url = edit;
    }

    private void changeDescription(String edit) {
        this.description = edit;
    }

    private void changeUploader(String edit) {
        this.uploader = edit;
    }


}
