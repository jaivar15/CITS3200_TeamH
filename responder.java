import java.util.HashMap;
public class responder {
   private HashMap<Integer,senderAddress> responderMap;

    public responder(){
        responderMap = new HashMap<Integer,senderAddress>();
    }

    public void addResponder(int ID, String address){
        if(!responderMap.containsKey(ID)){
            responderMap.get(ID).addNewAddress(address);
        }
    }

    public void addMutiResponder(int[] id, String address){
        for(int i = 0; i < id.length ; i++){
            addResponder(id[i],address);
        }
    }

    public void removeID(int ID){
        if(!responderMap.containsKey(ID)){
            responderMap.remove(ID);
        }
    }

    public void removeMutiID(int[] ID){
        for(int i = 0; i < ID.length; i++){
            removeID(ID[i]);
        }
    }

    public void newID(int ID){
        if(!responderMap.containsKey(ID)){
            responderMap.put(ID,new senderAddress());
        }
    }

    public HashMap<Integer,senderAddress> getResponderMap(){
        return responderMap;
    }

}
