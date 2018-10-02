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
		assertTrue(dayDataSet.getFullDayDataArray().size() != 0);
	}

	@Test
	void testGetDayDateGrouping() {
		addDataPoints();
		assertEquals(LocalDate.now(), dayDataSet.getDayDateGrouping());
	}

	@Test
	void testGetFullDayDataArray() {
		addDataPoints();
		assertEquals(5, dayDataSet.getFullDayDataArray().size());
	}

	@Test
	void testAddDataPointToDay() {
		addDataPoints();
		assertEquals(32, dayDataSet.getFullDayDataArray().get(4).getTemperature());
	}

	@Test
	void testRemoveFromDayData() {
		addDataPoints();
		dayDataSet.removeFromDayData(timeNow.plusHours(2));
		assertEquals(4, dayDataSet.getFullDayDataArray().size());
		dayDataSet.removeFromDayData(timeNow);
		assertEquals(3, dayDataSet.getFullDayDataArray().size());
		dayDataSet.removeFromDayData(timeNow.plusHours(1));
		dayDataSet.removeFromDayData(timeNow.plusHours(3));
		DataPoint pointLeft = dayDataSet.getFullDayDataArray().get(0);
		assertEquals(32, pointLeft.getTemperature());
		assertEquals(times[4], pointLeft.getTime());
	}

	@Test
	void testGetDataFromTime() {
		addDataPoints();
		DataPoint secondPoint = dayDataSet.getDataFromTime(timeNow.plusHours(1));
		DataPoint testSecondPoint = new DataPoint(timeNow.plusHours(1), 20);
		assertTrue(secondPoint.isEqualTo(testSecondPoint));
		assertEquals(25, dayDataSet.getDataFromTime(timeNow.plusMinutes(30)).getTemperature());
		assertEquals(19.5, dayDataSet.getDataFromTime(timeNow.plusMinutes(90)).getTemperature());
	}

	@Test
	void testTimeAlreadyExists() {
		addDataPoints();
		assertTrue(dayDataSet.timeAlreadyExists(timeNow.plusHours(2)));
		assertFalse(dayDataSet.timeAlreadyExists(timeNow.plusHours(6)));
		assertTrue(dayDataSet.timeAlreadyExists(timeNow.plusMinutes(120)));
	}
}
