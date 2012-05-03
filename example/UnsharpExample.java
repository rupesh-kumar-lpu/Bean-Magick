import java.io.File;

import com.laukien.bean.magick.Unsharp;
import com.laukien.exception.ExecutionException;

public class UnsharpExample {
	public static void main(String[] args) throws ExecutionException {
		Unsharp unsharp=new Unsharp();
		unsharp.setInputFile(new File("example"+File.separator+"logo.png"));
		unsharp.setOutputFile(new File("example"+File.separator+"new.png"));
		unsharp.setRadius(1);
		unsharp.setSigma(5);
		unsharp.setAmount(2);
		unsharp.setThreshold(1.7F);
		System.out.println(unsharp.getRuntimeCommand());
		unsharp.execute();
		
		if(unsharp.isError()) System.err.println(unsharp.getResult());
	}
}
