package com.example.kotlindemo.model.entity;


import java.io.Serializable;
import java.util.List;

/**
 * Create by luweihong on 2018/7/13
 * http://192.168.9.146/dokuwiki/doku.php?id=yasoon_school:api:onlinenum
 */
public class UserClassBean implements Serializable{


    private List<ClassListBean> classList;
    private List<SubjectListBean> subjectList;

    public List<ClassListBean> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassListBean> classList) {
        this.classList = classList;
    }

    public List<SubjectListBean> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectListBean> subjectList) {
        this.subjectList = subjectList;
    }

    public static class ClassListBean implements Serializable{
        /**
         * gradeName : 初三
         * classId : 0qSb0BrfL2yGmn13KMaTCQAQBS8jA
         * studySection : 2
         * className : 初三(1)班
         */

        private String gradeName;
        private String classId;
        private String classNo;
        private String studySection;
        private String className;
        private String gradeId;

        public ClassListBean(String classId,String className){
            setClassId(classId);
            setClassName(className);
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getStudySection() {
            return studySection;
        }

        public void setStudySection(String studySection) {
            this.studySection = studySection;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getClassNo() {
            return classNo;
        }

        public void setClassNo(String classNo) {
            this.classNo = classNo;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeId() {
            return gradeId;
        }
    }

    public static class SubjectListBean implements Serializable{
        /**
         * subjectname : 语文
         * subjectid : 0qS0VO4V4lxHMvtv8lP6ZUsP52Vm2Z
         */

        private String subjectname;
        private String subjectid;
        private String subjectno;

        public String getSubjectNo() {
            return subjectno;
        }

        public void setSubjectNo(String subjectNo) {
            this.subjectno = subjectNo;
        }

        public String getSubjectname() {
            return subjectname;
        }

        public void setSubjectname(String subjectname) {
            this.subjectname = subjectname;
        }

        public String getSubjectid() {
            return subjectid;
        }

        public void setSubjectid(String subjectid) {
            this.subjectid = subjectid;
        }
    }
}
