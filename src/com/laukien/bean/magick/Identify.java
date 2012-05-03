package com.laukien.bean.magick;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.laukien.exception.ExecutionException;
import com.laukien.exception.ParameterException;

public class Identify {
	private static Hashtable gsConfigure;
	private static Vector gsFormats;
	private static Vector gsTypes;
	private static Vector gsColors;
	
	static {
		init();
	}
	
	private synchronized static void init() {
		synchronized(Identify.class) {
			gsConfigure=null;
			gsFormats=null;
			gsTypes=null;
			gsColors=null;
		}
	}
	
	public static void reload() {
		init();
	}
	
	public Hashtable getConfigure() throws ExecutionException {
		if(gsConfigure!=null) return gsConfigure;
		
		Hashtable configure=new Hashtable();
		
		Process process;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			process=Runtime.getRuntime().exec(Config.getIdentify()+" -list configure");
			isr=new InputStreamReader(process.getInputStream());
			br=new BufferedReader(isr);
			String line;
			int pos;
			
			//dummy read
			br.readLine();
			br.readLine();
			while((line=br.readLine())!=null) {
				pos=line.indexOf(' ');
				if(pos==-1) continue;
				configure.put(line.substring(0,pos),line.substring(pos+1).trim());
			}
		} catch(IOException e) {
			throw new ExecutionException("Identify.getConfigure: Unable to get the configuration\n"+e);
		} finally {
			try {
				if(br!=null) br.close();
				if(isr!=null) isr.close();
			} catch(Exception e) {
				//do nothing
			}
		}
		
		synchronized(Identify.class) {
			gsConfigure=configure;
		}
		
