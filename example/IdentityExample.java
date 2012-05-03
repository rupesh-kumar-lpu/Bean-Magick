import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.laukien.bean.magick.Identify;
import com.laukien.exception.ExecutionException;

public class IdentityExample {
	public static void main(String[] args) throws ExecutionException {

		Identify identify=new Identify();
		System.out.println("Name: "+identify.getName());
		System.out.println("Version: "+identify.getVersion());
		System.out.println("Release: "+identify.getRelease());

		Vector formats=identify.getFormats();
		System.out.println("Formats:");
		for(int i=0; i<formats.size(); i++) System.out.println(((Identify.Format)formats.get(i)).toString());
		System.out.println("\n");

		Vector types=identify.getTypes();
		System.out.println("Types:");
		for(int i=0; i<types.size(); i++) System.out.println(((Identify.Type)types.get(i)).toString());
		System.out.println("\n");

		Vector colors=identify.getColors();
		System.out.println("Colors:");
		for(int i=0; i<colors.size(); i++) System.out.println(((Identify.Color)colors.get(i)).toString());
		System.out.println("\n");

		Hashtable image=identify.getProperties(new File("example"+File.separator+"logo.png"));
		System.out.println("Properties:");
		Enumeration keys=image.keys();
		String key;
		while(keys.hasMoreElements()) {
			key=(String)keys.nextElement();
			System.out.println(key+": "+image.get(key));
		}
		System.out.println("\n");
	}
}
