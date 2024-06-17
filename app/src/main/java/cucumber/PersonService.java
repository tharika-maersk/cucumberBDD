package cucumber;

import java.util.List;

public interface PersonService {
    List<Person> init();
    Person save(Person person);
    List<Person> findAll();

    void delete(int id);
}
