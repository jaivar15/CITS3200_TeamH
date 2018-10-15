package deviationTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DayDataTest {
	LocalDateTime timeNow = LocalDateTime.now();
	LocalDateTime[] times = {timeNow, timeNow.plusHours(1), timeNow.plusHours(2), timeNow.plusHours(3), timeNow.plusHours(4)};
	double[] temperatures = {30, 20, 19, 35, 32};
	DataPoint pointOne = new DataPoint(times[0], temperatures[0]);
	DataPoint pointTwo = new DataPoint(times[1], temperatures[1]);
	DataPoint pointThree = new DataPoint(times[2], temperatures[2]);
	DataPoint pointFour = new DataPoint(times[3], temperatures[3]);
	DataPoint pointFive = new DataPoint(times[4], temperatures[4]);
	DayData dayDataSet = new DayData(LocalDate.now());

	public void addDataPoints() {
		dayDataSet.addDataPointToDay(pointOne);
		dayDataSet.addDataPointToDay(pointTwo);
		dayDataSet.addDataPointToDay(pointThree);
		dayDataSet.addDataPointToDay(pointFour);
		dayDataSet.addDataPointToDay(pointFive);
	}
	
	@Test
	void testDayData() {
		addDataPoints();
		assertTrue(dayDataSet.getFullDayDataHashMap().size() != 0);
	}

	@Test
	void testGetDayDateGrouping() {
		addDataPoints();
		assertEquals(LocalDate.now(), dayDataSet.getDayDateGrouping());
	}
	
	@Test void testGetFullDayDataHashMap() {
		assertEquals(0, dayDataSet.getFullDayDataHashMap().size());
		addDataPoints();
		assertEquals(5, dayDataSet.getFullDayDataHashMap().size());
	}

	
	@Test
	void testAddDataPointToDay() {
		addDataPoints();
		assertEquals(32, dayDataSet.getFullDayDataHashMap().get(times[4]).getTemperature());
	}
	

	@Test
	void testRemoveFromDayData() {
		addDataPoints();
		dayDataSet.removeFromDayData(timeNow.plusHours(2));
		assertEquals(4, dayDataSet.getFullDayDataHashMap().size());
		dayDataSet.removeFromDayData(timeNow);
		assertEquals(3, dayDataSet.getFullDayDataHashMap().size());
		dayDataSet.removeFromDayData(timeNow.plusHours(1));
		dayDataSet.removeFromDayData(timeNow.plusHours(3));
		DataPoint pointLeft = dayDataSet.getFullDayDataHashMap().values().iterator().next();
		assertEquals(32, pointLeft.getTemperature());
		assertEquals(times[4], pointLeft.getTime());
	}

	@Test
	void testGetDataFromTime() {
		addDataPoints();
		DataPoint secondPoint = dayDataSet.getDataFromTime(timeNow.plusHours(1));
		DataPoint testSecondPoint = new DataPoint(timeNow.plusHours(1), 20);
		assertTrue(secondPoint.isEqualTo(testSecondPoint));
	}
}
