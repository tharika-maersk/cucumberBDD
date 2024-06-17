package stepDefinitions;

import cucumber.PersonService;
import cucumber.PersonServiceImpl;
import cucumber.Person;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class PersonFeatureSteps {
    private PersonService personService = new PersonServiceImpl();
    private List<Person> personList;
    private int personIntSize = 0;
    private int personSizeAfterSave = 0;
    private Person savedPerson;

    @Given("I have a static method which initialize a list of persons")
    public void iHaveAStaticMethodWhichInitializeAListOfPersons(){
        initPersonList();
    }

    @When("I connect I can see the size of the initialized list")
    public void iConnectICanSeeTheSizeOfTheInitializedList(){
        personIntSize = personList.size();
    }

    @Then("I should see the size of the list is 3")
    public void iShouldSeeTheSizeOfTheListIs3(){
        Assert.assertEquals(3, personList.size());
    }

    //Implementing create new person scenario
    @Given("The List of persons contains 3 persons already stored")
    public void givenTheListContains3Persons(){
        initPersonList();
        personIntSize = personList.size();
    }
    @When("I create a new person with random entries")
    public void iCreateNewPersonWithRandomEntries(){
        Person person = new Person();
        person.setId(4);
        person.setFirstName("Varuna");
        person.setLastName("s");
        person.setAge(22);
        savedPerson = personService.save(person);
        findAllPersons();
        personSizeAfterSave = personList.size();

    }

    @Then("I get the ID of the new person and the list contains more than three persons")
    public void listContainsMoreThanThreePerson(){
        Assert.assertEquals(4,personSizeAfterSave);
        Assert.assertNotNull(savedPerson);
    }

    // Bulk Creation
    @When("I create a new Person with {string} and {string} and {int}")
    public void whenICreateANewPersonWithData(String firstName , String lastName, Integer age){
        System.out.println(age);
        Person newPerson = new Person(firstName, lastName, age);
        personService.save(newPerson);
    }
    @Then("I get the ID of the new Person and list contains more than 3 persons")
    public void thenIGetDataSaved(){
        findAllPersons();
        Assert.assertTrue(personList.size()>3);
    }

  @When("I update a person data with {int} and {string} and {string} and {int}")
  public void updateBulk(int id, String firstName, String lastName, int age){
        Person updatePerson = new Person(id, firstName, lastName, age);
        savedPerson = personService.save(updatePerson);
  }
  @Then("I get the person updated")
  public void updatedPerson(){
    Assert.assertNotNull(savedPerson);
  }
  @When("I delete a person with ID {int}")
  public void deletePerson(int id){
    personService.delete(id);
  }

  @Then("The given person is deleted and the list size is {int}")
  public void afterDelete(int size){
        findAllPersons();
        Assert.assertEquals(personList.size(), size);
  }
  private void initPersonList() {
        personList = this.personService.init();
    }
    private void findAllPersons(){
        personList = this.personService.findAll();
    }
}
