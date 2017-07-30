package com.handiy.handiy.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FitriFebriana on 7/29/2017.
 */

public class CreationModel {
    private String creation_id;
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

    public String getCreation_id() {
        return creation_id;
    }

    public void setCreation_id(String creation_id) {
        this.creation_id = creation_id;
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

    public class CreationListModel extends BaseModel {
        @SerializedName("data")
        private List<CreationModel> result;

        public List<CreationModel> getResult() {
            return result;
        }

        public void setResult(List<CreationModel> result) {
            this.result = result;
        }

    }
}
