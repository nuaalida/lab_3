package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class ImageUploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private String type = null;
	private String imagename = null;
	private String imagedata = null;
	
	public String upload()
	{
		int result = 1;
		
		String destDir = null;
		if (type.equals("user")) {
			destDir = "I:\\blank\\userimage\\";
		}
		else {
			destDir = "I:\\blank\\goodimage\\";
		}
		
		String[] array = imagename.split("\\.");
		String fileType = array[array.length-1];
		
	    String imagepath = uploadUtil(fileType, imagedata, destDir);
	    if (imagepath == null) {
	    	result = 0;
	    }
	    
	    dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("imagepath", imagepath);
	    
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
		
		String newFilePath = dirPath+newFileName+"."+fileType;
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
					return newFileName+"."+fileType;
				}else
					out.write(buffer,0,ins);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImagedata() {
		return imagedata;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
}
