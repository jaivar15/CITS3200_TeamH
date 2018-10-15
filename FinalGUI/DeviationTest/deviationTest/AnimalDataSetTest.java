package deviationTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

class AnimalDataSetTest {
	AnimalDataSet animalOneData = new AnimalDataSet();
	LocalDateTime timeNow = LocalDate.now().atTime(3, 0);//the time at current day, 3AM
	LocalDateTime[] times = {timeNow, timeNow.plusHours(1), timeNow.plusHours(2), timeNow.plusHours(3), timeNow.plusHours(4), timeNow.plusHours(24), timeNow.plusHours(25), timeNow.plusHours(26), timeNow.plusHours(27), timeNow.plusHours(28)};
	double[] temperatures = {30, 20, 19, 35, 32, 20, 19, 15, 35, 10};
	
	public void addData() {
		DataPoint pointOne = new DataPoint(times[0], temperatures[0]);
		DataPoint pointTwo = new DataPoint(times[1], temperatures[1]);
		DataPoint pointThree = new DataPoint(times[2], temperatures[2]);
		DataPoint pointFour = new DataPoint(times[3], temperatures[3]);
		DataPoint pointFive = new DataPoint(times[4], temperatures[4]);
		DataPoint pointSix = new DataPoint(times[5], temperatures[5]);
		DataPoint pointSeven = new DataPoint(times[6], temperatures[6]);
		DataPoint pointEight = new DataPoint(times[7], temperatures[7]);
		DataPoint pointNine = new DataPoint(times[8], temperatures[8]);
		DataPoint pointTen = new DataPoint(times[9], temperatures[9]);
		animalOneData.addDataPoint(pointOne);
		animalOneData.addDataPoint(pointTwo);
		animalOneData.addDataPoint(pointThree);
		animalOneData.addDataPoint(pointFour);
		animalOneData.addDataPoint(pointFive);
		animalOneData.addDataPoint(pointSix);
		animalOneData.addDataPoint(pointSeven);
		animalOneData.addDataPoint(pointEight);
		animalOneData.addDataPoint(pointNine);
		animalOneData.addDataPoint(pointTen);
	}

	@Test
	void testAnimalDataSets() {
		addData();
		assertTrue(animalOneData.getClass() != null);
	}

	@Test
	void testDaysOfDataCount() {
		addData();
		assertEquals(2, animalOneData.daysOfDataCount());
	}

	
	@Test
	void testGetAnimalDataDaysArray() {
		addData();
		ArrayList<DayData> theData = new ArrayList<DayData>();
		theData.addAll(animalOneData.getAnimalDataDaysHashMap().values());
		assertEquals(2, theData.size());
		DayData firstDay;
		DayData secondDay;
		Iterator<DayData> dayIteration = theData.iterator();
		firstDay = dayIteration.next();
		secondDay = dayIteration.next();
		if(firstDay.getDayDateGrouping().isAfter(secondDay.getDayDateGrouping())) {
			DayData temp = firstDay;
			firstDay = secondDay;
			secondDay = temp;
		}
		assertTrue(times[0].toLocalDate().isEqual(firstDay.getDayDateGrouping()));
		assertTrue(times[5].toLocalDate().isEqual(secondDay.getDayDateGrouping()));
	}

	@Test
	void testGetIndividualDayData() {
		addData();
		DayData firstDayData = animalOneData.getIndividualDayData(LocalDate.now());
		DayData secondDayData = animalOneData.getIndividualDayData(LocalDate.now().plusDays(1));
		assertTrue(firstDayData.getDayDateGrouping().isEqual(LocalDate.now()));
		assertTrue(secondDayData.getDayDateGrouping().isEqual(LocalDate.now().plusDays(1)));
		assertTrue(firstDayData.getDayDateGrouping().isBefore(secondDayData.getDayDateGrouping()));
	}

	@Test
	void testGetDataPointFromTime() {
		addData();
		DataPoint actualDataPoint1 = new DataPoint(times[3], temperatures[3]);
		DataPoint actualDataPoint2 = new DataPoint(times[7], temperatures[7]);
		assertTrue(animalOneData.getDataPointFromTime(times[3]).isEqualTo(actualDataPoint1));
		assertTrue(animalOneData.getDataPointFromTime(times[7]).isEqualTo(actualDataPoint2));
	}

	@Test
	void testGetTemperatureFromTime() {
		addData();
		double actualTemperature1 = 30;
		LocalDateTime time1 = timeNow;
		double actualTemperature6 = 20;
		LocalDateTime time6 = timeNow.plusHours(24);
		assertEquals(actualTemperature1, animalOneData.getTemperatureFromTime(time1));
		assertEquals(actualTemperature6, animalOneData.getTemperatureFromTime(time6));
	}

	@Test
	void testAddDataPoint() {
		assertEquals(0, animalOneData.daysOfDataCount());
		addData();
		assertEquals(2, animalOneData.daysOfDataCount());
		animalOneData.addDataPoint(timeNow.plusDays(3), 100);
		assertEquals(3, animalOneData.daysOfDataCount());
		addData();
		assertEquals(3, animalOneData.daysOfDataCount());
	}
	
	@Test
	void testTimeAlreadyRecorded() {
		addData();
		assertTrue(animalOneData.timeAlreadyRecorded(timeNow));
		assertTrue(animalOneData.timeAlreadyRecorded(times[6]));
		assertFalse(animalOneData.timeAlreadyRecorded(timeNow.minusDays(10)));
	}

	@Test
	void testGetLatestUpdate() {
		AnimalDataSet emptyDataSet = new AnimalDataSet();
		assertTrue(emptyDataSet.getLatestUpdate() == null);
		addData();
		LocalDateTime actualLatestTime = timeNow.plusHours(28);
		double actualLatestTemperature = 10;
		assertEquals(actualLatestTime, animalOneData.getLatestUpdate().getTime());
		assertEquals(actualLatestTemperature, animalOneData.getLatestUpdate().getTemperature());
		DataPoint newDataPoint = new DataPoint(timeNow.plusHours(90), 103);
		animalOneData.addDataPoint(newDataPoint);
		assertEquals(103, animalOneData.getLatestUpdate().getTemperature());
		assertEquals(timeNow.plusHours(90), animalOneData.getLatestUpdate().getTime());
		DataPoint earlierDataPoint = new DataPoint(timeNow.minusHours(100), 31.5);
		animalOneData.addDataPoint(earlierDataPoint);
		assertEquals(timeNow.plusHours(90), animalOneData.getLatestUpdate().getTime());
	}

}
