package ch.fhnw.webec.coffeecatalogue.model;

import ch.fhnw.webec.coffeecatalogue.form.SelectOption;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Roasting implements SelectOption {
    @Id
    private long id;
    private String name;

    public Roasting() {}

    public Roasting(long id, String name) {
        this();
        this.name= name;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String getValue() { return String.valueOf(this.getId()); }

    @Override
    public String getLabel() { return this.getName(); }
}


