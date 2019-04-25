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
	
		Student student1 = new Student(1, "Lisa", "lisa@gmail.com");
		Student student2 = new Student(2, "John", "john@gmail.com");
		Student student3 = new Student(3, "Amy", "amy@naver.com");
	
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
	
	public void update(Student student) {
		students.get(student.getId()).setName(student.getName());
		students.get(student.getId()).setEmail(student.getEmail());
	}
	
	public void deleteAll() {
		students.clear();
	}
	
	public void deleteById(int id) {
		for (Iterator<Student> iterator = students.values().iterator(); iterator.hasNext(); ) {
		    Student student = iterator.next();
		    if (student.getId() == id) {
		        iterator.remove();
		    }
		}
	}
	
	public boolean isStudentExist(Student student) {
		if(findById(student.getId()) != null)
			return true;
		return false;
	}
}
