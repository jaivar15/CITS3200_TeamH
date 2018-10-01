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
		dayDataSet.addDailyData(pointOne);
		dayDataSet.addDailyData(pointTwo);
		dayDataSet.addDailyData(pointThree);
		dayDataSet.addDailyData(pointFour);
		dayDataSet.addDailyData(pointFive);
	}
	
	@Test
	void testDayData() {
		addDataPoints();
		assertTrue(dayDataSet.getDailyData().size() != 0);
	}

	@Test
	void testGetDayGrouping() {
		addDataPoints();
		assertEquals(LocalDate.now(), dayDataSet.getDayGrouping());
	}

	@Test
	void testGetDailyData() {
		addDataPoints();
		assertEquals(5, dayDataSet.getDailyData().size());
	}

	@Test
	void testAddDailyData() {
		addDataPoints();
		assertEquals(32, dayDataSet.getDailyData().get(4).getTemperature());
	}

	@Test
	void testRemoveDailyData() {
		addDataPoints();
		dayDataSet.removeDailyData(timeNow.plusHours(2));
		assertEquals(4, dayDataSet.getDailyData().size());
		dayDataSet.removeDailyData(timeNow);
		assertEquals(3, dayDataSet.getDailyData().size());
		dayDataSet.removeDailyData(timeNow.plusHours(1));
		dayDataSet.removeDailyData(timeNow.plusHours(3));
		DataPoint pointLeft = dayDataSet.getDailyData().get(0);
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
