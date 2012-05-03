import java.io.File;

import com.laukien.bean.magick.Image;
import com.laukien.bean.magick.Resize;
import com.laukien.exception.ExecutionException;

public class ResizeExample {
	public static void main(String[] args) throws ExecutionException {
		Resize resize=new Resize();
		resize.setInputFile(new File("example"+File.separator+"logo.png"));
		resize.setOutputFile(new File("example"+File.separator+"new.png"));
		resize.setGeometry("300x100");
		resize.setOption(Resize.SMALLER);
		System.out.println(resize.getRuntimeCommand());
		resize.execute();
		
		if(resize.isError()) System.err.println(resize.getResult());
		else {
			System.out.println(resize.getResult());
			Image image=resize.getImage();
			System.out.println("Size: "+image.getSize());
			System.out.println("Depth: "+image.getDepth()+" Bit");
			System.out.println("Geometry: "+image.getWidth()+'x'+image.getHeight()+'+'+image.getLeft()+'+'+image.getTop());
			System.out.println("File: "+image.getFile().getAbsolutePath());
			System.out.println("Name: "+image.getName());
			System.out.println("Extension: "+image.getExtension());
		}
	}
}
