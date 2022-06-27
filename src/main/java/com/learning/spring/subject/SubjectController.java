package com.learning.spring.subject;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.student.Student;
import com.learning.spring.student.StudentRepository;
import com.learning.spring.teacher.Teacher;
import com.learning.spring.teacher.TeacherRepository;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TeacherRepository teacherRepository;

	@GetMapping
	List<Subject> getSubjects(){
		return subjectRepository.findAll();
	}
	
	@PostMapping
	Subject createSubject(@RequestBody Subject subject) {
		return subjectRepository.save(subject);
	}
	
	@PutMapping("/{subjectId}/students/{studentId}")
	Subject enrollStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId) {
		Subject subject = subjectRepository.findById(subjectId).get();
		Student student = studentRepository.findById(studentId).get();
		subject.enrollStudent(student);
		return subjectRepository.save(subject);
	}
	
	@PutMapping("/{subjectId}/teachers/{teacherId}")
	Subject assignTeacherToSubject(@PathVariable Long subjectId, @PathVariable Long teacherId) {
		Subject subject = subjectRepository.findById(subjectId).get();
		Teacher teacher = teacherRepository.findById(teacherId).get();
		subject.assignTeacher(teacher);
		return subjectRepository.save(subject);
	}
	

}
