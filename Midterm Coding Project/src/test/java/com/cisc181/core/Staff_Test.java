package com.cisc181.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;
import com.cisc181.exceptions.PersonException;

public class Staff_Test {

	@BeforeClass
	public static void setup() {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSalary() throws PersonException {
		double expectedAVG = 60200;
		ArrayList<Staff> StaffList = new ArrayList<Staff>();
		StaffList.add(new Staff("Bonard", "T", "Geyer", new Date(1997,2,14), "Some place Some where", "(555)-555-5555", "bgeyer@fakeEmail.com", "MWF 12:00-1:00", 4, 57000, new Date(), eTitle.MR));
		StaffList.add(new Staff("Steven", "L", "NotSpelling", new Date(1996,5,6), "Around the corner", "(123)-456-7890", "lsteven@fakeEmail.com", "MWF 12:00-1:00", 4, 100000, new Date(), eTitle.MR));
		StaffList.add(new Staff("Alvin", "T", "Tang", new Date(1997,10,16), "Across the street", "(098)-765-4321", "atang@fakeEmail.com", "MWF 12:00-1:00", 4, 45000, new Date(), eTitle.MR));
		StaffList.add(new Staff("Vinay", "N", "Vazir", new Date(1997,7,28), "In space", "(302)-399-8212", "vvazir@udel.edu", "MWF 12:00-1:00", 3, 34000, new Date(), eTitle.MR));
		StaffList.add(new Staff("Raveena", "T", "Wadhwa", new Date(1997,4,19), "Behind the lamp", "(102)-938-4756", "rwadhwa@fakeEmail.com", "MWF 12:00-1:00", 4, 65000, new Date(), eTitle.MS));
		int sum = 0;
		for (Staff staff:StaffList){
			sum+=staff.getSalary();
		}
		double avg = sum/5;
		assertEquals(expectedAVG,avg,1);
		
	}	
	
	@Test(expected = PersonException.class)
	public void testBadPhoneAndDOB() throws PersonException{
		//Invalid DOB
		Staff badStaff1 = new Staff("John", "J", "Doe", new Date(1915,1,1), "Middle of Nowhere", "(555)-555-5555", "jdoe@fakeEmail.com", "MWF 12:00-1:00", 4, 57000, new Date(), eTitle.MR);
		//Invalid Phone Number
		Staff badStaff2 = new Staff("Jane", "J", "Doe", new Date(1960,1,1), "Middle of Nowhere", "(5555)-555-5555", "jdoe@fakeEmail.com", "MWF 12:00-1:00", 4, 57000, new Date(), eTitle.MR);
		
	}
	

}
