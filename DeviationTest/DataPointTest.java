package deviationTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DataPointTest {
	LocalDateTime timeNow = LocalDateTime.now();
	LocalDateTime[] times = {timeNow, timeNow.plusHours(1), timeNow.plusHours(2), timeNow.plusHours(3), timeNow.plusHours(4)};
	double[] temperatures = {30, 20, 19, 35, 32};
	DataPoint pointOne = new DataPoint(times[0], temperatures[0]);
	DataPoint pointTwo = new DataPoint(times[1], temperatures[1]);
	DataPoint pointThree = new DataPoint(times[2], temperatures[2]);
	DataPoint pointFour = new DataPoint(times[3], temperatures[3]);
	DataPoint pointFive = new DataPoint(times[4], temperatures[4]);
	
	@Test
	void testDataPoint() {
		assertTrue(pointOne != null);
		assertTrue(pointTwo != null);
	}

	@Test
	void testGetTemperature() {
		assertEquals(30, pointOne.getTemperature());
		assertEquals(20, pointTwo.getTemperature());
		assertEquals(19, pointThree.getTemperature());
		assertEquals(35, pointFour.getTemperature());
		assertEquals(32, pointFive.getTemperature());
	}

	@Test
	void testModifyTemperature() {
		pointOne.modifyTemperature(15);
		assertEquals(15, pointOne.getTemperature());
		pointOne.modifyTemperature(30);
		assertEquals(30, pointOne.getTemperature());
		pointFour.modifyTemperature(34);
		assertEquals(34, pointFour.getTemperature());
		pointFour.modifyTemperature(35);
		assertEquals(35, pointFour.getTemperature());
	}

	@Test
	void testGetTime() {
		assertEquals(timeNow, pointOne.getTime());
		System.out.println(pointOne.getTime());
		assertEquals(timeNow.plusHours(3), pointFour.getTime());
		System.out.println(pointFour.getTime());
	}

	@Test
	void testGetViability() {
		pointFive.setViability(false);
		assertEquals(false, pointFive.getViability());
		pointTwo.setViability(false);
		assertEquals(false, pointTwo.getViability());
		
	}
	
	
	@Test
	void testSetViability() {
		assertTrue(pointOne.getViability());
		pointOne.setViability(false);
		assertFalse(pointOne.getViability());
	}
	

	@Test
	void testGetUnmodifiedTemperature() {
		pointFive.modifyTemperature(19);
		pointThree.modifyTemperature(100);
		assertEquals(32, pointFive.getUnmodifiedTemperature());
		assertEquals(19, pointThree.getUnmodifiedTemperature());
	}
	
	@Test
	void testIsEqualTo() {
		assertFalse(pointOne.isEqualTo(pointTwo));
		DataPoint pointOneClone = new DataPoint(timeNow, 30);
		assertTrue(pointOne.isEqualTo(pointOneClone));
	}

}
