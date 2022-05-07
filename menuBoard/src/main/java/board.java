import javax.servlet.*;
import javax.servlet.http.*;

import org.eclipse.jdt.internal.compiler.ClassFile;

import java.io.*;
import java.net.URL;
import java.util.*;
public class board extends HttpServlet{
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//doPost(req, res);  
		PrintWriter  msg = res.getWriter();
		//msg.println(homePage());
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter  msg = res.getWriter();
		String menuBoardItems = "";
		ServletContext application = getServletConfig().getServletContext();
		String path = application.getRealPath("/WEB-INF/menu.csv");
		menu m = new menu(path);
		if(req.getParameter("display").contains("TV")) {
			menuBoardItems+=menuBoard(req,menuBoardItems,m,req.getParameter("display"));
		}
		else if(req.getParameter("display").contains("Mobile")) {	
			menuBoardItems+=mobilePage(req,menuBoardItems,m);
		}
		else
			menuBoardItems += homePage();		
		msg.println(menuBoardItems);
	}
	String mobilePage(HttpServletRequest req,String menuItemsPage, menu m){
		String type = "";
		menuItemsPage += webHeather();
		for(int j = 1; j < 6; j++ ) {
			for(int i = 0; i < m.menuItems.size(); i++){
				if(m.menuItems.get(i).getScreen().compareTo("TV " + j + " Display") == 0){
					if(m.menuItems.get(i).getType().compareTo(type) !=0) {				
						type = m.menuItems.get(i).getType();
						menuItemsPage += tableHeather(m,i);
					}
					menuItemsPage += menuItem(m, i);
				}
			}
		}
		menuItemsPage += "\n</table>\n</div>\n</div></form>\n</body>\n</html>";
		return menuItemsPage;
	}
	String menuBoard(HttpServletRequest req,String menuItemsPage, menu m, String screen){
		int line = 42;
		int currentLineItems = 0;
		int linecount = 0;
		int name = 30;
		int description = 50;
		int price = 12;
		String type = "";
		menuItemsPage += webHeather();
		for(int i = 0; i < m.menuItems.size(); i++){
			currentLineItems = 0;
			currentLineItems = (m.menuItems.get(i).getName().length() / name);
			currentLineItems = (m.menuItems.get(i).getPrice().length() / price);
			currentLineItems = (m.menuItems.get(i).getDiscription().length() / description);
			
			if(linecount > line || (linecount + currentLineItems) > line) {
				menuItemsPage += columnEnd();
				linecount = 0;
			}
			if(m.menuItems.get(i).getScreen().compareTo(screen) == 0){
				if(m.menuItems.get(i).getType().compareTo(type) !=0 || linecount == 0) {				
					type = m.menuItems.get(i).getType();
					menuItemsPage += tableHeather(m,i);
					linecount++;
				}
				
				linecount = linecount + (m.menuItems.get(i).getName().length() / name) + 1;
				menuItemsPage += menuItem(m, i);
				linecount = linecount + (m.menuItems.get(i).getPrice().length() / price);
				linecount = linecount + (m.menuItems.get(i).getDiscription().length() / description) + 1;
				
			}
		}
		menuItemsPage += "\n\t\t\t</table>\n\t\t</div>\n\t</div>\n</body>\n</html>";
		return menuItemsPage;
	}
	String menuItem(menu m, int i) {
		return ((m.menuItems.get(i).getPrice().length()<2)?("\n\t\t\t\t<tr><td class='name' colspan='2'> " + m.menuItems.get(i).getName() + "</td>"):
																		("\n\t\t\t\t<tr><td class='name'> " + m.menuItems.get(i).getName() + "</td>"))
				+(( m.menuItems.get(i).getPrice().length()<2)?"":("\n\t\t\t\t<td class='price' >" + m.menuItems.get(i).getPrice() + "</td></tr>"))
				+(( m.menuItems.get(i).getDiscription().length()>2)?"\n\t\t\t\t<tr><td class='description' colspan='2'>" 
				+ m.menuItems.get(i).getDiscription() + "</td></tr>":"");
	}
	String tableHeather(menu m, int i){
		return "\n\t\t\t\t<tr><th class='heather' colspan='2'>" + m.menuItems.get(i).getType() + "</th></tr>";
	}
	String columnEnd(){
		return "\n\t\t\t</table>\n\t\t</div>\n\t</div>\n\t<div class='column'>\n\t\t<div class='row'>\n\t\t\t<table id='myTable'>"; 
	}
	String webHeather() {
		return "<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<title>TV Menu Display</title>"
//				+ "\n\t\t<script type='text/javascript' src='https://menuboardfiles.s3.amazonaws.com/board.js'></script>"
//				+ "\n\t\t<link rel='stylesheet' type='text/css' href='https://menuboardfiles.s3.amazonaws.com/board.css'/>"
				+ "\n\t\t<link rel='stylesheet' type='text/css' href='board.css'/>\n"
				+ "\t\t<script type='text/javascript' src='board.js'></script>\n"
				+ "\t\t<meta charset='utf-8' name='viewport' content='width=device-width, initial-scale=1'>\n\t</head> "
				+ "\n<body class='home' onload='startTime()'>\n"
				+ "\t<button class='fullButton' onclick='openFullscreen();'><div id='clock'>Welcome to Listranis at McLean</div></button>"
				+ "\n\t<div class='column'>\n\t\t<div class='row'>\n\t\t\t<table id='myTable'>";
	}
	String homePage(){
		String s = "<html>\n <head>\n<title>Listrani's at McLean restaurant Menu Board</title>"
				+ "\n<meta name=viewport content=width=device-width, initial-scale=1>"
//				+ "\n<script type='text/javascript' src='https://menuboardfiles.s3.amazonaws.com/board.js'></script>"
//				+ "\n<link rel='stylesheet' type='text/css' href='https://menuboardfiles.s3.amazonaws.com/board.css'/>"
				+ "\n<script type='text/javascript' src='board.js'></script>"
				+ "\n<link rel='stylesheet' type='text/css' href='board.css'/>"
				+ "</head>\n<body>\n<div class = 'main' id='main'>\n<img id='img1' title=logo src=https://menuboardfiles.s3.amazonaws.com/logo.png>"
				+ "\n<p> Welcome to Listrani's at McLean restaurant Menu Board.</p>"
				+ "\n<p> Please Select the page you would like to view:</p>"
				+ "\n<form id='displayRequest' method='post' action='/menuBoard/tv'>";
		for(int i = 1; i < 7; i++)
			s+= "\n<input type='submit' name='display' value='TV " + i + " Display'/>";
		s+= "\n<input type='submit' name='display' value='Mobile'/>";
		s+= "\n</form>";
		return s;
	}
}
class menu {
	ArrayList <menuItem> menuItems = new ArrayList<menuItem>();
	menu(String path){
		readFile(menuItems,path);
	}
	void readFile(ArrayList <menuItem> p, String path) {	
		String s = new String();//String for File operation
		try {
			File myFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(myFile));
			while((s = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				if (st.countTokens() == 5)
					p.add(new menuItem(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));//add to ArrayList.
			}
			reader.close();//Close file.
		}catch (IOException e) {e.printStackTrace();};
	}
	void writeFile(ArrayList <menuItem> p) {
		try {
				FileWriter myWriter = new FileWriter("menuBoardFile.txt");
				for(int i = 0; i <= p.size(); i++)
					if(p.size() == 0)
						myWriter.write("Files in Java might be tricky, but it is fun enough!");
					else
						myWriter.write(p.toString());
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
}

class menuItem{
	private String screen;
	private String type;	
	private String name;
	private String discription;
	private String price;
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	menuItem(String s,String t, String n, String d,String p){
		screen = s;
		type = t;
		name = n;
		discription = d;
		price = p;		
	}
	public String toString() { return screen + "\t" + type + "\t" + name + "\t" + discription + "\t" + price + "\n";  }
	public String toFile() { return screen + "," + type + "," + name + "," + discription + "," + price + "\n";  }
	
}


