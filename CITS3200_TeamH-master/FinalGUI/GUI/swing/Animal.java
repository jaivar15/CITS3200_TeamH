package swing;
public class Animal {

	String animalName;
	String animalDescription;
	double deviation;
	double orangeDev;
	double redDev;
	int days;
	double duration;
	String filePath;
	boolean advancedAlerts;
	
	public String getAnimalName() {
		return animalName;
	}
	
	public String getAnimalDescription() {
		return animalDescription;
	}
	
	public double getDeviation() {
		return deviation;
	}
	
	public double getOrangeDev() {
		return orangeDev;
	}
	
	public double getRedDev() {
		return redDev;
	}
	
	public int getDays() {
		return days;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public boolean getAdvancedAlerts() {
		return advancedAlerts;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Animal animal = (Animal) o;
		
		if (Double.compare(animal.getDeviation(), getDeviation()) != 0) return false;
		if (Double.compare(animal.getOrangeDev(), getOrangeDev()) != 0) return false;
		if (Double.compare(animal.getRedDev(), getRedDev()) != 0) return false;
		if (getDays() != animal.getDays()) return false;
		if (Double.compare(animal.getDuration(), getDuration()) != 0) return false;
		if (!getAnimalName().equals(animal.getAnimalName())) return false;
		if (!getAnimalDescription().equals(animal.getAnimalDescription())) return false;
		return getFilePath().equals(animal.getFilePath());
	}
	
	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getAnimalName().hashCode();
		result = 31 * result + getAnimalDescription().hashCode();
		temp = Double.doubleToLongBits(getDeviation());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getOrangeDev());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getRedDev());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getDays();
		temp = Double.doubleToLongBits(getDuration());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getFilePath().hashCode();
		return result;
	}
}
