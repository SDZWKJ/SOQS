package com.zwkj.soqs.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="salaryInfo")
@Table(name="SALARY_INFO")
public class SalaryInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;                   //自增主键
	private String teacherId;         //省份证ID
	private String teacherName;       //姓名
	private String year;              //年份
	private String Month;             //月份
	private String salary;            //工资金额
	private String creator;           //录入人
	private Date createdDate;         //创建时间
	private String updater;           //更新人
	private String lastUpdatedDate;   //最后修改时间
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="TEACHER_ID")
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	@Column(name="TEACHER_NAME")
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	@Column(name="YEAR")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="MONTH")
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	@Column(name="SALARY")
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Column(name="CREATOR")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Column(name="CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="UPDATER")
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	@Column(name="LAST_UPDATED_DATE")
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
