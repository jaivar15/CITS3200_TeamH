import java.util.HashSet;

public class senderAddress {
    private HashSet<String> mailAddresses;

    public senderAddress(){
        mailAddresses = new HashSet<String>();
    }

    public void addNewAddress(String address){
        if (!mailAddresses.contains(address)) {
            mailAddresses.add(address);
        }
    }

    public void removeAddress(String address) {
        if (!mailAddresses.isEmpty()) {
            if (mailAddresses.contains(address)) {
                mailAddresses.remove(address);
            }
        }
    }

    public HashSet<String> getMailAddress(){
        return mailAddresses;
    }
}
