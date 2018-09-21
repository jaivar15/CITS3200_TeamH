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

public DataPoint(LocalDateTime time, double temperature) {
	this.time = time;
	this.temperature = temperature;
	viability = true;
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

}
