import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.laukien.bean.magick.Blur;
import com.laukien.bean.magick.Config;
import com.laukien.bean.magick.Image;
import com.laukien.bean.magick.Magick;
import com.laukien.bean.magick.Resize;
import com.laukien.exception.ExecutionException;

public class MagickExample {
	public static void main(String[] args) throws ExecutionException, FileNotFoundException, IOException {

		//load an alternative configuration ' cause the resizing needs more time
		Config.load(new File("example"+File.separator+"config.properties"));

		Magick magick=new Magick();
		magick.setInputFile(new File("example"+File.separator+"logo.png"));
		magick.setOutputFile(new File("example"+File.separator+"new.png"));

		Resize resize=new Resize();
		resize.setGeometry("300x300");
		
		Blur blur=new Blur();
		blur.setRadius(30);
		
		//Image information
		Image img=magick.getImage();
		System.out.println("Size: "+img.getWidth()+"x"+img.getHeight());
		
		magick.add(resize);
		magick.add(blur);
		
		System.out.println(magick.getRuntimeCommand());
		magick.execute();

		if(magick.isError()) System.err.println("Error!\n"+magick.getResult());
		else System.out.println(magick.getResult());
	}
}
