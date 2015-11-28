package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class ImageDownloadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String imagepath = null;
	private String type = null;
	private long filesize = 0;
	
	private InputStream targetfile = null;
	
	public String download() {
		System.out.println("download:"+imagepath);
		
		String filedir = null;
		if (type.equals("user")) {
			filedir = "I:\\blank\\userimage\\";
		}
		else {
			filedir = "I:\\blank\\goodimage\\";
		}
		
		try {
			
			File f = new File(filedir+imagepath);
			targetfile = new FileInputStream(f);
			filesize = f.length();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public InputStream getTargetfile() {
		return targetfile;
	}

	public void setTargetfile(InputStream targetfile) {
		this.targetfile = targetfile;
	}

	/**
	 * @return the filesize
	 */
	public long getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
}
