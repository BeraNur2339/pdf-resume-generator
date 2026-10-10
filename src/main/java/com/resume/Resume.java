package com.resume;

import java.util.List;

public class Resume {
    private String fullName;
    private String university;
    private String department;
    private String studentNo;
    private String email;
    private String phone;
    private String summary;
    private String photoPath;
    private List<Experience> experiences;

    public Resume(String fullName, String university, String department, String studentNo,
                  String email, String phone, String summary, String photoPath, List<Experience> experiences) {
        this.fullName = fullName;
        this.university = university;
        this.department = department;
        this.studentNo = studentNo;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
        this.photoPath = photoPath;
        this.experiences = experiences;
    }

    public String getFullName() { return fullName; }
    public String getUniversity() { return university; }
    public String getDepartment() { return department; }
    public String getStudentNo() { return studentNo; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getSummary() { return summary; }
    public String getPhotoPath() { return photoPath; }
    public List<Experience> getExperiences() { return experiences; }
}