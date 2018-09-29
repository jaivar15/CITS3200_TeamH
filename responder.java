package com.company;

import java.util.HashMap;
import java.util.HashSet;

public class responder {
    private HashMap<Integer,senderAddress> IDtoEmails;
    private HashMap<String,String> emailToName;
    private HashMap<Integer,String> animalIDtoName;
    private HashMap<String, HashSet<Integer> > emailsToIDs;

    public responder(){
        IDtoEmails = new HashMap<>();
        emailToName = new HashMap<>();
        emailsToIDs = new HashMap<>();
        animalIDtoName = new HashMap<>();
    }

    public void addResponder(int ID, String address){
        if(emailToName.containsKey(address) && animalIDtoName.containsKey(ID)){
            if( !IDtoEmails.containsKey(ID)){
                IDtoEmails.put(ID,new senderAddress());
            }
            IDtoEmails.get(ID).addNewAddress(address);

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
            for(String s: IDtoEmails.get(ID).getMailAddress()){
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

    public void newUser(String name, String email){
        if(!emailToName.containsKey(email)){
            emailToName.put(email,name);
        }
    }

    public void removeEmail(String email){
        if(emailsToIDs.containsKey(email) && emailToName.containsKey(email)){
            for(Integer ID : emailsToIDs.get(email)){
                IDtoEmails.get(ID).removeAddress(email);
            }
            emailsToIDs.remove(email);
            emailToName.remove(email);
        }
    }

    public void backUp(Object[] o){
        IDtoEmails = (HashMap<Integer,senderAddress>)o[0];
        emailToName = (HashMap<String,String>)o[1];
        animalIDtoName = (HashMap<Integer,String>)o[2];
        emailsToIDs = (HashMap<String, HashSet<Integer> >)o[3];

    }

    public HashMap<Integer,senderAddress> getResponderMap(){
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