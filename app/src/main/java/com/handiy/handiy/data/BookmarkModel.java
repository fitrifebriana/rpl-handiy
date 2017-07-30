package com.handiy.handiy.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FitriFebriana on 7/28/2017.
 */

public class BookmarkModel {
    private String bookmarks_id;
    private String tutorial_id;
    private String username;
    private Tutorial tutorial;

    public class Tutorial {
        private String id;
        private String title;
        private String thumbnail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    public String getBookmark_id() {
        return bookmarks_id;
    }

    public void setBookmark_id(String bookmark_id) {
        this.bookmarks_id = bookmark_id;
    }

    public String getTutorial_id() {
        return tutorial_id;
    }

    public void setTutorial_id(String tutorial_id) {
        this.tutorial_id = tutorial_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public class BookmarkListModel extends BaseModel {
        @SerializedName("data")
        private List<BookmarkModel> result;

        public List<BookmarkModel> getResult() {
            return result;
        }

        public void setResult(List<BookmarkModel> result) {
            this.result = result;
        }

    }

}
