package com.faisal_bs23.jasperreportusingjpaspecification.controller;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentDomain;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentFilter;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentGroupBy;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.service.StudentService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class StudentReportController implements StudentReportApi {

  private final StudentService studentService;

  public StudentReportController(StudentService studentService) {
    this.studentService = studentService;
  }

  @Override
  public ResponseEntity<List<StudentDomain>> getAllStudentList(
      String year, String semester, String section, Integer creditCompleted) {
    return ResponseEntity.ok(studentService.getStudents(year, semester, section, creditCompleted));
  }

  @Override
  public ResponseEntity<Void> exportAllStudentReport(
      String year, String semester, String section, Integer creditCompleted, ExportType exportType, HttpServletResponse response) throws IOException, JRException {
    studentService.exportStudentReport(year, semester, section, creditCompleted, exportType, response);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> exportStudentGroupByReport(
      List<StudentGroupBy> groupBy, String year, String semester, String section, String cgpa, ExportType exportType, HttpServletResponse response) throws IOException, JRException {
    var filter = new StudentFilter(year, semester, section, cgpa, exportType, groupBy);
    studentService.exportStudentGroupByReport(filter, response);
    return ResponseEntity.ok().build();
  }
}
