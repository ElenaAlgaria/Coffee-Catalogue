package ch.fhnw.webec.coffeecatalogue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message =  "not valid input - please enter the name")
    @Column(nullable = false)
    private String name;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column( nullable = false, name = "favorite", columnDefinition = "boolean default false")
    private boolean isFavorite;

    @NotNull( message = "please choose a roaster")
    @ManyToOne
    private Roaster roaster;

    @NotEmpty(message = "at least one has to be chosen")
    @ManyToMany
    private Set<Roasting> roastings = new HashSet<>();

    public Bean(){}

    public Bean(String name, String imagePath, String description, Roaster roaster) {
        this();
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.roaster = roaster;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavorite() { return isFavorite; }

    public void setFavorite(Boolean favorite) { this.isFavorite = favorite; }

    public Roaster getRoaster() {
        return roaster;
    }

    public void setRoaster(Roaster roaster) {
        this.roaster = roaster;
    }

    public Set<Roasting> getRoastings() {
        return roastings;
    }

    public void setRoastings(Set<Roasting> roastings) {
        this.roastings = roastings;
    }
}
