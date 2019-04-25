package com.ossp1.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ossp1.example.model.Student;
import com.ossp1.example.service.StudentService;

@RestController
public class StudentController {

	public static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents()
    {
		logger.info("Fetching Students");
		List<Student> students = studentService.findAll();
		if (students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(
    		@PathVariable("id") int id)
    {
		logger.info("Fetching Student with id {}", id);
		Student student = studentService.findById(id);
		if (student == null) {
			logger.error("Student with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(
			@RequestBody Student student) {
		logger.info("Creating Student : {}", student);

		if (studentService.isStudentExist(student)) {
			logger.error("Unable to create. A Student with name {} already exist", student.getName());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		studentService.save(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStudent(
			@PathVariable("id") int id,
			@RequestBody Student student) {
		logger.info("Updating Student with id {}", id);

		Student currentStudent = studentService.findById(id);

		if (currentStudent == null) {
			logger.error("Unable to update. Student with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		currentStudent.setName(student.getName());
		currentStudent.setEmail(student.getEmail());

		studentService.update(currentStudent);
		return new ResponseEntity<>(currentStudent, HttpStatus.OK);
	}

	@RequestMapping(value = "/students", method = RequestMethod.DELETE)
	public ResponseEntity<Student> deleteAll() {
		logger.info("Deleting All Students");

		studentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStudent(
			@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Student with id {}", id);

		Student student = studentService.findById(id);
		if (student == null) {
			logger.error("Unable to delete. Student with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		studentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
