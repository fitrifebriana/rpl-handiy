package com.handiy.handiy.data.dummy;

import com.handiy.handiy.R;
import com.handiy.handiy.data.TutorialModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FitriFebriana on 4/14/2017.
 */

public class WearData {
    private static final String[] tutorials = {"Upcycled Memo Board and Chigiri-e Ribbon Keepsake",
            "DIY Mini Tissue Paper Flowers Bouquet",
            "Easy Circle Punch Paper Flowers on Branches",
            "How to Make Paper Four Leaf Clovers",
            "DIY Curled Hyacinth Paper Flowers",
            "DIY Four Leaf Clover Paper Art"};
    private static final int[] img = {R.drawable.dummy_img4, R.drawable.dummy_img4,
            R.drawable.dummy_img4,R.drawable.dummy_img4,R.drawable.dummy_img4,R.drawable.dummy_img4};


    public static List<TutorialModel> getListTutorial(){
        List<TutorialModel> listTutorial = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < tutorials.length; i++) {
                TutorialModel tm = new TutorialModel();
                //tm.setImgId(img[i]);
                tm.setTitle(tutorials[i]);
                listTutorial.add(tm);
            }
        }
        return listTutorial;
    }
}
