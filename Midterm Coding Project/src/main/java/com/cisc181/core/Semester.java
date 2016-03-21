package com.cisc181.core;

import java.util.Date;
import java.util.UUID;

public class Semester {
	private UUID SemesterID;
	private Date StartDate;
	private Date EndDate;
	
	public Semester(UUID semesterID, Date startDate, Date endDate) {
		super();
		SemesterID = semesterID;
		StartDate = startDate;
		EndDate = endDate;
	}
	public UUID getSemesterID() {
		return SemesterID;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
}
