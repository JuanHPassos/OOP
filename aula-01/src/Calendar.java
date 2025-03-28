package Calendar;

class Calendar {
  private Person[] people;
  private int totalPeople;

  public Calendar(){
    this.people = new Person[100];
    // Position to insert people
    this.totalPeople = 0;
  }

  public Person findPersonByName(String name){
    for(Person person: this.people){
      if(person.getName().equals(name))
        return person;
    }
    return null;
  }

  public Person findPersonByPosition(int pos){
    return this.people[pos];
  }

  // Returns false if maximum people(100) are reached
  public boolean addPerson(Person person){
    if(this.totalPeople < 100) {
      this.people[this.totalPeople] = person;
      this.totalPeople++;
      return true;
    }
    return false;
  }

  public Person removePersonByName(String name){
    Person person = null;
    for(int i = 0; i < this.totalPeople; i++) {
      if (this.people[i].getName().equals(name)) {
        person = this.people[i];
        // position to remove person from the end of array
        int positionFree = Math.max(this.totalPeople - 1, 0);
        // Change the last with the removed
        this.people[i] = this.people[positionFree];
        // Free the position
        this.people[positionFree] = null;
        // Att counter
        this.totalPeople--;
        return person;
      }
    }
    return person;
  }

  public void data(){
    for(int i = 0; i < this.totalPeople; i++){
      System.out.println("Person in position " + i);
      people[i].data();
    }
  }

}