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
    private String reference;
    private boolean watched;
    //private boolean urlpresent;

    public YouTubeVideo(TipData tipData) {
        this.title = tipData.getTitle();
        this.uploader = tipData.getCreator();
        this.description = tipData.getDescription();
        this.url = tipData.url;
        this.tagString = tipData.getTagString();
        //addTags(tagString);
        this.type = tipData.getType();
        this.watched = tipData.read;
    }

    public YouTubeVideo(String title, String url) {
<<<<<<< 47b2f8d34d4a4a76de640853862ad708a7b5568c
        this(title, url, "", "", "", new String[0], "YouTubeVideo", false);
    }

    public YouTubeVideo(String title, String url, String uploader, String description, String tag) {
        this(title, url, uploader, description, tag, new String[0], "YouTubeVideo", false);
=======
        this(title, url, "", "", "", new String[0], "YouTubeVideo", "", false, true);
    }

    public YouTubeVideo(String title, String url, String uploader, String description, String tag) {
        this(title, url, uploader, description, tag, new String[0], "YouTubeVideo", "", false, true);
>>>>>>> suosittelijan luominen vinkeille
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
            case "tags":
                addTags(edit);
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
    
    private void changeReference(String ref) {
        this.reference = ref;
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
        if (this.tagString != null) {
            if (this.tagString.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private void addTags(String tags) {
        this.tagString = tags;
        /*if (tags != null) {
            this.tags = tags.split(";(\\s)*");
        } else {
            this.tags = new String[0];
        }*/
    }

    public String[] getTags() {
        if (tagString.length() > 0) {
            return tagString.split(";(\\s)*");
        } else {
            return null;
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public boolean getUrlpresent() {
        return true;
    }
<<<<<<< 47b2f8d34d4a4a76de640853862ad708a7b5568c
=======

    @Override
    public String getReference() {
        return this.reference;
    }
    
    
>>>>>>> suosittelijan luominen vinkeille
}
