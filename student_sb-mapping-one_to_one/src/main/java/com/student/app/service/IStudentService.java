package com.student.app.service;

import java.util.List;

import com.student.app.dtos.StudentDto;
import com.student.app.model.Student;

public interface IStudentService {
	
	String createStudent(StudentDto studentDto);
	StudentDto fetchStudent(Integer studentId);
	StudentDto updateStudent(Integer studentId,StudentDto studentDto);
	Boolean deleteStudent(Integer studentId);
	List<Student> getAllStudent();
	List<Student> getStudentsByDepartment(String departmentName);
	
}
