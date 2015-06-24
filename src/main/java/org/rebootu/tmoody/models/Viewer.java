package org.rebootu.tmoody.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by taylor on 6/15/15.
 */
@Entity
@Table(name = "viewers")
public class Viewer extends AbstractEntity {

    private String viewerName;
    private int points;

    public Viewer(String viewername) {
        this.viewerName = viewerName;
        this.points = 1;
    }

    public Viewer() {}

    @Column(name = "points")
    public int getPoints() {return points;}
    public void setPoints(int points) {this.points = points;}


    @NotNull
    @Column(name = "viewer_name", unique = true, nullable = false)
    public String getViewerName(){return viewerName;}

    public void setViewerName(String viewerName){this.viewerName = viewerName;}

     public void addPoint (){
        this.points++;
    }

}

