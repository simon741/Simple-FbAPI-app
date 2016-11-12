package sk.simon;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Simon on 10.11.2016.
 */

@Entity
@Table(name = "FB_USER")
public class FbUserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String fbId;
    private String gender;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy="fbUser", cascade = CascadeType.ALL)
    private List<LikedPageEntity> likedPages;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FbUserEntity)) return false;

        FbUserEntity fbUser = (FbUserEntity) o;

        return getFbId() != null ? getFbId().equals(fbUser.getFbId()) : fbUser.getFbId() == null;

    }

    @Override
    public int hashCode() {
        return getFbId() != null ? getFbId().hashCode() : 0;
    }
}