		return configure;
	}
	
	public String getName() throws ExecutionException {
		return (String)getConfigure().get("NAME");
	}
	
	public String getVersion() throws ExecutionException {
		return (String)getConfigure().get("VERSION");
	}
	
	public String getRelease() throws ExecutionException {
		return (String)getConfigure().get("RELEASE_DATE");
	}
	
	public Vector getFormats() throws ExecutionException {
		if(gsFormats!=null) return gsFormats;
		
		Vector formats=new Vector();
		Process process;
		InputStreamReader isr=null;
		BufferedReader br=null;
		StringTokenizer st=null;
		
		try {
			process=Runtime.getRuntime().exec(Config.getIdentify()+" -list format");
			isr=new InputStreamReader(process.getInputStream());
			br=new BufferedReader(isr);
			String line;
			Format format=null;
			
			//dummy read
			br.readLine();
			br.readLine();
			while((line=br.readLine())!=null) {
				st=new StringTokenizer(line," ");
				if(line.startsWith("         ") || st.countTokens()<4 || line.startsWith("*")) continue;
				
				if(format!=null) formats.add(format);
				format=new Format(st.nextToken(),st.nextToken(),st.nextToken(),line.substring(line.indexOf(st.nextToken())));
			}
		} catch(IOException e) {
			throw new ExecutionException("Identify.getFormats: Unable to get the version\n"+e);
		} finally {
			try {
				if(br!=null) br.close();
				if(isr!=null) isr.close();
			} catch(Exception e) {
				//do nothing
			}
		}
		
		synchronized(Identify.class) {
			gsFormats=formats;
		}

		return formats;
	}
	
	public Vector getTypes() throws ExecutionException {
		if(gsTypes!=null) return gsTypes;
		
		Vector types=new Vector();
		Process process;
		InputStreamReader isr=null;
		BufferedReader br=null;

		try {
			process=Runtime.getRuntime().exec(Config.getIdentify()+" -list type");
			isr=new InputStreamReader(process.getInputStream());
			br=new BufferedReader(isr);
			String line;
			String name, family, style, stretch, weight;
			int idxName, idxStyle=56, idxStretch=64, idxWeight;
			Type type=null;
			
			//dummy read
			br.readLine();
			br.readLine();
			while((line=br.readLine())!=null) {
				idxName=line.indexOf(' ');
				idxWeight=line.lastIndexOf(' ');
				if(idxName<1 || idxName==idxWeight) continue;	//invalid line
				try {
					name=line.substring(0,idxName);
					family=line.substring(idxName,idxStyle).trim();
					style=line.substring(idxStyle,idxStretch).trim();
					stretch=line.substring(idxStretch, idxWeight).trim();
					weight=line.substring(idxWeight+1);
				} catch(Exception e) {
					continue;
				}
				
				type=new Type(name, family, style, stretch, weight);
				if(type.gWeight!=-1) types.add(type);
			}
		} catch(IOException e) {
			throw new ExecutionException("Identify.getTypes: Unable to get the version\n"+e);
		} finally {
			try {
				if(br!=null) br.close();
				if(isr!=null) isr.close();
			} catch(Exception e) {
				//do nothing
			}
		}
		
		synchronized(Identify.class) {
			gsTypes=types;
		}

		return types;
	}
	
	public Vector getColors() throws ExecutionException {
		if(gsColors!=null) return gsColors;
		
		Vector colors=new Vector();
		Process process;
		InputStreamReader isr=null;
		BufferedReader br=null;

		try {
			process=Runtime.getRuntime().exec(Config.getIdentify()+" -list color");
			isr=new InputStreamReader(process.getInputStream());
			br=new BufferedReader(isr);
			String line;
			String name, rgb, compliance;
			int idxName, idxColor=23, idxCompliance=49;
			Color color=null;
			
			//dummy read
			while((line=br.readLine())!=null && (line.length()==0 || line.charAt(0)!='-'));
			
			while((line=br.readLine())!=null) {
				idxName=line.indexOf(' ');
				if(idxName<1) continue;	//invalid line
				try {
					name=line.substring(0,idxName);
					rgb=line.substring(idxColor,idxCompliance-1);
					compliance=line.substring(idxCompliance);
				} catch(Exception e) {
					continue;
				}
				
				color=new Color(name, rgb, compliance);
				colors.add(color);
			}
		} catch(IOException e) {
			throw new ExecutionException("Identify.getTypes: Unable to get the version\n"+e);
		} finally {
			try {
				if(br!=null) br.close();
				if(isr!=null) isr.close();
			} catch(Exception e) {
				//do nothing
			}
		}
		
		synchronized(Identify.class) {
			gsColors=colors;
		}

		return colors;
	}
	
	public Hashtable getProperties(File pInputFile) throws ExecutionException {
		Hashtable properties=new Hashtable();
		Process process;
		InputStreamReader isr=null;
		BufferedReader br=null;

		try {
			process=Runtime.getRuntime().exec(Config.getIdentify()+" -verbose "+pInputFile.getAbsolutePath());
			isr=new InputStreamReader(process.getInputStream());
			br=new BufferedReader(isr);
			String line;
			int idx;
			String value;
			
			//First line
			line=br.readLine();
			if(line==null) throw new RuntimeException("Identify.getProperties: No data from the application");
			if((idx=line.indexOf("Image: "))==-1) throw new ParameterException("Identify.getProperties: No such file or directory.");
			properties.put("Image",line.substring(7));
			
			while((line=br.readLine())!=null) {
				if(line.startsWith("   ") || line.startsWith("0x")) continue;
				
				idx=line.indexOf(':');
				value=line.substring(idx+1);
				value=value.trim();
				if(value.length()==0) continue;
				
				properties.put(line.substring(2,idx).toLowerCase(),value);
			}
		} catch(IOException e) {
			throw new ExecutionException("Identify.getTypes: Unable to get the version\n"+e);
		} finally {
			try {
				if(br!=null) br.close();
				if(isr!=null) isr.close();
			} catch(Exception e) {
				//do nothing
			}
		}
		
		return properties;
	}
	
