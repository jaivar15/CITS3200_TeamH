import java.util.Properties;
/**
 * Write a description of class testing here.
 *
 * @author (CITS3200 Team H)
 * @version (1)
 */
public class testing
{
    public static void main(String[] args)throws Exception{
        String[] mails = {"*******.com","*******8@gmail.com"};
        mail Mail = new mail("********@gmail.com","**********","587",mails);
        Mail.setSenderEmailSMTPHost( "smtp.gmail.com");
        Properties props = Mail.initProperties();
        Mail.initSmtpPort(props);
        Mail.sent(props);
        
    }
}
