package com.handiy.handiy.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FitriFebriana on 4/11/2017.
 */

public class TutorialModel {
    private String id;
    private String title;
    private String category_id;
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

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

    public class TutorialListModel extends BaseModel {
        private int id;
        private int tutorial_id;
        private String tutorial;
        private String photo;

        @SerializedName("data")
        private List<TutorialModel> result;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTutorial_id() {
            return tutorial_id;
        }

        public void setTutorial_id(int tutorial_id) {
            this.tutorial_id = tutorial_id;
        }

        public String getTutorial() {
            return tutorial;
        }

        public void setTutorial(String tutorial) {
            this.tutorial = tutorial;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public List<TutorialModel> getResult() {
            return result;
        }

        public void setResult(List<TutorialModel> result) {
            this.result = result;
        }

        public class StepsListModel extends BaseModel {

            private List<TutorialModel.TutorialListModel> steps;

            public List<TutorialListModel> getSteps() {
                return steps;
            }

            public void setSteps(List<TutorialListModel> steps) {
                this.steps = steps;
            }
        }
    }
}
