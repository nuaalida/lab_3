package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class ImageUploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String type = null;
	private String fileName = null;
	private String fileData = null;
	
	public String upload()
	{
		String destDir = null;
		if (type.equals("user")) {
			destDir = "I:\\blank\\userimage\\";
		}
		else {
			destDir = "I:\\blank\\goodimage\\";
		}
		
		String[] array = fileName.split("\\.");
		String fileType = array[array.length-1];
		
	    uploadUtil(fileType, fileData, destDir);

	    return SUCCESS;
	}
	
	private String uploadUtil(String fileType,String fileData,String destDir){
		
		Date date = new Date();
		String newFileName = new SimpleDateFormat("yyyyMMddHHmmss_SSS").format(date);
		
		String dirPath = destDir;
		File dir = new File(dirPath);
		if(!dir.exists()  && !dir.isDirectory()){
			dir.mkdir();
		}
		
		String newFilePath = dirPath+"/"+newFileName+"."+fileType;
		FileInputStream in;
		try {
			in = new FileInputStream(new File(fileData));
			FileOutputStream out=new FileOutputStream(newFilePath);
			int length=2097152;
			byte[] buffer=new byte[length];
			while(true){
				int ins=in.read(buffer);
				if(ins==-1){
					in.close();
					out.flush();
					out.close();
					return newFilePath;
				}else
					out.write(buffer,0,ins);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
