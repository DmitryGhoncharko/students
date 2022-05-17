package by.webproj.carshowroom.entity;

import java.util.Objects;

public class Favorites {
    private final long id;
    private final User user;
    private final Car car;

    private Favorites(Builder builder) {
        id = builder.id;
        user = builder.user;
        car = builder.car;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return id == favorites.id &&
                Objects.equals(user, favorites.user) &&
                Objects.equals(car, favorites.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, car);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                '}';
    }

    public static class Builder {
        private long id;
        private User user;
        private Car car;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withCar(Car car) {
            this.car = car;
            return this;
        }

        public Favorites build() {
            return new Favorites(this);
        }
    }
}
