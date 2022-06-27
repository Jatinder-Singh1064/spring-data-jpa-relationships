package com.learning.spring.subject;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.learning.spring.student.Student;
import com.learning.spring.teacher.Teacher;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String name;
	
	@ManyToMany
	@JoinTable(
				name="students_enrolled",
				joinColumns = @JoinColumn(name = "subject_id"),
				inverseJoinColumns = @JoinColumn(name = "student_id")
			)
	private Set<Student> enrolledStudents = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
	
	public Teacher getTeacher() {
		return teacher;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void enrollStudent(Student student) {
		enrolledStudents.add(student);	
	}

	public void assignTeacher(Teacher teacher) {
		this.teacher = teacher;	
	}

}
