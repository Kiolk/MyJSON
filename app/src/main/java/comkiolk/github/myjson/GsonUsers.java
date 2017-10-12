package comkiolk.github.myjson;

import java.util.ArrayList;

public class GsonUsers {

    String name;
    String gender;
    int age;
    String address;
    Long registered;
    String[] tags;
    final ArrayList<Friends> friends = new ArrayList<>();

    public void setName(final String string) {
        name = string;
    }

    public void setAge(final int pAge) {
        age = pAge;
    }

}

class Friends {

    int id;
    String name;
}