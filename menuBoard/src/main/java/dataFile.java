import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class dataFile {
	ArrayList <menuItem> menuItems = new ArrayList<menuItem>();
	dataFile(String path){
		readFile(path);
	}
	void readFile(String path) {	
		String s = new String();//String for File operation
		try {
			File myFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(myFile));
			while((s = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				if (st.countTokens() == 5)
					menuItems.add(new menuItem(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));//add to ArrayList.
			}
			reader.close();//Close file.
		}catch (IOException e) {e.printStackTrace();};
	}
	void writeFile(ArrayList <menuItem> p) {
		try {
				FileWriter myWriter = new FileWriter("/files/menu.csv");
				for(int i = 0; i <= p.size(); i++)
						myWriter.write(p.get(i).toFile());
				myWriter.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
}