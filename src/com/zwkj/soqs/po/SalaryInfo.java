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
	
	private int id;                       //自增主键
	private String empId;                 //人员编号
	private String teacherName;           //姓名
	private String yfSalary;   		      //应发项
	private String sfSalary;		      //实发工资
	private String jcSalary;              //基础工资
	private String gwSalary;              //岗位工资
	private String xjSalary;              //薪级工资
	private String glSalary;              //工龄工资
	private String tgSalary;              //提高工资
	private String jtSalary;              //津贴工资
	private String qtjbSalary;            //其他基本工资
	private String sydwjtbthjAllowance;   //事业单位津贴补贴合计
	private String zwbtAllowance;         //职务补贴
	private String tgjtAllowance;         //特岗津贴
	private String jxjtAllowance;         //警衔津贴
	private String jhljtAllowance;        //教护龄津贴
	private String bzrAllowance;          //班 主任津贴
	private String ggxbtWybtAllowance;    //改革性补贴二物业补贴
	private String qtbzAllowance;         //其他补助工资
	private String kpAward;               //考评奖
	private String qtSalary;              //其他工资
	private String dsznAllowance;         //独生子女费
	private String bfgzyfSalary;          //补发工资应发额
	private String zfAllowance;           //住房补贴
	private String kfTotal;               //扣发小记
	private String kgjjMoney;             //扣公积金
	private String kyalbxMoney;           //扣养老保险
	private String kyilbxMoney;           //扣医疗保险
	private String dbbzjMoney;            //大病补助金
	private String iitMoney;              //应纳所得税额
	private String kiitMoney;			  //扣所得税
	private String year;                  //年份
	private String month;                 //分月
	private Date dateSalary;              //把年份和月份转换成date类型存储，方便查询
	private String sfjsTax;              //是否计税
	private String creator;               //录入人
	private Date createdDate;             //创建时间
	private String updater;               //更新人
	private String lastUpdatedDate;       //最后修改时间
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="EMP_ID")
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Column(name="TEACHER_NAME")
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	@Column(name="YF_SALARY")
	public String getYfSalary() {
		return yfSalary;
	}
	public void setYfSalary(String yfSalary) {
		this.yfSalary = yfSalary;
	}
	@Column(name="SF_SALARY")
	public String getSfSalary() {
		return sfSalary;
	}
	public void setSfSalary(String sfSalary) {
		this.sfSalary = sfSalary;
	}
	@Column(name="JC_SALARY")
	public String getJcSalary() {
		return jcSalary;
	}
	public void setJcSalary(String jcSalary) {
		this.jcSalary = jcSalary;
	}
	@Column(name="GW_SALARY")
	public String getGwSalary() {
		return gwSalary;
	}
	public void setGwSalary(String gwSalary) {
		this.gwSalary = gwSalary;
	}
	@Column(name="XJ_SALARY")
	public String getXjSalary() {
		return xjSalary;
	}
	public void setXjSalary(String xjSalary) {
		this.xjSalary = xjSalary;
	}
	@Column(name="GL_SALARY")
	public String getGlSalary() {
		return glSalary;
	}
	public void setGlSalary(String glSalary) {
		this.glSalary = glSalary;
	}
	@Column(name="TG_SALARY")
	public String getTgSalary() {
		return tgSalary;
	}
	public void setTgSalary(String tgSalary) {
		this.tgSalary = tgSalary;
	}
	@Column(name="JT_SALARY")
	public String getJtSalary() {
		return jtSalary;
	}
	public void setJtSalary(String jtSalary) {
		this.jtSalary = jtSalary;
	}
	@Column(name="QTJB_SALARY")
	public String getQtjbSalary() {
		return qtjbSalary;
	}
	public void setQtjbSalary(String qtjbSalary) {
		this.qtjbSalary = qtjbSalary;
	}
	@Column(name="SYDWJTBTHJ_ALLOWANCE")
	public String getSydwjtbthjAllowance() {
		return sydwjtbthjAllowance;
	}
	public void setSydwjtbthjAllowance(String sydwjtbthjAllowance) {
		this.sydwjtbthjAllowance = sydwjtbthjAllowance;
	}
	@Column(name="ZWBT_ ALLOWANCE")
	public String getZwbtAllowance() {
		return zwbtAllowance;
	}
	public void setZwbtAllowance(String zwbtAllowance) {
		this.zwbtAllowance = zwbtAllowance;
	}
	@Column(name="TGJT_ ALLOWANCE")
	public String getTgjtAllowance() {
		return tgjtAllowance;
	}
	public void setTgjtAllowance(String tgjtAllowance) {
		this.tgjtAllowance = tgjtAllowance;
	}
	@Column(name="JXJT_ ALLOWANCE")
	public String getJxjtAllowance() {
		return jxjtAllowance;
	}
	public void setJxjtAllowance(String jxjtAllowance) {
		this.jxjtAllowance = jxjtAllowance;
	}
	@Column(name="JHLJT_ ALLOWANCE")
	public String getJhljtAllowance() {
		return jhljtAllowance;
	}
	public void setJhljtAllowance(String jhljtAllowance) {
		this.jhljtAllowance = jhljtAllowance;
	}
	@Column(name="BZR_ ALLOWANCE")
	public String getBzrAllowance() {
		return bzrAllowance;
	}
	public void setBzrAllowance(String bzrAllowance) {
		this.bzrAllowance = bzrAllowance;
	}
	@Column(name="GGXBT_WYBT_ALLOWANCE")
	public String getGgxbtWybtAllowance() {
		return ggxbtWybtAllowance;
	}
	public void setGgxbtWybtAllowance(String ggxbtWybtAllowance) {
		this.ggxbtWybtAllowance = ggxbtWybtAllowance;
	}
	@Column(name="QTBZ_ ALLOWANCE")
	public String getQtbzAllowance() {
		return qtbzAllowance;
	}
	public void setQtbzAllowance(String qtbzAllowance) {
		this.qtbzAllowance = qtbzAllowance;
	}
	@Column(name="KP_ AWARD")
	public String getKpAward() {
		return kpAward;
	}
	public void setKpAward(String kpAward) {
		this.kpAward = kpAward;
	}
	@Column(name="QT_SALARY")
	public String getQtSalary() {
		return qtSalary;
	}
	public void setQtSalary(String qtSalary) {
		this.qtSalary = qtSalary;
	}
	@Column(name="DSZN_ ALLOWANCE")
	public String getDsznAllowance() {
		return dsznAllowance;
	}
	public void setDsznAllowance(String dsznAllowance) {
		this.dsznAllowance = dsznAllowance;
	}
	@Column(name="BFGZYF_SALARY")
	public String getBfgzyfSalary() {
		return bfgzyfSalary;
	}
	public void setBfgzyfSalary(String bfgzyfSalary) {
		this.bfgzyfSalary = bfgzyfSalary;
	}
	@Column(name="ZF_ ALLOWANCE")
	public String getZfAllowance() {
		return zfAllowance;
	}
	public void setZfAllowance(String zfAllowance) {
		this.zfAllowance = zfAllowance;
	}
	@Column(name="KF_TOTAL")
	public String getKfTotal() {
		return kfTotal;
	}
	public void setKfTotal(String kfTotal) {
		this.kfTotal = kfTotal;
	}
	@Column(name="KGJJ_MONEY")
	public String getKgjjMoney() {
		return kgjjMoney;
	}
	public void setKgjjMoney(String kgjjMoney) {
		this.kgjjMoney = kgjjMoney;
	}
	@Column(name="KYALBX_MONEY")
	public String getKyalbxMoney() {
		return kyalbxMoney;
	}
	public void setKyalbxMoney(String kyalbxMoney) {
		this.kyalbxMoney = kyalbxMoney;
	}
	@Column(name="KYILBX_MONEY")
	public String getKyilbxMoney() {
		return kyilbxMoney;
	}
	public void setKyilbxMoney(String kyilbxMoney) {
		this.kyilbxMoney = kyilbxMoney;
	}
	@Column(name="DBBZJ_MONEY")
	public String getDbbzjMoney() {
		return dbbzjMoney;
	}
	public void setDbbzjMoney(String dbbzjMoney) {
		this.dbbzjMoney = dbbzjMoney;
	}
	@Column(name="IIT_MONEY")
	public String getIitMoney() {
		return iitMoney;
	}
	public void setIitMoney(String iitMoney) {
		this.iitMoney = iitMoney;
	}
	@Column(name="KIIT_MONEY")
	public String getKiitMoney() {
		return kiitMoney;
	}
	public void setKiitMoney(String kiitMoney) {
		this.kiitMoney = kiitMoney;
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
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name="DATE_SALARY")
	public Date getDateSalary() {
		return dateSalary;
	}
	public void setDateSalary(Date dateSalary) {
		this.dateSalary = dateSalary;
	}
	@Column(name="SFJS_TAX")
	public String getSfjsTax() {
		return sfjsTax;
	}
	public void setSfjsTax(String sfjsTax) {
		this.sfjsTax = sfjsTax;
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
