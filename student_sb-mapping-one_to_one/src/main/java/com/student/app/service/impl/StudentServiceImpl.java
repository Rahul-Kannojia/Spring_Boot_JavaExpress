package com.student.app.service.impl;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.app.dtos.StudentDto;
import com.student.app.exception.ResourceNotFoundException;
import com.student.app.model.Address;
import com.student.app.model.Student;
import com.student.app.repository.AddressRepository;
import com.student.app.repository.StudentRepository;
import com.student.app.service.IStudentService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
/**
 * Constructor Injection
 */
//@AllArgsConstructor 
public class StudentServiceImpl implements IStudentService {

	/**
	 * Setter Injection
	 */
	private StudentRepository studentRepository;

	private AddressRepository addressRepository;

	private ModelMapper modelMapper;

	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Autowired
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public String createStudent(StudentDto studentDto) {
		log.info("StudentServiceImpl ::createStudent:: {} ", studentDto.getStudentName());
		Student student = new Student();
		student.setStudentName(studentDto.getStudentName());
		student.setDepartment(studentDto.getDepartment());
		student.setStudentIdCardNumber(new Random().nextLong(100000));
		modelMapper.map(student, studentDto);
		Address address = addressRepository.findByMobileNumber(studentDto.getAddressDto().getMobileNumber()).orElseThrow(()-> new ResourceNotFoundException("Address with given mobile number not exist:"+studentDto.getAddressDto().getMobileNumber()));
		student.setAddress(address);
		studentRepository.save(student);
		log.info("StudentServiceImpl ::createStudent:: Student saved successfully: {} ", studentDto.getStudentName());
		return "Student Created Successfully";
	}

	@Override
	public StudentDto fetchStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto updateStudent(Integer studentId, StudentDto studentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentsByDepartment(String departmentName) {
		// TODO Auto-generated method stub
		return null;
	}

}
