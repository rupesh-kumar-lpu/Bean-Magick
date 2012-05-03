import java.io.File;

import com.laukien.exception.ExecutionException;

public class ThumbnailExample {
	public static void main(String[] args) throws ExecutionException {
/*		
		Magick magick=new Magick();
		magick.setInputFile(new File("example"+File.separator+"logo.png"));
		magick.setOutputFile(new File("example"+File.separator+"new.png"));
		magick.setImageIndex(0);
		
		Depth depth=new Depth();
		depth.setValue(8);
		magick.add(depth);
		
		Quality quality=new Quality();
		quality.setValue(75);
		magick.add(quality);
		
		magick.add(new Trim());

		Thumbnail thumbnail=new Thumbnail();
		thumbnail.setGeometry("x120");
		magick.add(thumbnail);
		
		Resize resize=new Resize();
		resize.setGeometry("160x");
		resize.setOption(Resize.SMALLER);
		magick.add(resize);
		
		Gravity gravity=new Gravity();
		gravity.setType(Gravity.CENTER);
		magick.add(gravity);

		Repage repage=new Repage();
		magick.add(repage);

		Crop crop=new Crop();
		crop.setGeometry("160x120");
		crop.setOffset(0,0);
		magick.add(crop);
		
		System.out.println(magick.getRuntimeCommand());
		magick.execute();
		
		System.out.println(magick.getResult());
		
*/
		com.laukien.bean.magick.template.Thumbnail thumbnail=new com.laukien.bean.magick.template.Thumbnail();
		thumbnail.setGeometry(100, 150);
		//thumbnail.setInputFile(new File("example"+File.separator+"logo.png"));
		thumbnail.setInputFile(new File("/home/sl096/Desktop/51343.png"));
		thumbnail.setOutputFile(new File("example"+File.separator+"new.png"));
		
		//Optimal alignment
		thumbnail.setAlignment(com.laukien.bean.magick.template.Thumbnail.ALIGN_AUTO);
		

		//convert option
		thumbnail.setOption(com.laukien.bean.magick.template.Thumbnail.OPTION_HEIGHT);
		
		System.out.println(thumbnail.getRuntimeCommand());
		thumbnail.execute();
		
		System.out.println(thumbnail.getResult());
	}
}