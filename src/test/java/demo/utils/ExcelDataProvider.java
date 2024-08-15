package demo.utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] excelData() {
        String fileLocation = System.getProperty("user.dir")+"/src/test/resources/data.xlsx";
        System.out.println("Fetching excel file from "+fileLocation);
        return ExcelReaderUtil.readExcelData(fileLocation);
    }
    public static void main(String[] args){
        Object[][] excelData = excelData();

        for (Object[] objArr : excelData){
            for(Object obj : objArr){
                System.out.println("Test - " + obj);
            }
        }


    }
}