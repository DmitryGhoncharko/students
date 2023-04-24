package by.webproj.carshowroom.entity;

import java.util.Objects;

public class Request {
    private final Long id;
    private final String name;
    private final String description;
    private final User user;

    private Request(Builder builder){
        id = builder.id;
        name= builder.name;
        description = builder.description;
        user= builder.user;
    }
    public static class Builder{
        private  Long id;
        private  String name;
        private  String description;
        private  User user;
        public Builder withId(long id){
            this.id = id;
            return this;
        }
        public Builder withName(String name){
            this.name= name;
            return this;
        }
        public Builder withDescription(String description){
            this.description = description;
            return this;
        }
        public Builder withUser(User user){
            this.user= user;
            return this;
        }
        public Request build(){
            return new Request(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!Objects.equals(id, request.id)) return false;
        if (!Objects.equals(name, request.name)) return false;
        if (!Objects.equals(description, request.description)) return false;
        return Objects.equals(user, request.user);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}

