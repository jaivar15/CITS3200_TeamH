package file;
/*
 * try to catch system shout down
 * soft ware shut down
 * and before it crash save system data;
 * 
 */
public class shutDownHooker{
	serialization a;
		
	public shutDownHooker(serialization a) {
		this.a = a;
	}
	
	public void doShutDownWork(){
		Runtime run=Runtime.getRuntime();
		run.addShutdownHook(new Thread(){
			public void run() {
		           try {
		        	   // system quieting process before
		        	   a.SerializeObject();
		        	   System.out.println("process complete, system able to shutdown");
		           } catch (SecurityException  e) {
		        	   e.printStackTrace();
		           }catch(IllegalStateException e) {
		        	   e.printStackTrace();
		           }catch(IllegalArgumentException e) {
		        	   e.printStackTrace();
		           }
		       }
		});
	}
}