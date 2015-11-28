package action;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "I:\\blank\\userimage\\image.jpg";
		String[] array = fileName.split("\\\\|\\.");
		String fileType = array[array.length-1];
		System.out.println(fileType);
		System.out.println(array[array.length-2]);
	}

}
