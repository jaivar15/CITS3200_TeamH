/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

/**
 *
 * @author varunjain
 */
import java.util.HashMap;
import java.util.HashSet;

public class responder {
    private HashMap<Integer,HashSet<String>> IDtoEmails;
    private HashMap<String,String> emailToName;
    private HashMap<Integer,String> animalIDtoName;
    private HashMap<String, HashSet<Integer> > emailsToIDs;
    private int user;

    public responder(){
        IDtoEmails = new HashMap<>();
        emailToName = new HashMap<>();
        emailsToIDs = new HashMap<>();
        animalIDtoName = new HashMap<>();
        user = 0;
    }

    public responder(Object[] o){
        user = 0;
        IDtoEmails = new HashMap<>();
        emailToName = new HashMap<>();
        emailsToIDs = new HashMap<>();
        animalIDtoName = new HashMap<>();
        recover(o);
    }

    public void addResponder(int ID, String address){
        if(emailToName.containsKey(address) && animalIDtoName.containsKey(ID)){
            if( !IDtoEmails.containsKey(ID)){
                IDtoEmails.put(ID,new HashSet<String>());
            }
            IDtoEmails.get(ID).add(address);

            if(!emailsToIDs.containsKey(address)){
                emailsToIDs.put(address,new HashSet<>());
            }
            emailsToIDs.get(address).add(ID);
        }else{
            //TODO show that email or animal does not exist
        }
    }

    public void addMutiResponder(int[] id, String address){
        for(int i = 0; i < id.length ; i++){
            addResponder(id[i], address);
        }
    }

    public void removeAnimal(int ID){
        if(IDtoEmails.containsKey(ID)){
            animalIDtoName.remove(ID);
            for( String s: IDtoEmails.get(ID)){
                emailsToIDs.get(s).remove(ID);
            }
            IDtoEmails.remove(ID);
        }
    }

    public void removeMutiAnimal(int[] ID){
        for(int i = 0; i < ID.length; i++){
            removeAnimal(ID[i]);
        }
    }

    public void newAnimal(int ID, String Name){
        if(!animalIDtoName.containsKey(ID)){
            animalIDtoName.put(ID,Name);
        }
    }

    public boolean newUser(String name, String email){
        if(!emailToName.containsKey(email)){
            emailToName.put(email,name);
            user++;
            return true;
        }
        return false;
    }

    public void removeEmail(String email){
        if(emailsToIDs.containsKey(email) && emailToName.containsKey(email)){
            for(Integer ID : emailsToIDs.get(email)){
                IDtoEmails.get(ID).add(email);
            }
            emailsToIDs.remove(email);
            emailToName.remove(email);
        }
    }

    public final void recover(Object[] o){
        user = o.length;
        IDtoEmails = (HashMap<Integer,HashSet<String>>)o[0];
        emailToName = (HashMap<String,String>)o[1];
        animalIDtoName = (HashMap<Integer,String>)o[2];
        emailsToIDs = (HashMap<String, HashSet<Integer> >)o[3];

    }

    public void backUp(){
        Object[] data = {IDtoEmails,emailToName,animalIDtoName,emailsToIDs};
        serialization ser = new serialization(data, "/Users/varunjain/Desktop","responder");
        ser.SerializeObject();
    }

    public HashMap<Integer,HashSet<String>> getResponderMap(){
        return IDtoEmails;
    }

    public HashMap<String, String> getEmailToName() {
        return emailToName;
    }

    public void setEmailToName(HashMap<String, String> emailToName) {
        this.emailToName = emailToName;
    }

    public HashMap<String,HashSet<Integer>> getEmailsToIDs() {
        return emailsToIDs;
    }

    public void setEmailsToIDs(HashMap<String,HashSet<Integer> > emailsToIDs) {
        this.emailsToIDs = emailsToIDs;
    }

    public Object[][] UserInfoOutPut(){

        Object[][] out = new Object[user][3];
        int i = 0;
        for(String s : emailToName.keySet()){
            out[i][0] = emailToName.get(s);
            out[i][1] = s;
            //out[i][2] = emailsToIDs.get(s).toString();
            i++;
        }
        return out;
    }
    
    public Object[] readBackUpFile(String path){
        deserialization d = new deserialization(path);
        d.deserializeObject();
        Object[] o = d.getData();
        if( o == null){
            //TODO
            System.out.println("error");
            System.exit(0);
        }
        return o;
    }

    public String toString(){
        String s = "Animals ID to Emails\n";
        for(Integer i : IDtoEmails.keySet()){
            s+= "" + i + "  " + IDtoEmails.get(i).toString() + "\n";
        }
        s+= "\nemail to name \n";

        for(String ss :  emailToName.keySet()){
            s+= ss + "  " +emailToName.get(ss) + "\n";
        }
        s+= "\nanimal id to name\n";

        for(Integer i : animalIDtoName.keySet()){
            s+="" + i +"  " +animalIDtoName.get(i) + "\n";
        }
        s+="\nemails to IDs\n";

        for(String ss : emailsToIDs.keySet()){
            s+=ss+"  " +emailsToIDs.get(ss).toString();
        }

        return s;
    }
}
