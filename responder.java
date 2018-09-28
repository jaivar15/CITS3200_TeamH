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

    public void addResponder(int ID,String Name, String address){
        if(IDtoEmails.containsKey(ID)){
            IDtoEmails.get(ID).addNewAddress(address);
            emailsToIDs.get(address).add(ID);

        }else{
            IDtoEmails.put(ID, new senderAddress());
            IDtoEmails.get(ID).addNewAddress(address);
            emailsToIDs.put(address,new HashSet<>() );
            emailsToIDs.get(address).add(ID);
        }

        if(!emailToName.containsKey(address)){
            emailToName.put(address,Name);
        }
    }

    public void addMutiResponder(int[] id,String[] names, String address){
        for(int i = 0; i < id.length ; i++){
            addResponder(id[i], names[i], address);
        }
    }

    public void removeAnimal(int ID){
        if(IDtoEmails.containsKey(ID)){
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
}