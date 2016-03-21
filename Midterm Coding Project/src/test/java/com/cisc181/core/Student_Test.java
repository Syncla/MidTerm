package com.cisc181.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.cisc181.eNums.eMajor;
import com.cisc181.exceptions.PersonException;

public class Student_Test {
	ArrayList<Course> course = new ArrayList<Course>();
	ArrayList<Semester> semester = new ArrayList<Semester>();
	ArrayList<Section> section = new ArrayList<Section>();
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<Enrollment> enroll = new ArrayList<Enrollment>();
	@Before
	public void setup() throws PersonException {
		course.add(new Course(UUID.randomUUID(),"CISC 181",4,eMajor.COMPSI));
		course.add(new Course(UUID.randomUUID(),"CISC 106",4,eMajor.COMPSI));
		course.add(new Course(UUID.randomUUID(),"PHYS 207",4,eMajor.PHYSICS));
		semester.add(new Semester(UUID.randomUUID(),new Date(8,20,2015),new Date(12,20,2015)));
		semester.add(new Semester(UUID.randomUUID(),new Date(2,5,2016),new Date(5,24,2016)));		
		int roomID=100;
		for (Course cou:course){
			for (Semester sem:semester){
				section.add(new Section(cou.getCourseID(),sem.getSemesterID(),UUID.randomUUID(),roomID));
				roomID+=2;
			}
		}
		students.add(new Student("Vinay", "N", "Vazir", new Date(1997,7,28), eMajor.COMPSI, "Milky Way Galaxy", "(302)-399-8212", "vvazir@udel.edu"));
		students.add(new Student("John", "N", "Doe", new Date(1997,7,28), eMajor.NURSING, "555 Fake Street", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Jane", "N", "Doe", new Date(1997,7,28), eMajor.NURSING, "555 Fake Street", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Bob", "N", "Marly", new Date(1997,7,28), eMajor.PHYSICS, "University Of Delaware", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Taylor", "N", "Hebert", new Date(1997,7,28), eMajor.COMPSI, "Worm - Web Novel", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Missy", "N", "Brion", new Date(1997,7,28), eMajor.PHYSICS, "Worm - Web Novel", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Lily", "N", "Sabah", new Date(1997,7,28), eMajor.CHEM, "Worm - Web Novel", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Dennis", "N", "Danger", new Date(1997,7,28), eMajor.BUSINESS, "Worm - Web Novel", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Alec", "N", "Regent", new Date(1997,7,28), eMajor.NURSING, "Worm - Web Novel", "(302)-399-8212", "someemail@udel.edu"));
		students.add(new Student("Lisa", "N", "Wilborn", new Date(1997,7,28), eMajor.BUSINESS, "Worm - Web Novel", "(302)-399-8212", "someemail@udel.edu"));
	}
	
	@Test
	public void testEnrollment() {
		double[][] grades={
				{97,87,90,78,91,99,86,67,40,100},//AVG=83.5
				{94,84,93,85,92,98,88,70,78,100},//AVG=88.2
				{88,88,87,90,93,97,87,84,90,100},//AVG=90.4
				{95,90,88,88,94,96,90,40,90,100},//AVG=87.1
				{85,89,97,78,95,95,79,78,78,100},//AVG=87.4
				{90,91,94,91,96,94,80,77,76,100} //AVG=88.9
				};
		int count=0;
		for (Section sec:section){
			int index=0;
			for (Student stu:students){
				enroll.add(new Enrollment(stu.getStudentID(),sec.getSectionID()));
				enroll.get(enroll.size()-1).SetGrade(grades[count][index]);
				index++;
			}
			count++;
		}
	}
	@Test
	public void testGPA(){
		//Formula used for GPA = (AVG of grades /100)*4	
		double[] expectedGPA={3.66,3.52,3.66,3.4,3.74,3.86,3.4,2.77,3.01,4.0};
		double[] actualGPA={0,0,0,0,0,0,0,0,0,0};
		double[][] grades={
				{97,87,90,78,91,99,86,67,40,100},//AVG=83.5
				{94,84,93,85,92,98,88,70,78,100},//AVG=88.2
				{88,88,87,90,93,97,87,84,90,100},//AVG=90.4
				{95,90,88,88,94,96,90,40,90,100},//AVG=87.1
				{85,89,97,78,95,95,79,78,78,100},//AVG=87.4
				{90,91,94,91,96,94,80,77,76,100} //AVG=88.9
				};
		int count=0;
		for (Section sec:section){
			int index=0;
			for (Student stu:students){
				enroll.add(new Enrollment(stu.getStudentID(),sec.getSectionID()));
				enroll.get(enroll.size()-1).SetGrade(grades[count][index]);
				index++;
			}
			count++;
		}
		int index=0;
		for (Student stu:students){
			double sum =0;
			for (Enrollment enr:enroll){
				if (enr.getStudentID()==stu.getStudentID()){
					sum+=enr.getGrade();
				}
			}
			actualGPA[index]=((double)((int)(((sum/6)/100)*4*100)))/100;
			index++;
		}
		for (int i =0;i<10;i++){
			assertEquals(expectedGPA[i],actualGPA[i],.01);
		}	
	}
	@Test
	public void testGradeAverage(){
		double[] expectedAVG={83.5,88.2,90.4,87.1,87.4,88.9};
		double[] actualAVG={0,0,0,0,0,0};
		double[][] grades={
				{97,87,90,78,91,99,86,67,40,100},//AVG=83.5
				{94,84,93,85,92,98,88,70,78,100},//AVG=88.2
				{88,88,87,90,93,97,87,84,90,100},//AVG=90.4
				{95,90,88,88,94,96,90,40,90,100},//AVG=87.1
				{85,89,97,78,95,95,79,78,78,100},//AVG=87.4
				{90,91,94,91,96,94,80,77,76,100} //AVG=88.9
				};
		int count=0;
		for (Section sec:section){
			int index=0;
			for (Student stu:students){
				enroll.add(new Enrollment(stu.getStudentID(),sec.getSectionID()));
				enroll.get(enroll.size()-1).SetGrade(grades[count][index]);
				index++;
			}
			count++;
		}
		int index=0;
		for (Section sec:section){
			double sum =0;
			for (Enrollment enr:enroll){
				if (enr.getSectionID()==sec.getSectionID()){
					sum+=enr.getGrade();
				}
			}
			actualAVG[index]=sum/10;
			index++;
		}
		for (int i =0;i<6;i++){
			assertEquals(expectedAVG[i],actualAVG[i],1);
		}	
	}
	@Test
	public void changeMajor(){
		students.get(0).setMajor(eMajor.PHYSICS);
	}
}