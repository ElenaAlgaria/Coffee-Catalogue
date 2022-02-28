package ch.fhnw.webec.coffeecatalogue.model;

import ch.fhnw.webec.coffeecatalogue.form.SelectOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.URL;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Roaster implements SelectOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "please enter a name")
    @Column(nullable = false)
    private String name;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @NotEmpty(message = "please enter a description")
    @Column(columnDefinition = "TEXT")
    private String description;

    @URL(message = "please enter a valid URL")
    @Column(name = "url")
    private String roasterUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roaster")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Bean> beansList = new HashSet<>();

    public Roaster() {}

    public Roaster(String name, String imagePath, String description, String roasterUrl) {
        this();
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.roasterUrl = roasterUrl;
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

    public String getRoasterUrl() {
        return roasterUrl;
    }

    public void setRoasterUrl(String roasterUrl) {
        this.roasterUrl = roasterUrl;
    }

    public Set<Bean> getBeansList() {
        return beansList;
    }

    public void setBeansList(Set<Bean> beansList) {
        this.beansList = beansList;
    }

    public String getImage_path() {
        return imagePath;
    }

    public void setImage_path(String image_path) {
        this.imagePath = image_path;
    }

    @Override
    public String getValue() {
        return String.valueOf(this.getId());
    }

    @Override
    public String getLabel() {
        return this.getName();
    }
}
