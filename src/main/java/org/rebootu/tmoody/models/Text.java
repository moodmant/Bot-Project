package org.rebootu.tmoody.models;

/**
 * Created by taylor on 6/11/15.
 */
public class Text {

    private int id;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text(String text){
        this.text = text;
    }
}
