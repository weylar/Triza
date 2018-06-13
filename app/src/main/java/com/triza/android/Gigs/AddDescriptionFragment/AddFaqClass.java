package com.triza.android.Gigs.AddDescriptionFragment;


import android.widget.TextView;

public class AddFaqClass {
    private String question;
    private String answer;


    AddFaqClass(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}

