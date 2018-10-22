package parser;

import DeviationTest.DataPoint;
import DeviationTest.AnimalDataSet;
import GUI.swing.Animal;
import GUI.swing.Home;
import GUI.swing.Notifications;
import GUI.swing.responder;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import mail.mail;

import javax.mail.internet.MimeMultipart;

import static DeviationTest.DeviationChecks.checkDeviationLatest;

public class CSVMonitor extends Thread{
	
	private int charCount, lineCount, consecutiveTimeForAlert, daysToCheck; //get someone to make consec. time minutes or
	private double[] deviationBeforeAlert;									//hours and define this, make it an int in settings.
	private Animal animal;
	private LocalDateTime timeToAlert;
	private BufferedReader reader;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private String delimiter, sensorID, senderEmail, senderEmailPass;
	private String[] receivingEmails = new String[100];
	private AnimalDataSet data;
	private AtomicBoolean stop = new AtomicBoolean(false);
	private Boolean initialRead = true;
	private boolean advancedAlerts = false;
	private LocalDateTime lastDataPointReceived, lastAlertSentAt;
	private Home home;

	/**
	 * Creating this object will monitor an animal's CSV data file and send an email when deviation parameters met
	 * Maybe this one for backup feature?
	 * @param a Animal that this monitor object will be watching
	 */
	public CSVMonitor(Animal a, Home h, int startLine) {
		home = h;
		lastAlertSentAt = null;
		animal = a;
		consecutiveTimeForAlert = (int) animal.getDuration();
		daysToCheck = animal.getDays();
		advancedAlerts = animal.getAdvancedAlerts();
		if(advancedAlerts){
			deviationBeforeAlert = new double[3];
			deviationBeforeAlert[0] = animal.getDeviation();
			deviationBeforeAlert[1] = animal.getOrangeDev();
			deviationBeforeAlert[2] = animal.getRedDev();
		} else {
			deviationBeforeAlert = new double[1];
			deviationBeforeAlert[0] = animal.getDeviation();
		}
		if (home.getResponder().getEmailsForAnimal(animal.getAnimalName()) != null){
			receivingEmails = home.getResponder().getEmailsForAnimal(animal.getAnimalName()).toArray(new String[0]);
		}
		charCount = 0;
		lineCount = 0;
		delimiter = ",";
		sensorID = "";
		data = new AnimalDataSet();
		try {
			reader = new BufferedReader(new FileReader(animal.getFilePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (; lineCount < startLine; lineCount++) {
			try {
				reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ReadCSV();
		initialRead = false;
	}
	
	/**
	 * Creating this object will monitor an animal's CSV data file and send an email when deviation parameters met
	 * Use this one for now
	 * @param a Animal that this monitor object will be watching
	 */
	public CSVMonitor(Animal a, Home h){
		this(a, h, 0);
	}
	
	/**
	 * Check if user has updated animal parameters in GUI and make these changes to the monitor
	 */
	private void checkForUpdatesToAnimal(){
		Animal temp = GUI.swing.change.getAnimal(animal.getAnimalName());
		if (!animal.equals(temp)){
			animal = temp;
			consecutiveTimeForAlert = (int) animal.getDuration();
			daysToCheck = animal.getDays();
			advancedAlerts = animal.getAdvancedAlerts();
			if(advancedAlerts){
				deviationBeforeAlert = new double[3];
				deviationBeforeAlert[0] = animal.getDeviation();
				deviationBeforeAlert[1] = animal.getOrangeDev();
				deviationBeforeAlert[2] = animal.getRedDev();
			} else {
				deviationBeforeAlert = new double[1];
				deviationBeforeAlert[0] = animal.getDeviation();
			}
			if (home.getResponder().getEmailsForAnimal(animal.getAnimalName()) != null){
				receivingEmails = home.getResponder().getEmailsForAnimal(animal.getAnimalName()).toArray(new String[0]);
			}
		}
	}
	
	/**
	 * Read the CSV file for the newest entry
	 * Do some roundabout shit if first read.
	 * @return latest datapoint from CSV file
	 */
	public DataPoint ReadCSV() {
		String[] line = new String[2];
		DataPoint p = null;
		try {
			while ((line[0] = reader.readLine()) != null) {
				if (line[0].length() == 0) {
					continue;
				}
				charCount += line[0].length();
				line = line[0].split(delimiter);
				if (lineCount == 0) {
					sensorID = line[1];
					lineCount++;
					continue;
				}
				try {
					p = new DataPoint(LocalDateTime.parse(line[0], formatter), Double.parseDouble(line[1]));
					if (initialRead && data.getLatestUpdate() != null){
						if(!data.getLatestUpdate().isEqualTo(p)){
							data.addDataPoint(p);
						}
					} else if (initialRead && data.getLatestUpdate() == null){
						data.addDataPoint(p);
					}
				} catch (DateTimeParseException e) {
					e.printStackTrace();
				}
				lineCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 *
	 * @return dataset for this monitor
	 */
	public AnimalDataSet getData() {
		return data;
	}
	
	/**
	 * Check if monitor is running or stopped
	 * @return True if stopped, false if running
	 */
	public boolean isStopped() { return stop.get(); }
	
	/**
	 * Use this to stop monitor from checking, will still exist in memory tho
	 */
	public void stopThread() { stop.set(true); }
	
	/**
	 * Runs the monitor every minute
	 * Reads the CSV file for latest changes
	 * If there is an update check for deviation, send alert if necessary
	 * Use .interrupt() to update early
	 */
	@Override
	public void run() {
		while (!isStopped()){
			checkForUpdatesToAnimal();

			DataPoint fromFile = ReadCSV();
			DataPoint latest = data.getLatestUpdate();

				if (!latest.isEqualTo(fromFile)) {

					data.addDataPoint(fromFile);

					latest = data.getLatestUpdate();
					double[] check = checkDeviationLatest(data, deviationBeforeAlert, daysToCheck);
					int maxAlert = 0;
					if (check[0] == 1){
						if (maxAlert < check[1]){
							maxAlert = (int) check[1];
						}
						if (timeToAlert == null){
							timeToAlert = data.getLatestUpdate().getTime().plusHours(consecutiveTimeForAlert);
						} else if (timeToAlert.compareTo(latest.getTime()) <= 0 ){
							lastAlertSentAt = latest.getTime();
							sendMail(maxAlert);
							timeToAlert = null;
							maxAlert = 0;
						}
					}
					if (timeToAlert != null && check[0] == 0){
						timeToAlert = null;
						maxAlert = 0;
					}
				}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException ignored) {
			}
		}
	}
	
	public String getSensorID(){
		return sensorID;
	}
	
	public void setSensorID(String s){
		sensorID = s;
	}
	
	public String getLastAlertSentAt(){
		if (lastAlertSentAt != null){
			return lastAlertSentAt.format(formatter);
		} else {
			return "No Alerts Sent";
		}
	}
	
	/**
	 * Sends mail
	 * TODO: Add receiving emails list to this, add way to get sender email and pass.
	 */
	private void sendMail(int alertL){
		mail mailer = null;
		String alertLevel = "";
		senderEmail = home.getResponder().getSenderEmail();
		senderEmailPass = home.getResponder.getSenderEmailPass();
		if (advancedAlerts){
			switch (alertL) {
				case (1):
					alertLevel = "Green";
					break;
				case (2):
					alertLevel = "Orange";
					break;
				case (3):
					alertLevel = "Red";
					break;
			}
		} else {
			alertLevel = "ALERT";
		}
		try {
			mailer = new mail(senderEmail, senderEmailPass, "587", receivingEmails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties props = mailer.initProperties();
		mailer.initSmtpPort(props);
		MimeMultipart finals;
		try {
			finals = mailer.text(alertLevel + ": " + animal.getAnimalName() + "'s temperature has deviated");
			mailer.sent(props,finals);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//home.addNotification(new Notifications(animal.getAnimalName(), animal.getAnimalDescription(), alertLevel));
	}
	
	public Animal getMonitoredAnimal() {
		return animal;
	}

}