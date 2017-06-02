package ai.usher.model;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import ai.usher.utils.Utils;

@Entity
@Cache
public class Account extends ModelObject {
    private static final long serialVersionUID = -8548975941959059024L;

    @Id
    Long id;
    @Index
    String email;
    @Index
    String phone;
    long joined;
    String name;
    String picture;

    public Account() {
    }

    public Account(String email, String phone, long joined, String name, String picture) {
        super();
        this.email = email;
        this.phone = phone;
        this.joined = joined;
        this.name = name;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getJoined() {
        return joined;
    }

    public void setJoined(long joined) {
        this.joined = joined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    // methods

    public void save() {
        if (this.joined == 0) {
            this.joined = Utils.now();
        }
        ofy().save().entity(this).now();
    }

    public static Account get(long id) {
        return ofy().load().type(Account.class).id(id).now();
    }

    public static Account get(String email) {
        return ofy().load().type(Account.class).filter("email", email).first().now();
    }

    public static Account getByNumber(String phone) {
        return ofy().load().type(Account.class).filter("phone", phone).first().now();
    }
}
