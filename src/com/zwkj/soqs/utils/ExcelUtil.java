package com.zwkj.soqs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
		String teacherId = "";
		String teacherName = "";
		String year = "";
		String month = "";
		String salary = "";
		
		try {
			poifsFileSystem = new POIFSFileSystem(new FileInputStream(path));
			wb = new HSSFWorkbook(poifsFileSystem);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(0);
			
			int rowNum = sheet.getPhysicalNumberOfRows(); //行数
			//int colNum = row.getPhysicalNumberOfCells();  //列数
			
			for(int i=1;i<rowNum;i++){  //从第二行开始读取数据
				row = sheet.getRow(i);  //得到该行的数据
				salaryInfo = new SalaryInfo();
				teacherName = getCellFormatValue(row.getCell(0));  
				teacherId = getCellFormatValue(row.getCell(1));
				year = getCellFormatValue(row.getCell(2));
				month = getCellFormatValue(row.getCell(3));
				salary = getCellFormatValue(row.getCell(4));
				salaryInfo.setTeacherId(teacherId);
				salaryInfo.setTeacherName(teacherName);
				salaryInfo.setYear(year);
				salaryInfo.setMonth(month);
				salaryInfo.setSalary(salary);
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
			
			for(int i=1;i<rowNum;i++){  //从第二行开始读取数据
				row = sheet.getRow(i);  //得到该行的数据
				teacherInfo = new TeacherInfo();
				teacherName = getCellFormatValue(row.getCell(0));     //1列  姓名   
				teacherId = getCellFormatValue(row.getCell(1));       //2列  身份证
				queryPassword = getCellFormatValue(row.getCell(2));   //3列 查询密码
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
