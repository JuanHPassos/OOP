package Calendar;

import java.time.LocalDate;

// Principal Class
public class Main {
    public static void main(String[] args) {
        // creating an object of class people
        Calendar calendar = new Calendar();
        calendar.addPerson(new Person("Juan", LocalDate.of(2004, 8, 11), 68.8f, 1.65f));
        calendar.addPerson(new Person("Maria", LocalDate.of(1999, 5, 23), 60.5f, 1.70f));
        calendar.addPerson(new Person("Carlos", LocalDate.of(1985, 12, 1), 80.2f, 1.75f));
        calendar.addPerson(new Person("Ana", LocalDate.of(2000, 7, 15), 55.0f, 1.60f));

        calendar.data();

        calendar.removePersonByName("Maria");

        calendar.data();

        Person Juan = calendar.findPersonByName("Juan");
        Juan.data();


    }
}
