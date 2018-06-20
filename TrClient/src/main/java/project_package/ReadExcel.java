package project_package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReadExcel {
	
	public static void main(String arg[]) throws FileNotFoundException {
		File fle= new File("C:\\Eclispse workspace\\TrClient\\src\\main\\resources\\Input\\trclientdatainput.xls");
		FileInputStream src	=new FileInputStream(fle);
		XSSworkbook wb=new xssWorkbook(src);
	}

}
