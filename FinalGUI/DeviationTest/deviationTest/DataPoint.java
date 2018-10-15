package deviationTest;
import java.time.LocalDateTime;

/**
 * 
 * DataPoint holds information on the time, temperature and viability status
 * 
 * @author Kieron Ho
 *
 */
public class DataPoint {
private LocalDateTime time;
private double temperature;
private boolean viability;
private double originalTemperature;

public DataPoint(LocalDateTime time, double temperature) {
	this.time = time;
	this.temperature = temperature;
	viability = true;
	originalTemperature = temperature;
}

public double getTemperature() {
	return temperature;
}

public void modifyTemperature(double newTemperature) {
	temperature = newTemperature;
}

public LocalDateTime getTime() {
	return time;
}

public boolean getViability() {
	return viability;
}

public void setViability(boolean newViability) {
	viability = newViability;
}

public double getUnmodifiedTemperature() {
	return originalTemperature;
}

/**
 * A test for data point equivalence
 * @param secondDataPoint
 * @return
 */
public boolean isEqualTo(DataPoint secondDataPoint) {
	if((secondDataPoint.getTemperature() == temperature) && (secondDataPoint.getTime().compareTo(time) == 0)) {
		return true;
	}
	else return false;
}

}
