import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.util.ArrayList;

/**
 * This file is used for send mail when we called.
 *
 * @author (CITS3200 Team H)
 * @version (1)
 */
public class mail
{
    // instance variables
    private String senderEmailAccount;
    private String senderEmailPassword;
    private String senderEmailSMTPHost;
    private String smtpPort;
    private InternetAddress[] recipientAddress;
    private int addressLength;
    
    /**
     * Constructor for objects of class mail
     */
    public mail(String senderEmailAccount,String senderEmailPassword, String smtpPort,String[] recipientList) throws Exception
    {
        // initialise instance variables
        this.senderEmailAccount = senderEmailAccount;
        this.senderEmailPassword = senderEmailPassword;
        this.smtpPort = smtpPort;
        recipientAddress = new InternetAddress[recipientList.length];
        addressLength = 0;
        for (String recipient : recipientList) {
            recipientAddress[addressLength] = new InternetAddress(recipient.trim(), "", "UTF-8");
            addressLength++;
        }
    }
    
    public Properties initProperties(){
        //set up properties, used go connect to mails
        Properties props = new Properties();                    // properties configuration
        props.setProperty("mail.transport.protocol", "smtp");   // protocol Agreement（JavaMail format requirements）
        props.setProperty("mail.smtp.host", senderEmailSMTPHost);   // sender's stmp protocol address
        props.setProperty("mail.smtp.auth", "true");            // request authentication
        return props;
    }
    
    public void initSmtpPort(Properties props){
        //  Some Mailbox servers require SSL security authentication for SMTP connections (to increased security, 
        //  mail box have to support SSL connections, turn on yourself)
        
        // SMTP server port (not ssl serve port should be 25,
        //                  gmail SMTP(SLL) port is 465/587)
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
    }
    
    public void sent(Properties props) throws Exception{
        // Create a session object based on the configuration to interact with the mail server
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // activate debug mode, get some infromation log

        // create a mail, some text 
        MimeMessage message = createMessage(session, senderEmailAccount);

        // Get the transfer object according to the Session
        Transport transport = session.getTransport();

        // using mail passowrd and account: error will pop up when something is wrong, tested gmail would work
        transport.connect(senderEmailAccount, senderEmailPassword);

        // send message
        transport.sendMessage(message, message.getAllRecipients());

        // close connection
        transport.close();
    }
    
    public MimeMessage createMessage(Session session,String sendMail)throws Exception{
        // 1. create a mail
        MimeMessage message = new MimeMessage(session);
        // 2. sender: avoid scam name
        message.setFrom(new InternetAddress(sendMail, "name_gogogo", "UTF-8"));
        //3
        message.setRecipients(MimeMessage.RecipientType.TO, recipientAddress);
        
         // 4. Subject: care scam subject, mail will block it
        message.setSubject("EVENT IN FRIDAY", "UTF-8");

        // 5. Content: avoid scam content
        message.setContent("test mail----------------- good if received", "text/html;charset=UTF-8");

        // 6. Set the sending time
        message.setSentDate(new Date());

        // 7. Save Settings
        message.saveChanges();

        return message;
    }

    private void addInternetAddress(String recipient)throws Exception{
        InternetAddress[] tempRecipientAddress = new InternetAddress[addressLength+1];
        System.arraycopy(recipientAddress, 0, tempRecipientAddress , 0, addressLength);
        tempRecipientAddress[addressLength+1] = new InternetAddress(recipient.trim(), "", "UTF-8");
        recipientAddress = tempRecipientAddress;
    }
    
    // setter and getter methods
    public void setSenderEmailAccount(String accountName){
        senderEmailAccount = accountName;
    }
    
    public void setSenderEmailPassword(String password){
        senderEmailPassword = password;
    }
    
    public void setSenderEmailSMTPHost(String host){
        senderEmailSMTPHost = host;
    }
    
    public void set(String port){
        smtpPort = port;
    }
    
}
