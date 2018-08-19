import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * version 1.0
 * JavaMail version: 1.6.0
 * JDK version: JDK 1.7 or up
 * simple test email sending 
 */
public class testmail {

    // sender password email and password（change to your own passowrd and email）
    // PS:if using gmail have to allow ""Less secure app access"(on), as this is unknow app, i dont know how to make it scure.
    public static String myEmailAccount = "xxxxxxxx@gmail.com"; // mail user name 
    public static String myEmailPassword = "xxxxxx"; // password

    // sender's stmp formate: smtp.xxx.com 
    // gmail formate: smtp.gmail.com
    public static String myEmailSMTPHost = "smtp.gmail.com"; // gmail is this format other email i dont know 

    // receiver's Email 
    public static String receiveMailAccount = "**********@mail.com"; //receiver email account

    public static void main(String[] args) throws Exception {
        // 1. set up properties, used go connect to mails
        Properties props = new Properties();                    // properties configuration
        props.setProperty("mail.transport.protocol", "smtp");   // protocol Agreement（JavaMail format requirements）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // sender's stmp protocol address
        props.setProperty("mail.smtp.auth", "true");            // request authentication

        final String smtpPort = "587";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        // "*/"
        
        // 2. Create a session object based on the configuration to interact with the mail server
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // activate debug mode, get some infromation log

        // 3. create a mail, some text 
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. Get the transfer object according to the Session
        Transport transport = session.getTransport();

        // 5. using mail passowrd and account: error will pop up when something is wrong, tested gmail would work
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. send message
        transport.sendMessage(message, message.getAllRecipients());

        // 7. close connection
        transport.close();
    }

    /**
     * create a simple mail
     * @param session
     * @param sendMail 
     * @param receiveMail 
     * @return message
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. create a mail
        MimeMessage message = new MimeMessage(session);

        // 2. sender: avoid scam name
        message.setFrom(new InternetAddress(sendMail, "name", "UTF-8"));

        // 3. resciver: can add more
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));

        // 4. Subject: care scam subject, mail will block it
        message.setSubject("******subject*******", "UTF-8");

        // 5. Content: avoid scam content
        message.setContent("test mail----------------- good if received", "text/html;charset=UTF-8");

        // 6. Set the sending time
        message.setSentDate(new Date());

        // 7. Save Settings
        message.saveChanges();

        return message;
    }

}
