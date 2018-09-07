package file;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;

public class serialization implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String folderDir;
	private String fileName;
	private Object[] obj;
	private String dash;
	
	public serialization(Object[] obj, String folderDir,String fileName) {
		this.obj = obj;
		this.fileName = fileName;
		this.setFolderDir(folderDir);
		dash = "/";
	}
	
	public boolean SerializeObject() {
		 // Serialization 
		FileOutputStream f_out = null;
		try {
			
			File file = new File(folderDir + dash + fileName + ".dat" ); 
			Files.deleteIfExists(file.toPath());
			// delete file is exist
			try {
				Files.deleteIfExists(file.toPath());
	        } catch (DirectoryNotEmptyException e) {
	            return false;
	        }
			
			// Write to disk with FileOutputStream
			f_out = new FileOutputStream(file);

			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
			
			// Write object out to disk
			obj_out.writeObject (obj);
			obj_out.flush();
			obj_out.close();
			f_out.flush();
		    f_out.close();
	        return true;
		}catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
            return false;
        } finally {
            // releases all system resources from the streams
            if(f_out!=null)
				try {
					f_out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         }
	}
	
	public Object getObj() {
		return obj;
	}
	
	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public String getFolderDir() {
		return folderDir;
	}

	public void setFolderDir(String folderDir) {
		this.folderDir = folderDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
