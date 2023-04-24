package by.webproj.carshowroom.entity;

import java.util.Objects;

public class CarRepair {
    private final Long id;
    private final String name;
    private final String description;
    private final User user;
    private final boolean complete;

    private final double cost;

    private CarRepair(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        user = builder.user;
        complete = builder.complete;
        cost = builder.cost;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private User user;
        private boolean complete;

        private double cost;
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withComplete(boolean complete) {
            this.complete = complete;
            return this;
        }
        public Builder withCost(double cost){
            this.cost = cost;
            return this;
        }
        public CarRepair build() {
            return new CarRepair(this);
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

    public boolean isComplete() {
        return complete;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarRepair carRepair = (CarRepair) o;

        if (complete != carRepair.complete) return false;
        if (Double.compare(carRepair.cost, cost) != 0) return false;
        if (!Objects.equals(id, carRepair.id)) return false;
        if (!Objects.equals(name, carRepair.name)) return false;
        if (!Objects.equals(description, carRepair.description))
            return false;
        return Objects.equals(user, carRepair.user);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (complete ? 1 : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CarRepair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", complete=" + complete +
                ", cost=" + cost +
                '}';
    }
}
