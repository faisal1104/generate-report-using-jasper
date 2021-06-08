package com.faisal_bs23.jasperreportusingjpaspecification.domain;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.dynamic.DynamicReportProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.faisal_bs23.jasperreportusingjpaspecification.util.Constant.EXTENSION_JRXML;
import static com.faisal_bs23.jasperreportusingjpaspecification.util.Constant.FOLDER_PATH_REPORT_DYNAMIC;

@Data@NoArgsConstructor
public class StudentFilter {
  private String studentId;
  private String studentName;
  private String admissionYear;
  private String currentSemester;
  private String currentSection;
  private String currentCgpa;
  private Integer creditCompleted;
  private ExportType exportType;

  public StudentFilter(String admissionYear, String currentSemester, String currentSection, Integer creditCompleted) {
    this.admissionYear = admissionYear;
    this.currentSemester = currentSemester;
    this.currentSection = currentSection;
    this.creditCompleted = creditCompleted;
  }

  public InputStream getInputStream() {
    var name =
        FOLDER_PATH_REPORT_DYNAMIC
            + exportType.toString().toLowerCase()
            + EXTENSION_JRXML;
    return getClass().getResourceAsStream(name);
  }

  public String getReportTitle() {
    return "Student List";
  }

  public DynamicReportProperties generateDynamicColumnAndRows(List<StudentDomain> list) {

    List<String> columnHeaders = new ArrayList<>();
    List<Integer> indexesOfColumnTypeNumber = new ArrayList<>();
    List<List<String>> rows = new ArrayList<>();
    List<String> summary = new ArrayList<>();

    // dynamic

    // static
    columnHeaders.add("Student Id");
    columnHeaders.add("Student Name");
    columnHeaders.add("Admission Year");
    columnHeaders.add("Current Semester");
    columnHeaders.add("Current Section");
    columnHeaders.add("Current CGPA");
    columnHeaders.add("Credit Completed");


    for (var s : list) {
      List<String> row = new ArrayList<>();
      row.add(s.getStudentId());
      row.add(s.getStudentName());
      row.add(s.getAdmissionYear());
      row.add(s.getCurrentSemester());
      row.add(s.getCurrentSection());
      row.add(s.getCurrentCgpa());
      row.add(String.valueOf(s.getCreditCompleted()));

      rows.add(row);
    }

    // summary
    summary.add("Total Student");
    summary.add(String.valueOf(list.size()));

    // number field index list
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 2); // "Total"
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 1); // countNumber

    return new DynamicReportProperties(columnHeaders, indexesOfColumnTypeNumber, rows, summary);
  }
}
