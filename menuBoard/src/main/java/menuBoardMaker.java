public class menuBoardMaker {
	String menuBoard(String menuItemsPage, dataFile m, String screen){
		int countColumn = 1;
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
				countColumn++;
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
		menuItemsPage += "\n\t\t\t</table>";
		if(linecount < 30 && countColumn <= 4) {
			menuItemsPage += addQRFB();
			if(linecount < 20)
				menuItemsPage += addQRHP();
		}
		menuItemsPage += "\n\t\t</div>\n\t</div>\n</div>\n</body>\n</html>";
		return menuItemsPage;
	}
	String menuItem(dataFile m, int i) {
		String s = "";
		if(m.menuItems.get(i).getScreen().compareTo("TV 6 Display") != 0 || m.menuItems.get(i).getPrice().length() < 10) {
			s+= ((m.menuItems.get(i).getPrice().length()<2)?("\n\t\t\t\t<tr><td class='name' colspan='2'> " + m.menuItems.get(i).getName() + "</td>"):
						("\n\t\t\t\t<tr><td class='name'> " + m.menuItems.get(i).getName() + "</td>"));
			s+= (( m.menuItems.get(i).getPrice().length()<2)?"":("\n\t\t\t\t<td class='price' >" + m.menuItems.get(i).getPrice() + "</td></tr>"));
		}
		else {
			s+= ("\n\t\t\t\t<tr><td class='name' colspan='2'> " + m.menuItems.get(i).getName() + "</br>" + m.menuItems.get(i).getPrice() + "</td>");
		}
		
		s+= (( m.menuItems.get(i).getDiscription().length()>2)?"\n\t\t\t\t<tr><td class='description' colspan='2'>" 
					+ m.menuItems.get(i).getDiscription() + "</td></tr>":"");
		
		return s;
	}
	String tableHeather(dataFile m, int i){
		return "\n\t\t\t\t<tr><th class='heather' colspan='2'>" + m.menuItems.get(i).getType() + "</th></tr>";
	}
	String columnEnd(){
		return "\n\t\t\t</table>\n\t\t</div>\n\t</div>\n\t<div class='column'>\n\t\t<div class='row'>\n\t\t\t<table id='myTable'>"; 
	}
	String webHeather() {
		return  "\n<div class='column'>\n\t\t<div class='row'>\n\t\t\t<table id='myTable'>";
	}	
	String webHeatherJava() {
		return "<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<title>TV Menu Display</title>"
				+ "\n\t\t<link rel='stylesheet' type='text/css' href='files/board.css'/>\n"
				+ "\t\t<script type='text/javascript' src='files/board.js'></script>\n"
				+ "\t\t<meta charset='utf-8' name='viewport' content='width=device-width, initial-scale=1'>\n\t</head> "
				+ "\n<body class='home' id='home' onload='startTime();getDate();'>\n<div class='content'>\n"
				+"\n\t<button class='leftButton' name='display' onclick='previousPage();'><div id='leftButton'> Back </div></button>"
				+ "\n\t<button class='centerButton' onclick='openFullscreen();'><div>Welcome to Listranis at McLean</div></button>"
				+ "\n\t<button class='rightButton' name='display' onclick='nextPage();'><div id='rightButton'> Next </div></button>"
				+ "\n<div class='column'>\n\t\t<div class='row'>\n\t\t\t<table id='myTable'>";
	}
	String homePage(){
		String s = "<html>\n <head>\n<title>Listrani's at McLean restaurant Menu Board</title>"
				+ "\n<meta name=viewport content='width=device-width, initial-scale=1'>"
//				+ "\n<script type='text/javascript' src='files/board.js'></script>"
				+ "\n<script type='text/javascript' src='https://menuboardfiles.s3.amazonaws.com/files/board.js'></script>"
//				+ "\n<link rel='stylesheet' type='text/css' href='files/board.css'/>"
				+ "\n<link rel='stylesheet' type='text/css' href='https://menuboardfiles.s3.amazonaws.com/files/board.css'/>"
				+ "\n<body class='home' onload='startTime();getDate();'>\n<div class='content'>"
				+"\n\t<div id='topButtons'><button class='leftButton' name='display' onclick='previousPage();'><div id='leftButton'> Back </div></button>"
				+ "\n\t<button class='centerButton' id='centerButton' onclick='openFullscreen();'><div>Welcome to Listranis at McLean</div></button>"
				+ "\n\t<button class='rightButton' name='display' onclick='nextPage();'><div id='rightButton'> Next </div></button>"
				+ "\n\t<div id='homeButtonDiv' style='visibility: visible;'><button class='homeButton' id='HomeButton' onclick='homeButton();'>Home</button></div></div>"
				+ "\n<div class = 'home' id='home'>"
				+ "\n<div class = 'main' id='main'>"
				//+ "\n\t<img id='logo' title=logo src=files/img/logo.png>"
				+ "\n\t<img id='logo' title=logo src=https://menuboardfiles.s3.amazonaws.com/files/img/logo.png>"
				+ "\n<p> Menu Board.</p>"
				+ "\n<p> Please Select the page you would like to view:</p>";
		for(int i = 1; i < 7; i++)
			s+= "\n<input type='button' id='displayRequest' value='TV " + i + " Display' onclick='getPage("+ i +")'/></br>";
		s+= "\n</div>\n</div>\n</body>\n</html>";
		return s;
	}
	String homePageJava(){
		String s = "<html>\n <head>\n<title>Listrani's at McLean restaurant Menu Board</title>"
				+ "\n<meta name=viewport content=width=device-width, initial-scale=1>"
//				+ "\n<script type='text/javascript' src='https://menuboardfiles.s3.amazonaws.com/board.js'></script>"
//				+ "\n<link rel='stylesheet' type='text/css' href='https://menuboardfiles.s3.amazonaws.com/board.css'/>"
				+ "\n<script type='text/javascript' src='files/board.js'></script>"
				+ "\n<link rel='stylesheet' type='text/css' href='files/board.css'/>"
				+ "\n<body class='home' onload='startTime();getDate();'>\n<div class='content'>"
				+"\n\t<button class='leftButton' name='display' onclick='previousPage();'><div id='leftButton'> Back </div></button>"
				+ "\n\t<button class='centerButton' onclick='openFullscreen();'><div>Welcome to Listranis at McLean</div></button>"
				+ "\n\t<button class='rightButton' name='display' onclick='nextPage();'><div id='rightButton'> Next </div></button>"
				+ "\n<div class = 'home' id='home'>"
				+ "\n<div class = 'main' id='main'>"
				//+ "\n\t<img id='img1' title=logo src=https://menuboardfiles.s3.amazonaws.com/logo.png>"
				+ "\n\t<img id='logo' title=logo src=files/img/logo.png>"
				+ "\n<p> Menu Board.</p>"
				+ "\n<p> Please Select the page you would like to view:</p>"
				+ "\n<form id='displayRequest' method='post' action='/menuBoard/board'>";
		for(int i = 1; i < 7; i++)
			s+= "\n<input type='submit' name='display' value='TV " + i + " Display'/></br>";
		s+= "\n</form>\n</div>\n</div>\n</body>\n</html>";
		return s;
	}
	String addQRFB() {
		String s = "";
		//s += "\n\t<img id='qr' title=QR src=files/img/facebookQR.png> <p id='qr'> Follow us on Facebook<p>";
		s += "\n\t<img id='qr' title=QR src=https://menuboardfiles.s3.amazonaws.com/files/img/facebookQR.png> <p id='qr'> Follow us on Facebook<p>";
		return s;
	}
	String addQRHP() {
		String s = "";
		//s += "\n\t<img id='qr' title=QR src=files/img/listranisQR.png> <p id='qr'> Visit our Page<p>";
		s += "\n\t<img id='qr' title=QR src=https://menuboardfiles.s3.amazonaws.com/files/img/listranisQR.png> <p id='qr'> Visit our Page<p>";
		return s;
	}
}
