package com.faisal_bs23.jasperreportusingjpaspecification.controller;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentDomain;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentGroupBy;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(value = "student-report", tags = "student-report")
@RequestMapping(path = "/student")
public interface StudentReportApi {

  @ApiOperation(
      value = "Get All Student List",
      nickname = "getAllStudentList",
      notes = "Get All Student List as Json Data",
      tags = {"student-report"}, response = StudentDomain.class)
  @GetMapping("/all")
  public ResponseEntity<List<StudentDomain>> getAllStudentList(
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted);

  @ApiOperation(
      value = "Export All Student List",
      nickname = "exportAllStudentReport",
      notes = "Export (PDF,DOCX,EXCEL,CSV) Report of All Student",
      tags = {"student-report"}, response = StudentDomain.class)
  @GetMapping("/list-report/export")
  public ResponseEntity<Void> exportAllStudentReport(
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted,
      @RequestParam(value = "exportType", required = false) final ExportType exportType,
      HttpServletResponse response) throws IOException, JRException;

  @ApiOperation(
      value = "Export Student Report by Group",
      nickname = "exportStudentGroupByReport",
      notes = "Export (PDF,DOCX,EXCEL,CSV) Student Report by Group",
      tags = {"student-report"}, response = StudentDomain.class)
  @GetMapping("/groupby-report/export")
  public ResponseEntity<Void> exportStudentGroupByReport(
      @RequestParam(value = "groupBy",required = false) final List<StudentGroupBy> groupBy,
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "cgpa", required = false) final String cgpa,
      @RequestParam(value = "exportType", required = false) final ExportType exportType,
      HttpServletResponse response) throws IOException, JRException;

}