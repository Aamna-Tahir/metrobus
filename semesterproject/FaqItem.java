package com.example.semesterproject;

public class FaqItem {
    private String question;
    private String answer;
  //  private String apilevel;
    //  private String description;
    private boolean isExpandable;

    public FaqItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
      //  this.apilevel = apilevel;
      //  this.description = description;
        this.isExpandable = false;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

 /*   public String getApilevel() {
        return apilevel;
    }

    public String getDescription() {
        return description;
    }
*/
    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
