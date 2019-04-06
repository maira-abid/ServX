package pl.servx.servx.Model;

import java.util.List;
import java.util.Vector;

public class User {
    private String Name;
    private String Password;
    List<vehicle> vehicles;

    public User(){
     Name= "";
     Password="";
     vehicles =  new Vector<vehicle>();

    }
    public User(String name, String pass){
        Name = name;
        Password = pass;
        vehicles =  new Vector<vehicle>();
        vehicle vehi= new vehicle();
        vehi.vmake="";
        vehi.vmodel="";
        vehi.vyear="";
        vehicles.add(vehi);

    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
