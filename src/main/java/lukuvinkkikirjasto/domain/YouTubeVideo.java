package lukuvinkkikirjasto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YouTubeVideo implements Tip {

    private String title;
    private String url;
    private String uploader;
    private String description;
    private String tagString;
    private String[] tags;
    private String type;
    private boolean watched;

    public YouTubeVideo(String title, String url) {
        this(title, url, "", "", "", new String[0], "YouTubeVideo", false);
    }
    
    public YouTubeVideo(String title, String url, String uploader, String description, String tag) {
        this(title, url, uploader, description,  tag, new String[0], "YouTubeVideo", false);
    }

    @Override
    public boolean markRead() {
        if (this.watched) {
            return false;
        }
        this.watched = true;
        return true;
    }

    @Override
    public boolean markNotRead() {
        if (!this.watched) {
            return false;
        }
        this.watched = false;
        return true;
    }

    @Override
    public boolean isRead() {
        return watched;
    }
        
    public void setRead(boolean watched) {
        this.watched = watched;
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

    @Override
    public boolean contains(String keyword) {
        keyword = keyword.toLowerCase().trim();
        if (this.uploader != null) {
            if (this.uploader.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        if (this.url != null) {
            if (this.url.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        if (this.description != null) {
            if (this.description.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        if (this.title != null) {
            if (this.title.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
    
    private void addTags(String tags) {
        this.tagString = tags;
        if (tags != null) {
            this.tags = tags.split(";(\\s)*");
        } else {
            this.tags = new String[0];
        }
    }
    
    @Override
    public String getType() {
        return this.type;
    }
}
