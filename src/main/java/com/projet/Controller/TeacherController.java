package com.projet.Controller;


import com.projet.Model.Teacher;
import com.projet.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id" )
                                                           Integer id) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No teacher with this id!" + id));
        return ResponseEntity.ok().body(teacher);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") Integer id ,
                                                 @Valid @RequestBody Teacher teacherDetails) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No teacher for this id" + id));

        teacher.setLastname(teacherDetails.getLastname());
        teacher.setName(teacherDetails.getName());
        teacher.setSection(teacherDetails.getSection());
        teacher.setSpecialty(teacherDetails.getSpecialty());

        final Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/teachers/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable Integer id) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No teacher for this id" + id));
        teacherRepository.delete(teacher);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
