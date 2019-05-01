package com.ossp1.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ossp1.example.model.Student;

@Service
public class StudentService {
	
	private Map<Integer, Student> students = new HashMap<Integer, Student>();
	{
		
		Student student1 = new Student();
		student1.setId(1);
		student1.setName("Lisa");
		student1.setEmail("lisa@gmail.com");
		
		Student student2 = new Student();
		student2.setId(2);
		student2.setName("John");
		student2.setEmail("john@gmail.com");
		
		Student student3 = new Student();
		student3.setId(3);
		student3.setName("Amy");
		student3.setEmail("amy@naver.com");
	
		students.put(student1.getId(), student1);
		students.put(student2.getId(), student2);
		students.put(student3.getId(), student3);
	}
	
	public List<Student> findAll() {
		List<Student> studentList = new ArrayList<Student>(students.values());
		return studentList;
	}
	
	public Student findById(int id) {
		return students.get(id);
	}
	
	public void save(Student student) {
		students.put(student.getId(), student);
	}
	
	//public void update(Student student) {
	
	//public void deleteAll() {
	
	//public void deleteById(int id) {
	
	public boolean isStudentExist(Student student) {
		if(findById(student.getId()) != null)
			return true;
		return false;
	}
}
