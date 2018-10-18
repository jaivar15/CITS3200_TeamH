/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.swing;

/**
 *
 * @author varunjain
 */
import java.util.HashMap;
import java.util.HashSet;

public class responder {
    private HashMap<String,HashSet<String>> AnimalNameToEmails;
    private HashMap<String,String> emailToUserName;
    private HashSet<String> animalName;
    private HashMap<String, HashSet<String> > emailsToAnimalName;
    private int user;

    public responder(){
        AnimalNameToEmails = new HashMap<>();
        emailToUserName = new HashMap<>();
        emailsToAnimalName = new HashMap<>();
        animalName = new HashSet<>();
        user = 0;
    }

    public responder(Object[] o){
        user = 0;
        AnimalNameToEmails = new HashMap<>();
        emailToUserName = new HashMap<>();
        emailsToAnimalName = new HashMap<>();
        animalName = new HashSet<>();
        recover(o);
    }

    public void addResponder(String AnimalName, String address){
        if(emailToUserName.containsKey(address) && animalName.contains(AnimalName)){
            if( !AnimalNameToEmails.containsKey(AnimalName)){
                AnimalNameToEmails.put(AnimalName,new HashSet<>());
                AnimalNameToEmails.get(AnimalName).add(address);
            }else{
                if(!AnimalNameToEmails.get(AnimalName).contains(address)){
                    AnimalNameToEmails.get(AnimalName).add(address);
                }
            }

            if(!emailsToAnimalName.containsKey(address)){
                emailsToAnimalName.put(address,new HashSet<>());
                emailsToAnimalName.get(address).add(AnimalName);
            }else{
                if(!emailsToAnimalName.get(address).contains(AnimalName)){
                    emailsToAnimalName.get(address).add(AnimalName);
                }
            }
        }else{
            //TODO show that email or animal does not exist
        }
    }
    
    public void removeAnimalFromResponder(String AnimalName,String address){

        if(AnimalNameToEmails.containsKey(AnimalName)){
            if(AnimalNameToEmails.get(AnimalName).contains(address)){
                AnimalNameToEmails.get(AnimalName).remove(address);
            }
        }
        if(emailsToAnimalName.containsKey(address)) {
            if (emailsToAnimalName.get(address).contains(AnimalName)) {
                emailsToAnimalName.get(address).remove(AnimalName);
            }
        }
    }

    public void removeAnimal(String AnimalName){
        if(animalName.contains(AnimalName)){
            animalName.remove(AnimalName);
            if(AnimalNameToEmails.containsKey(AnimalName)) {
                for (String s : emailsToAnimalName.keySet()) {
                    emailsToAnimalName.get(s).remove(AnimalName);
                }
                AnimalNameToEmails.remove(AnimalName);
            }
        }
        System.out.println(toString());
    }

    public void newAnimal(String Name){
        if(!animalName.contains(Name)){
            animalName.add(Name);
        }
    }

    public boolean newUser(String name, String email){
        if(!emailToUserName.containsKey(email)){
            emailToUserName.put(email,name);
            user++;
            return true;
        }
        return false;
    }

    public void removeEmail(String email){
        if(emailsToAnimalName.containsKey(email) && emailToUserName.containsKey(email)){
            for(String s : emailsToAnimalName.get(email)){
                AnimalNameToEmails.get(s).remove(email);
            }
            emailsToAnimalName.remove(email);
            emailToUserName.remove(email);
        }
    }

    public final void recover(Object[] o){
        AnimalNameToEmails = (HashMap<String,HashSet<String>>)o[0];
        emailToUserName = (HashMap<String,String>)o[1];
        animalName = (HashSet<String>)o[2];
        emailsToAnimalName = (HashMap<String, HashSet<String> >)o[3];
        user = emailToUserName.size();
    }

    public void backUp(){
        Object[] data = {AnimalNameToEmails,emailToUserName,animalName,emailsToAnimalName};
        serialization ser = new serialization(data, "" ,"responder");
        ser.SerializeObject();
    }

    public Object[][] UserInfoOutPut(){

        Object[][] out = new Object[user][3];
        int i = 0;
        for(String s : emailToUserName.keySet()){
            out[i][0] = emailToUserName.get(s);
            out[i][1] = s;
            if(emailsToAnimalName.containsKey(s)) {
                out[i][2] = emailsToAnimalName.get(s).toString();
            }
            i++;
        }
        return out;
    }

    public Object[] readBackUpFile(String path){
        deserialization d = new deserialization(path);
        if((d.deserializeObject()) == false){
            backUp();
            d.deserializeObject();
        }
        Object[] o = d.getData();
        return o;
    }
    
    public String emailsToAnimalName(String email){
        return emailsToAnimalName.get(email).toString()!=null ? emailsToAnimalName.get(email).toString() : null;
    }
    
    public String[] getEmail(String animalName) {
    	return (String[])AnimalNameToEmails.get(animalName).toArray();
    }
    
    public boolean[] trueOrFalse(String email){
        int size  = animalName.size();
        int i = 0;
        boolean[] det = new boolean[size];
        for(String s : animalName){
            det[i]=false;
            if(emailsToAnimalName.containsKey(email)) {
                if (emailsToAnimalName.get(email).contains(s)) {
                    det[i] = true;
                }
            }
            i++;
        }
        return det;
    }
    
    public Object[][] getAnimals(){
        int size  = animalName.size();
        Object det[][] = new Object[size][2];
        int i = 0;
        for(String s: animalName){
            det[i][0] = s;
            det[i][1] = false;
            i++;
        }
        return det;
    }

    public String toString(){
        String s = "Animals name to Emails\n";
        for(String i : AnimalNameToEmails.keySet()){
            s+= "" + i + "  " + AnimalNameToEmails.get(i).toString() + "\n";
        }
        s+= "\nemail to name \n";

        for(String ss :  emailToUserName.keySet()){
            s+= ss + "  " +emailToUserName.get(ss) + "\n";
        }
        s+= "\nanimal id to name\n";

        for(String i : animalName){
            s+="" + i +"  " + "\n";
        }
        s+="\nemails to names\n";

        for(String ss : emailsToAnimalName.keySet()){
            s+= "\n"+ss+"  " +emailsToAnimalName.get(ss).toString();
        }

        return s;
    }
}