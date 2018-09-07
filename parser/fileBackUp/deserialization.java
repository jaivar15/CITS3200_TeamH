package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class deserialization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String filePath;
	private Object[] data;

	public deserialization(String filePath, Object data[]) {
		this.filePath = filePath;
		this.data = data;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public boolean deserializeObject() {
		try
        {    
            // Reading the object from a file 
            FileInputStream f_in = new FileInputStream(filePath); 
            ObjectInputStream obj_in = new ObjectInputStream(f_in); 
              
            // Method for deserialization of object 
            data = (Object[]) obj_in.readObject(); 
            obj_in.close(); 
            f_in.close();
            return true;

        } 
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
            return false;
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught");
            return false;
        } 
		
	}
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the data
	 */
	public Object[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data[]) {
		this.data = data;
	}

}
