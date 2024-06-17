package cucumber;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService{

    private List<Person> personList = new ArrayList<>();

    @Override
    public List<Person> init() {
        personList.add(new Person(1,"Tharika", "K", 22));
        personList.add(new Person(2,"Vicka", "Srinivasa", 28));
        personList.add(new Person(3,"Tanu", "K", 16));

        return personList;
    }

    public Person save(Person person){
        if(person == null){
            return person;
        }
        person.setId(personList.size()+1);
        personList.add(person);
        return person;
    }

    public List<Person> findAll(){
        return personList;
    }

    public int findPersonIndex(int id){
        for(int i = 0;i < personList.size();i++){
            if (personList.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    public void delete(int id){
        int personIndex = findPersonIndex(id);
        personList.remove(personIndex);
    }

}
