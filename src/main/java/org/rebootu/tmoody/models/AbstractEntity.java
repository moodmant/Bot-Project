package org.rebootu.tmoody.models;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by taylor on 6/15/15.
 */
@MappedSuperclass
public abstract class AbstractEntity {

    private int id;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public int getId(){return id;}

    protected void setId(int id) {this.id = id;}

}
