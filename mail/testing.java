import java.util.Properties;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
/**
 * Write a description of class testing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class testing
{
    public static void main(String[] args)throws Exception{
        String[] mails = {"kieron_ho25@hotmail.com","22249435@student.uwa.edu.au"};
        mail Mail = new mail("cunjun.yin01@gmail.com","************","587",mails);
        Mail.setSenderEmailSMTPHost( "smtp.gmail.com");  
        Properties props = Mail.initProperties();
        Mail.initSmtpPort(props);
        MimeMultipart finals = Mail.text_image("this is a image","honda.jpg","picture1");
        Mail.sent(props,finals);
        
    }
}
