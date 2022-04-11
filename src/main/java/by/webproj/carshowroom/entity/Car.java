package by.webproj.carshowroom.entity;

import java.sql.Blob;
import java.util.Objects;

public class Car {
    private final long userId;
    private final String carName;
    private final String carDescription;
    private final Blob carImage;

    private Car(Builder builder) {
        userId = builder.userId;
        carName = builder.carName;
        carDescription = builder.carDescription;
        carImage = builder.carImage;
    }

    public long getUserId() {
        return userId;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public Blob getCarImage() {
        return carImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (userId != car.userId) return false;
        if (!Objects.equals(carName, car.carName)) return false;
        if (!Objects.equals(carDescription, car.carDescription))
            return false;
        return Objects.equals(carImage, car.carImage);
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (carName != null ? carName.hashCode() : 0);
        result = 31 * result + (carDescription != null ? carDescription.hashCode() : 0);
        result = 31 * result + (carImage != null ? carImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "userId=" + userId +
                ", carName='" + carName + '\'' +
                ", carDescription='" + carDescription + '\'' +
                ", carImage=" + carImage +
                '}';
    }

    public static class Builder {
        private long userId;
        private String carName;
        private String carDescription;
        private Blob carImage;

        public Builder withUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withCarName(String carName) {
            this.carName = carName;
            return this;
        }

        public Builder withCarDescription(String carDescription) {
            this.carDescription = carDescription;
            return this;
        }

        public Builder withCarImage(Blob carImage) {
            this.carImage = carImage;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
