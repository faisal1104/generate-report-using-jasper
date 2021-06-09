package com.faisal_bs23.jasperreportusingjpaspecification.controller;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentDomain;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentFilter;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentGroupBy;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.service.StudentService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<StudentDomain>> getAllStudents(
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted){
    return ResponseEntity.ok(studentService.getStudents(year, semester, section, creditCompleted));
  }

  @GetMapping("/list-report/export")
  public ResponseEntity<Void> exportStudentReport(
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted,
      @RequestParam(value = "exportType", required = false) final ExportType exportType,
      HttpServletResponse response) throws IOException, JRException {
    studentService.exportStudentReport(year, semester, section, creditCompleted,exportType, response);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/groupby-report/export")
  public ResponseEntity<Void> exportStudentGroupByReport(
      @RequestParam(value = "groupBy",required = false) final List<StudentGroupBy> groupBy,
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "cgpa", required = false) final String cgpa,
      @RequestParam(value = "exportType", required = false) final ExportType exportType,
      HttpServletResponse response) throws IOException, JRException {
    var filter = new StudentFilter(year,semester,section, cgpa, exportType, groupBy);
    studentService.exportStudentGroupByReport(filter, response);
    return ResponseEntity.ok().build();
  }
}