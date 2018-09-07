package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public deserialization(String filePath) {
		this.filePath = filePath;
		data = null;
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

        } catch(FileNotFoundException exception)
	    {
            System.out.println("The file was not found.");
        }catch(IOException exception)
	    {
            System.out.println(exception);
        }
		catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught");
            return false;
        }
		return false; 
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
