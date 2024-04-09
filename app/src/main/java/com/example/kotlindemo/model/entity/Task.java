package com.example.kotlindemo.model.entity;

import java.io.Serializable;

/**
 * Create by luweihong on 2018/7/13
 */
public class Task implements Serializable {

    /**
     * jobid : 0qYUDXdB1GmvG1SaysE92RFVGofWQ4
     * jobname : 6月15日语文课中练习
     * completion : 85
     */

    private String jobid;
    private String jobname;
    private float completion;
    private String type;//标志是不是试题
    private String state;//当前状态，未开始，进行中，已结束
    private long start_time;
    private long end_time;
    private long create_time;
    private int fnum;//完成人数
    private int unum;//未完成人数u
    public int total;//下发总人数
    private int total_score;//总分
    private int unCorrectNum;//未批改人数
    private int correctNum;//已批改人数
    private int unfinishNum;//没用到
    private String periodType;//任务类型（课前、课中、课后）
    private String publishAnswerState;//公布答案状态:s-交卷后公布,e-结束后公布(根据结束时间来)

    public String classId; //后加
    public String testBookPageNum;
    public String data_id;

    public String subjectName;
    public String interType;

    public int getUnfinishNum() {
        return unfinishNum;
    }

    public void setUnfinishNum(int unfinishNum) {
        this.unfinishNum = unfinishNum;
    }

    public int getUnCorrectNum() {
        return unCorrectNum;
    }

    public void setUnCorrectNum(int unCorrectNum) {
        this.unCorrectNum = unCorrectNum;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getFnum() {
        return fnum;
    }

    public void setFnum(int fnum) {
        this.fnum = fnum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public float getCompletion() {
        return completion;
    }

    public void setCompletion(float completion) {
        this.completion = completion;
    }

    public void setPeriodType(String periodType){
        this.periodType = periodType;
    }

    public String getPeriodType(){
        return periodType;
    }

    public String getPublishAnswerState() {
        return publishAnswerState;
    }

    public void setPublishAnswerState(String publishAnswerState) {
        this.publishAnswerState = publishAnswerState;
    }

    public int getUnum() {
        return unum;
    }

    public void setUnum(int unum) {
        this.unum = unum;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }
}
