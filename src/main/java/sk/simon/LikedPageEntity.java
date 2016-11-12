package sk.simon;

import javax.persistence.*;


/**
 * Created by Simon on 11.11.2016.
 */

@Entity
@Table(name = "LIKED_PAGE")
public class LikedPageEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String fbId;
    private String name;
    private String description;

    @ManyToOne
    private FbUserEntity fbUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikedPageEntity)) return false;

        LikedPageEntity that = (LikedPageEntity) o;

        return getFbId() != null ? getFbId().equals(that.getFbId()) : that.getFbId() == null;

    }

    @Override
    public int hashCode() {
        return getFbId() != null ? getFbId().hashCode() : 0;
    }
}

