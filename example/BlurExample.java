import java.io.File;

import com.laukien.bean.magick.Blur;
import com.laukien.exception.ExecutionException;

public class BlurExample {
	public static void main(String[] args) throws ExecutionException {
		Blur blur=new Blur();
		blur.setInputFile(new File("example"+File.separator+"logo.png"));
		blur.setOutputFile(new File("example"+File.separator+"new.png"));
		blur.setRadius(10);
		System.out.println(blur.getRuntimeCommand());
		blur.execute();
		
		if(blur.isError()) System.err.println(blur.getResult());
	}
}
