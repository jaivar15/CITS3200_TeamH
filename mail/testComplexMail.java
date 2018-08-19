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

/**
 * version 1.1
 * JavaMail version: 1.6.0
 * JDK version: JDK 1.7 or up
 */
public class testComplexMail {

    // sender password email and password（change to your own passowrd and email）
    // PS:if using gmail have to allow ""Less secure app access"(on), as this is unknow app, i dont know how to make it scure.
    public static String myEmailAccount = "******@gmail.com";
    public static String myEmailPassword = "*******;

    // sender's stmp formate: smtp.xxx.com // usually not always
    public static String myEmailSMTPHost = "smtp.***.com";

    // receiver's Email 
    public static String receiveMailAccount = "*****@mail.com";

    public static void main(String[] args) throws Exception {
        // 1. set up properties, used go connect to mails
        Properties props = new Properties();                    // properties configuration
        props.setProperty("mail.transport.protocol", "smtp");   // protocol Agreement（JavaMail format requirements）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // sender's stmp protocol address
        props.setProperty("mail.smtp.auth", "true");            // request authentication

	//ssl
	//is smtp port not always 587 
	final String smtpPort = "587"; // change if needed
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);        

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
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. create a mail
        MimeMessage message = new MimeMessage(session);

        // 2. sender: avoid scam name
        message.setFrom(new InternetAddress(sendMail, "cunjun", "UTF-8"));

        // 3. resciver: can add more
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));

        // 4. Subject: care scam subject, mail will block it
        message.setSubject("testing_complex", "UTF-8");

        /*
         * mail content
         */

        // 5. image node creation
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("BellmanFord.png")); //read local picture file
        image.setDataHandler(dh);                   // put the picture file into handler
        image.setContentID("BellmanFord_image");     // set an ID for data handler

        // 6. create file node
        MimeBodyPart text = new MimeBodyPart();
        //    put inmage intp mail content 
        text.setContent("this is a image<br/><img src='cid:BellmanFord_image'/>", "text/html;charset=UTF-8");

        // 7. （txt+picture） manpulpate the relationship between picture and txt
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related");    // connect

        // 8.image part
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        // 9. create file node
        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource("testmail.java"));  // read local file
        attachment.setDataHandler(dh2);                                             // create a handler
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));              // id of local file 

        // 10. set pict + file
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment);     // if contain mito attachment, add.
        mm.setSubType("mixed");         // mixed relationship

        // 11.Set the relationship of the entire message
        message.setContent(mm);

        // 6. Set the sending time
        message.setSentDate(new Date());

        // 7. Save Settings
        message.saveChanges();

        return message;
    }
}
