import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class board extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ 
		PrintWriter  msg = res.getWriter();
		menuBoardMaker mbm = new menuBoardMaker();
		msg.println(mbm.homePage());		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		menuBoardMaker mbm = new menuBoardMaker();
		PrintWriter  msg = res.getWriter();
		String menuBoardItems = "";
		ServletContext application = getServletConfig().getServletContext();
		String path = application.getRealPath("/files/menu.csv");
		dataFile m = new dataFile(path);
		res.setContentType("text/html");
		if(req.getHeader("display").contains("TV")) {
			menuBoardItems+=mbm.menuBoard(menuBoardItems,m,req.getHeader("display"));
		}else if(req.getParameter("display").contains("TV")) {
			menuBoardItems+=mbm.menuBoard(menuBoardItems,m,req.getHeader("display"));
		}
		else
			menuBoardItems += mbm.homePage();		
		msg.println(menuBoardItems);
	}
	
}


