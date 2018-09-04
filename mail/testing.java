import java.util.Properties;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
/**
 * Write a description of class testing here.
 *
 * @author (CITS3200 Team H)
 * @version (1.1)
 */
public class testing
{
    public static void main(String[] args)throws Exception{
        String[] mails = {"*******.com","*******8@gmail.com"};
        mail Mail = new mail("********@gmail.com","**********","587",mails);
        Mail.setSenderEmailSMTPHost( "smtp.gmail.com");
        Properties props = Mail.initProperties();
	Mail.initSmtpPort(props);
        //MimeMultipart finals = Mail.text("only text in this meeage");
	MimeMultipart finals = Mail.text_image("******", "***.jpg","ID 1001")
        Mail.sent(props,finals);
        
    }
}
