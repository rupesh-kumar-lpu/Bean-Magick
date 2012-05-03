import java.io.File;

import com.laukien.bean.magick.SepiaTone;
import com.laukien.exception.ExecutionException;

public class SepiaToneExample {
	public static void main(String[] args) throws ExecutionException {
		SepiaTone sepia=new SepiaTone();
		sepia.setInputFile(new File("example"+File.separator+"logo.png"));
		sepia.setOutputFile(new File("example"+File.separator+"new.png"));
		sepia.setPercentage(80);
		System.out.println(sepia.getRuntimeCommand());
		sepia.execute();
		
		if(sepia.isError()) System.err.println(sepia.getResult());
	}
}
