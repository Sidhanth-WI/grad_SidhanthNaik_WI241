package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

public interface StudentDAO extends JpaRepository<Student,Integer>{
	List<Student> findBySchool(String school);
	int countBySchool(String school);
	int countByStandard(int standard);
	List<Student> findByPercentageGreaterThanEqualOrderByPercentageDesc(long Percentage);
	List<Student>findByPercentageLessThanOrderByPercentageDesc(long Percentage);
	List<Student>findByGenderAndStandard(String gender,int standard);

}
