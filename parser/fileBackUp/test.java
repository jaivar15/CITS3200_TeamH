package file;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void ser() {
		Object[] data = {"days of life","number of days", "std",1};
		serialization ser = new serialization(data, "C:\\Users\\y2434\\Desktop","test");
		ser.SerializeObject();
	}
	
	public static void deser() {
		Object[] o = null;
		deserialization deser = new deserialization("C:\\Users\\y2434\\Desktop\\test.dat");
		deser.deserializeObject();
		o = deser.getData();
		if( o == null) {
			System.out.println("error");
		}
		for(int i = 0; i< o.length; i++) {
			System.out.println(o[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		deser();
		//ser();
	}

}
