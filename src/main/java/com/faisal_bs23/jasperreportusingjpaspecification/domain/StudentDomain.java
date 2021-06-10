package com.faisal_bs23.jasperreportusingjpaspecification.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Student repone")
public class StudentDomain {
  private String studentId;
  private String studentName;
  private String admissionYear;
  private String currentSemester;
  private String currentSection;
  private String currentCgpa;
  private Integer creditCompleted;
  private Integer studentCount;
}
