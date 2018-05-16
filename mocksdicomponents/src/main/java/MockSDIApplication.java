
import java.io.IOException;

import com.bc.mocksdicomponents.reader.SimpleFileReader;

public class MockSDIApplication {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		
		SimpleFileReader simpleFileReader = new SimpleFileReader();
		
		simpleFileReader.readFile(args[0]);

	}

}
