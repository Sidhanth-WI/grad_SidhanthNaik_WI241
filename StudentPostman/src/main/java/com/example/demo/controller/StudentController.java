package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDAO;
import com.example.demo.model.Student;

@RestController
public class StudentController {
	
	@Autowired
	StudentDAO studao;
	
	@GetMapping("/")
	public String home() {
		return "<h2>WELCOME TO THE STUDENT PORTAL</h2>";
	}
	
	
	
	@GetMapping("/students")//get all students
	public List<Student> all()
	{
		return studao.findAll();
	}
	
	@PostMapping("/students")//insert a student record
	public String insertStudent(@RequestBody Student s)
	{
		if (studao.existsById(s.getRegno()))
			return "Sorry Student already exists by this regno";
		studao.save(s);
		return "Successfully added Student";
	}
	
	@GetMapping("/students/{regno}")//Specific student details for given Registration number
	public Optional<Student> getStudentRegno(@PathVariable int regno)
	{
		return (studao.findById(regno));
	}
	


	@PutMapping("/students/{regno}")//Update specific student record
	public String putStudent(@PathVariable int regno, @RequestBody Student s)
	{
		if (regno!=s.getRegno())
			return "Reg numbers do not match";
		if(!studao.existsById(regno))
			return "Student does not exist";
			
		studao.save(s);
		return "Student doesn't exist";
	}
	
	@PatchMapping("/students/{regno}")//Update with given attributes
	public String patchStudent(@PathVariable int regno, @RequestBody Student s)
	{
		if (regno!=s.getRegno())
			return "Reg numbers do not match";
		if(!studao.existsById(regno))
			return "Student does not exist";
		
		Optional<Student> s1=studao.findById(regno);
		Student stud = s1.get();
		if(s.getGender()!=null && stud != null)
		{
			stud.setGender(s.getGender());
		}
		if(s.getName()!=null && stud != null)
		{
			stud.setName(s.getName());
		}
		if(s.getPercentage()!=0 && stud != null)
		{
			stud.setPercentage(s.getPercentage());
		}
		if(s.getRegno()!=0 && stud != null)
		{
			stud.setRegno(s.getRegno());
		}
		if(s.getRollno()!=0 && stud != null)
		{
			stud.setRollno(s.getRollno());
		}
		if(s.getSchool()!=null && stud != null)
		{
			stud.setSchool(s.getSchool());
		}
		if(s.getStandard()!=0 && stud != null)
		{
			stud.setStandard(s.getStandard());
		}
		studao.save(stud);
		return "Patch Successful";
		
		
	}
	
	@DeleteMapping("/students/{regno}")//Remove the student record for the given Registration number
	public String removeStudent(@PathVariable int regno)
	{
		if(studao.existsById(regno))
		{
			studao.deleteById(regno);
			return "Student deleted successfully";
		}
		
		return "Student does not exist";
		
	}
	
	@GetMapping("/students/school")//List all students belonging to that school
	public List<Student> sameSchoolStudents(@RequestParam("name") String school){
		return studao.findBySchool(school);
	
	}
//	
	@GetMapping("/students/school/count")
	public int schoolStudentCount(@RequestParam("name") String school)
	{
		return studao.countBySchool(school);
	}
//	
	@GetMapping("/students/school/standard/count")
	public int standardCount(@RequestParam("class") int standard)
	{
		return studao.countByStandard(standard);
	}
//	
	@GetMapping("/students/result")
	public List<Student> studentPercentageList(@RequestParam("pass") boolean pass)
	{
		if(pass)
		{
			return studao.findByPercentageGreaterThanEqualOrderByPercentageDesc(40);
		}
		else
			return studao.findByPercentageLessThanOrderByPercentageDesc(40);
	}
//	
	@GetMapping("/students/strength")
	public List<Student> studentGenderStandard(@RequestParam("gender") String gender, @RequestParam("standard") int standard)
	{
		return studao.findByGenderAndStandard(gender, standard);
	}
	
	
	

}
