package Calendar;

import java.time.LocalDate;
import java.time.Period;

class Person {
    // Attributes
    private final String modelName;
    private final LocalDate modelDateOfBirth;
    private float modelWeight;
    private float modelHeight;

    // Constructor
    public Person(
        String name,
        LocalDate dateOfBirth,
        float weight,
        float height
    ) {
        this.modelName = name;
        this.modelDateOfBirth = dateOfBirth;
        this.modelWeight = weight;
        this.modelHeight = height;
    }

    // Method get
    public String getName() {
        return this.modelName;
    }
    public LocalDate getDateOfBirth() {
        return this.modelDateOfBirth;
    }
    public float getWeight() {
        return this.modelWeight;
    }
    public float getHeight() {
        return this.modelHeight;
    }

    // Method set
    public void setPeso(float newWeight) { this.modelWeight = newWeight; }
    public void setHeight(float newHeight) { this.modelHeight = newHeight; }

    // Method for calculating age.
    // You need to create the object to use this method.
    public int age() {
        // get the current date
        LocalDate today = LocalDate.now();
        return Period.between(getDateOfBirth(), today).getYears();
    }

    // Method for obtaining information
    public void data() {
        System.out.println("Name: " + getName());
        System.out.println("Age: " + age());
        System.out.println("Date of Birth: " + getDateOfBirth());
        System.out.println("Weight: " + getWeight());
        System.out.println("Height: " + getHeight());
    }
}
