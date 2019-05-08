package pl.servx.servx.Model;

import java.util.List;
import java.util.Vector;

public class User {
    private String Name;
    private String Password;
    private String Email;
    List<vehicle> vehicles;

    public User(){
     Name= "";
     Password="";
     Email="";
     vehicles =  new Vector<vehicle>();

    }
    public User(String name, String pass, String email){
        Name = name;
        Password = pass;
        Email = email;
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

    public String getEmail() {
        return Email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email){
        Email = email;
    }
}