// Internal Classes \\
	public class Format {
		protected String gName;
		protected String gModule;
		protected String gDescription;
		protected boolean gIsReadable;
		protected boolean gIsWritable;
		protected boolean gIsBlob;		//native blob support
		
		public Format() {
			gName=null;
			gModule=null;
			gIsReadable=false;
			gIsWritable=false;
			gIsBlob=false;
		}

		protected Format(String pName, String pModule, String gMode, String pDescription) {
			if(pName.endsWith("*")) {
				gName=pName.substring(0,pName.length()-1);
				gIsBlob=true;
			} else {
				gName=pName;
				gIsBlob=false;
			}
			
			gModule=pModule;
			
			if(gMode.indexOf('r')!=-1) gIsReadable=true;
			else gIsReadable=false;
			if(gMode.indexOf('w')!=-1) gIsWritable=true;
			else gIsWritable=false;
			
			gDescription=pDescription;
		}
		
		public boolean isBlob() {
			return gIsBlob;
		}

		public boolean isReadable() {
			return gIsReadable;
		}

		public boolean isWritable() {
			return gIsWritable;
		}

		public String getDescription() {
			return gDescription;
		}

		public String getModule() {
			return gModule;
		}

		public String getName() {
			return gName;
		}
		
		public String toString() {
			return gName+(gIsBlob?"*":"")+'\t'+gModule+'\t'+(gIsReadable?"r":"-")+(gIsWritable?"w":"-")+'\t'+gDescription;
		}
	}

	public class Type {
		public static final int STYLE_UNKNOWN=-1;
		public static final int STYLE_NORMAL=1;
		public static final int STYLE_ITALIC=2;
		public static final int STYLE_OBLIQUE=3;

		public static final int STRETCH_UNKNOWN=-1;
		public static final int STRETCH_NORMAL=1;
		public static final int STRETCH_CONDENSED=2;
		
		protected String gName;
		protected String gFamily;
		protected int gStyle;
		protected int gStretch;
		protected int gWeight;
		
		public Type() {
			gName=null;
			gFamily=null;
			gStyle=STYLE_UNKNOWN;
			gStretch=STRETCH_UNKNOWN;
			gWeight=-1;
		}

		protected Type(String pName, String pFamily, String pStyle, String pStretch, String pWeight) {
			gName=pName;
			
			gFamily=pFamily;
			
			if(pStyle==null) gStyle=STYLE_UNKNOWN;
			if(pStyle.equalsIgnoreCase("normal")) gStyle=STYLE_NORMAL;
			else if(pStyle.equalsIgnoreCase("italic")) gStyle=STYLE_ITALIC;
			else if(pStyle.equalsIgnoreCase("oblique")) gStyle=STYLE_OBLIQUE;
			else gStyle=STYLE_UNKNOWN;
			
			if(pStretch==null) gStretch=STRETCH_UNKNOWN;
			if(pStretch.equalsIgnoreCase("normal")) gStretch=STRETCH_NORMAL;
			else if(pStretch.equalsIgnoreCase("condensed")) gStretch=STRETCH_CONDENSED;
			else gStretch=STRETCH_UNKNOWN;
			
			try {
				gWeight=Integer.parseInt(pWeight);
			} catch(Exception e) {
				gWeight=-1;
			}
		}
		
		public int getWeight() {
			return gWeight;
		}
		
		public int getStyle() {
			return gStyle;
		}

		public int getStretch() {
			return gStretch;
		}

		public String getFamily() {
			return gFamily;
		}

		public String getName() {
			return gName;
		}
		
		public String toString() {
			return gName+'\t'+gFamily+'\t'+getStyleString()+'\t'+getStretchString()+'\t'+gWeight;
		}

		private String getStyleString() {
			switch(gStyle) {
			case STYLE_NORMAL: return "Normal";
			case STYLE_ITALIC: return "Italic";
			case STYLE_OBLIQUE: return "Oblique";
			default: return "Unknown";
			}
		}

		private String getStretchString() {
			switch(gStretch) {
			case STRETCH_NORMAL: return "Normal";
			case STRETCH_CONDENSED: return "Condensed";
			default: return "Unknown";
			}
		}
	}

	public class Color {
		protected String gName;
		protected boolean gIsX11;
		protected boolean gIsSVG;
		protected boolean gIsXPM;
		
		protected int gRed;
		protected int gGreen;
		protected int gBlue;
		protected int gAlpha;
		
		public Color() {
			gName=null;
			gIsX11=false;
			gIsSVG=false;
			gIsXPM=false;

			gRed=-1;
			gGreen=-1;
			gBlue=-1;
			gAlpha=-1;

		}

		protected Color(String pName, String pRGBColor, String pCompliance) {
			gName=pName;
			
			//color+2 'cause '(1,2,3)'
			StringTokenizer st=new StringTokenizer(pRGBColor,"(,)");
			int count=st.countTokens();
			if(!(count==5 || count==6)) throw new ParameterException("Identify.Color: Invalid RGB-Color-Parameter\n'rgb(r,g,b) or rgba(r,g,b,a')");

			//dummy
			st.nextToken();
			
			gRed=Integer.parseInt(st.nextToken().trim());
			gGreen=Integer.parseInt(st.nextToken().trim());
			gBlue=Integer.parseInt(st.nextToken().trim());
			if(count==6) gAlpha=Integer.parseInt(st.nextToken().trim());

			if(pCompliance.indexOf("SVG")!=-1) gIsSVG=true;
			else gIsSVG=false;
			if(pCompliance.indexOf("X11")!=-1) gIsX11=true;
			else gIsX11=false;
			if(pCompliance.indexOf("XPM")!=-1) gIsXPM=true;
			else gIsXPM=false;
		}
		
		public Color(int pRed, int pGreen, int pBlue) {
			setRGB(pRed, pGreen, pBlue);
		}
		
		public Color(int pRed, int pGreen, int pBlue, int pAlpha) {
			setRGBA(pRed, pGreen, pBlue, pAlpha);
		}

		public int getAlpha() {
			return gAlpha;
		}

		public int getBlue() {
			return gBlue;
		}

		public int getGreen() {
			return gGreen;
		}

		public boolean isRGB() {
			return (gRed!=-1 && gGreen!=-1 && gBlue!=-1 && gAlpha==-1);
		}

		public void setRGB(int pRed, int pGreen, int pBlue) {
			gRed=pRed;
			gGreen=pGreen;
			gBlue=pBlue;
			gAlpha=-1;
		}

		public boolean isRGBA() {
			return (gRed!=-1 && gGreen!=-1 && gBlue!=-1 && gAlpha!=-1);
		}

		public void setRGBA(int pRed, int pGreen, int pBlue, int pAlpha) {
			gRed=pRed;
			gGreen=pGreen;
			gBlue=pBlue;
			gAlpha=pAlpha;
		}

		public int getRed() {
			return gRed;
		}

		public void setRed(int pRed) {
			gRed = pRed;
		}
		public String getName() {
			return gName;
		}

		public String getColor() {
			if(isRGB()) return "rgb("+gRed+','+gGreen+','+gBlue+')';
			if(isRGBA())  return "rgba("+gRed+','+gGreen+','+gBlue+','+gAlpha+')';
			return "(UNKNOWN)";
		}
		
		public boolean isSVG() {
			return gIsSVG;
		}
		
		public boolean isX11() {
			return gIsX11;
		}
		
		public boolean isXPM() {
			return gIsXPM;
		}
		
		public String toString() {
			
			return gName+'\t'+getColor()+'\t'+(gIsX11?"SVG":"")+' '+(gIsX11?"X11":"")+' '+(gIsX11?"XPM":"");
		}
	}
}
