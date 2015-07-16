package com.zwkj.soqs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.zwkj.soqs.po.SalaryInfo;
import com.zwkj.soqs.po.TeacherInfo;

public class ExcelUtil {
	//工资信息
	public List<SalaryInfo> readSalaryExcel(String path){
		List<SalaryInfo> list = new ArrayList<SalaryInfo>();
		POIFSFileSystem poifsFileSystem = null;
		HSSFWorkbook wb = null;                  //excel
		HSSFSheet sheet = null;                  //工作簿
		HSSFRow row = null;                      //行
		//HSSFCell cell = null;                    //单元格
		//String cValue = "";                      //单元格的值
		//--------------------------------
		SalaryInfo salaryInfo = null;
		String empId = "";                 //人员编号
		String teacherName = "";           //姓名
		String yfSalary = "";   		   //应发项
	    String sfSalary = "";		       //实发工资
		String jcSalary = "";              //基础工资
	    String gwSalary = "";              //岗位工资
		String xjSalary = "";              //薪级工资
		String glSalary = "";              //工龄工资
		String tgSalary = "";              //提高工资
		String jtSalary = "";              //津贴工资
		String qtjbSalary = "";            //其他基本工资
		String sydwjtbthjAllowance = "";   //事业单位津贴补贴合计
		String zwbtAllowance = "";         //职务补贴
		String tgjtAllowance = "";         //特岗津贴
		String jxjtAllowance = "";         //警衔津贴
		String jhljtAllowance = "";        //教护龄津贴
		String bzrAllowance = "";          //班 主任津贴
		String ggxbtWybtAllowance = "";    //改革性补贴二物业补贴
		String qtbzAllowance = "";         //其他补助工资
		String kpAward = "";               //考评奖
		String qtSalary = "";              //其他工资
		String dsznAllowance = "";         //独生子女费
		String bfgzyfSalary = "";          //补发工资应发额
		String zfAllowance = "";           //住房补贴
		String kfTotal = "";               //扣发小记
		String kgjjMoney = "";             //扣公积金
		String kyalbxMoney = "";           //扣养老保险
		String kyilbxMoney = "";           //扣医疗保险
		String dbbzjMoney = "";            //大病补助金
		String iitMoney = "";              //应纳所得税额
		String kiitMoney = "";			   //扣所得税
		String year = "";                  //年份
		String month = "";                 //分月
		Date dateSalary = null;            //把年份和月份转换成date类型存储，方便查询
		String  sfjsTax = "";              //是否计税
		try {
			poifsFileSystem = new POIFSFileSystem(new FileInputStream(path));
			wb = new HSSFWorkbook(poifsFileSystem);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(0);
			
			int rowNum = sheet.getPhysicalNumberOfRows(); //行数
			//int colNum = row.getPhysicalNumberOfCells();  //列数
			
			for(int i=3;i<rowNum;i++){  //从第四行开始读取数据
				row = sheet.getRow(i);  //得到该行的数据
				salaryInfo = new SalaryInfo();
				empId = getCellFormatValue(row.getCell(1));
				teacherName = getCellFormatValue(row.getCell(2));
				yfSalary = getCellFormatValue(row.getCell(3));
				sfSalary = getCellFormatValue(row.getCell(4));
				jcSalary = getCellFormatValue(row.getCell(5));
				gwSalary = getCellFormatValue(row.getCell(6));
				xjSalary = getCellFormatValue(row.getCell(7));
				glSalary = getCellFormatValue(row.getCell(8));
				tgSalary = getCellFormatValue(row.getCell(9));
				jtSalary = getCellFormatValue(row.getCell(10));
				qtjbSalary = getCellFormatValue(row.getCell(11));
				sydwjtbthjAllowance = getCellFormatValue(row.getCell(12));
				zwbtAllowance = getCellFormatValue(row.getCell(13));
				tgjtAllowance = getCellFormatValue(row.getCell(14));
				jxjtAllowance = getCellFormatValue(row.getCell(15));
				jhljtAllowance = getCellFormatValue(row.getCell(16));
				bzrAllowance = getCellFormatValue(row.getCell(17));
				ggxbtWybtAllowance = getCellFormatValue(row.getCell(18));
				qtbzAllowance = getCellFormatValue(row.getCell(19));
				kpAward = getCellFormatValue(row.getCell(20));
				qtSalary = getCellFormatValue(row.getCell(21));
				dsznAllowance = getCellFormatValue(row.getCell(22));
				bfgzyfSalary = getCellFormatValue(row.getCell(23));
				zfAllowance = getCellFormatValue(row.getCell(24));
				kfTotal = getCellFormatValue(row.getCell(25));
				kgjjMoney = getCellFormatValue(row.getCell(26));
				kyalbxMoney = getCellFormatValue(row.getCell(27));
				kyilbxMoney = getCellFormatValue(row.getCell(28));
				dbbzjMoney = getCellFormatValue(row.getCell(29));
				iitMoney = getCellFormatValue(row.getCell(30));
				kiitMoney = getCellFormatValue(row.getCell(31));
				year = getCellFormatValue(row.getCell(32));
				month = getCellFormatValue(row.getCell(33));
				dateSalary = null;
				sfjsTax = getCellFormatValue(row.getCell(34));
				salaryInfo.setEmpId(empId);
				salaryInfo.setTeacherName(teacherName);
				salaryInfo.setYfSalary(yfSalary);
				salaryInfo.setSfSalary(sfSalary);
				salaryInfo.setJcSalary(jcSalary);
				salaryInfo.setGwSalary(gwSalary);
				salaryInfo.setXjSalary(xjSalary);
				salaryInfo.setGlSalary(glSalary);
				salaryInfo.setTgSalary(tgSalary);
				salaryInfo.setJtSalary(jtSalary);
				salaryInfo.setQtjbSalary(qtjbSalary);
				salaryInfo.setSydwjtbthjAllowance(sydwjtbthjAllowance);
				salaryInfo.setZwbtAllowance(zwbtAllowance);
				salaryInfo.setTgjtAllowance(tgjtAllowance);
				salaryInfo.setJxjtAllowance(jxjtAllowance);
				salaryInfo.setJhljtAllowance(jhljtAllowance);
				salaryInfo.setBzrAllowance(bzrAllowance);
				salaryInfo.setGgxbtWybtAllowance(ggxbtWybtAllowance);
				salaryInfo.setQtbzAllowance(qtbzAllowance);
				salaryInfo.setKpAward(kpAward);
				salaryInfo.setQtSalary(qtSalary);
				salaryInfo.setDsznAllowance(dsznAllowance);
				salaryInfo.setBfgzyfSalary(bfgzyfSalary);
				salaryInfo.setZfAllowance(zfAllowance);
				salaryInfo.setKfTotal(kfTotal);
				salaryInfo.setKfTotal(kfTotal);
				salaryInfo.setKgjjMoney(kgjjMoney);
				salaryInfo.setKyalbxMoney(kyalbxMoney);
				salaryInfo.setKyilbxMoney(kyilbxMoney);
				salaryInfo.setDbbzjMoney(dbbzjMoney);
				salaryInfo.setIitMoney(iitMoney);
				salaryInfo.setKiitMoney(kiitMoney);
				salaryInfo.setYear(year);
				salaryInfo.setMonth(month);
				salaryInfo.setDateSalary(dateSalary);
				salaryInfo.setSfjsTax(sfjsTax);
				list.add(salaryInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	//用户信息
	public List<TeacherInfo> readUserExcel(String path){
		List<TeacherInfo> list = new ArrayList<TeacherInfo>();
		POIFSFileSystem poifsFileSystem = null;
		HSSFWorkbook wb = null;                  //excel
		HSSFSheet sheet = null;                  //工作簿
		HSSFRow row = null;                      //行
		//HSSFCell cell = null;                    //单元格
		//String cValue = "";                      //单元格的值
		//--------------------------------
		TeacherInfo teacherInfo = null;
		String empId = "";
		String teacherId = "";
		String teacherName = "";
		String queryPassword = "";
		
		try {
			poifsFileSystem = new POIFSFileSystem(new FileInputStream(path));
			wb = new HSSFWorkbook(poifsFileSystem);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(0);
			
			int rowNum = sheet.getPhysicalNumberOfRows(); //行数
			//int colNum = row.getPhysicalNumberOfCells();  //列数
			
			for(int i=2;i<rowNum;i++){  //从第三行开始读取数据
				row = sheet.getRow(i);  //得到该行的数据
				teacherInfo = new TeacherInfo();
				empId = getCellFormatValue(row.getCell(1));           //教职工编号
				teacherName = getCellFormatValue(row.getCell(2));     //1列  姓名   
				teacherId = getCellFormatValue(row.getCell(3));       //2列  身份证
				queryPassword = teacherId.substring(13);              //3列 查询密码,身份证后4位
				teacherInfo.setEmpId(empId);
				teacherInfo.setTeacherId(teacherId);
				teacherInfo.setTeacherName(teacherName);
				teacherInfo.setQueryPassword(queryPassword);
				list.add(teacherInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("deprecation")
	private String getCellFormatValue(HSSFCell cell) {
		String cellValue = "";
		if (cell != null) {
			// 判断cell的Type
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC: //数字和时间类型
				DecimalFormat df = new DecimalFormat("0");
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					// 如果是date，转化为date格式
					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					cellValue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					// Date date = cell.getDateCellValue();
					// SimpleDateFormat sdf = new
					// SimpleDateFormat("yyyy-MM-dd");
					// cellValue = sdf.format(date);
				}else{
					cellValue = df.format(cell.getNumericCellValue());
				}
				break;
			case XSSFCell.CELL_TYPE_STRING: //字符类型
				cellValue = cell.getRichStringCellValue().getString();
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN://布尔类型
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			default:                        //默认情况
				cellValue = "";
			}
		}
		return cellValue;
	}
	
}
