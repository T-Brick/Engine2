package com.estrelsteel.engine2.file;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.estrelsteel.engine2.Engine2;

public class GameFile {
	
	private String path;
	private ArrayList<String> lines;
	
	public GameFile(String path) {
		this.path = path;
		this.lines = new ArrayList<String>();
	}
	
	public static String getCurrentPath() {
		File f = new File(System.getProperty("java.class.path"));
		return f.getParentFile().getAbsolutePath().toString();
	}
	
	public static String getDataPath() {
		if(Engine2.development) {
			return Engine2.devPath;
		}
		else if(System.getProperty("os.name").startsWith("Windows")) {
			return System.getProperty("user.home") + "/AppData/Roaming";
		}
		else if(System.getProperty("os.name").startsWith("Mac")) {
			return System.getProperty("user.home") + "/Library/Application Support";
		}
		else if(System.getProperty("os.name").startsWith("Linux")) {
			return System.getProperty("user.home") + "";
		}
		return "";
	}
	
	public String getPath() {
		return path;
	}
	
	public ArrayList<String> getLines() {
		return lines;
	}
	
	public ArrayList<String> readFile() throws IOException {
		FileReader file = new FileReader(path);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(file);
		ArrayList<String> lines = new ArrayList<String>();
		String line = br.readLine();
		while(line != null) {
			lines.add(line);
			line = br.readLine();
		}
		return lines;
	}
	
	public void updateLines() throws IOException {
		lines = readFile();
	}
	
	public void saveFile() throws IOException{
		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		for(String line : lines) {
			bw.write(line + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}
}
