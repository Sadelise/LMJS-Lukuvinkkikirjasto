/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkkikirjasto.domain;

import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.dao.TipDao;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class YouTubeVideoTest {

    @Test
    public void bookHasTitleAndAuthor() {
        Book book = new Book("A book about books", "Billy Book");
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
    }

    @Test
    public void bookHasAllInformation() {
        Book book = new Book("A book about books", "Billy Book", "This book has a lot of information about books", "tag", "ISBN1234");
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
        assertEquals("This book has a lot of information about books", book.getDescription());
        assertEquals("tag", book.getTagString());
        assertEquals("ISBN1234", book.getISBN());
    }

    @Test
    public void videoCanBeMarkedAsReadAndNotRead() {
        YouTubeVideo video = new YouTubeVideo("Title", "Url", "Uploader", "Description", "Ref", "Tag");
        assertFalse(video.isRead());
        video.markRead();
        assertTrue(video.isRead());
        video.markRead();
        assertTrue(video.isRead());
        video.markNotRead();
        assertFalse(video.isRead());
    }

    @Test
    public void containsWorksForAllIntendedStrings() {
        YouTubeVideo video = new YouTubeVideo("Title", "Url", "Uploader", "Description", "Ref", "Tag");

        assertTrue(video.contains("Title"));
        assertTrue(video.contains("Url"));
        assertTrue(video.contains("Uploader"));
        assertTrue(video.contains("Description"));
        assertTrue(video.contains("Ref"));
        assertTrue(video.contains("Tag"));
    }

    @Test
    public void lowerCaseIsRecognisedByContainsMethod() {
        YouTubeVideo video = new YouTubeVideo("Title", "Url", "Uploader", "Description", "Ref", "Tag");

        assertTrue(video.contains("tiTle"));
        assertTrue(video.contains("uRl"));
        assertTrue(video.contains("uPloader"));
        assertTrue(video.contains("dEscription"));
        assertTrue(video.contains("rEf"));
        assertTrue(video.contains("tAg"));
    }

}
