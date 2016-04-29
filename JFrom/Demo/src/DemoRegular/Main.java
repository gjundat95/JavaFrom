package DemoRegular;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		String st = "http:\\/\\/localhost\\/woores\\/wp-content\\/uploads\\/2016\\/02\\/receita-soft-drink-ruella-001.jpg";
		System.out.println(st);
		st = st.replaceAll("localhost", "10.0.3.2");
		System.out.println(st);

	}
	

}
