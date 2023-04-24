package by.webproj.carshowroom.entity;

import lombok.EqualsAndHashCode;

import lombok.ToString;



@EqualsAndHashCode
@ToString
public class Image {
    private final long id;
    private final User user;

    private Image(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public static class Builder {
        private long id;
        private User user;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Image build() {
            return new Image(this);
        }
    }
}
