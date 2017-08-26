import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DrawSimulator {
	static Map<String, Team> teams = new HashMap<>();
	static List<Team> pot1 = new LinkedList<>();
	static List<Team> pot2 = new LinkedList<>();
	static List<Team> pot3 = new LinkedList<>();
	static List<Team> pot4 = new LinkedList<>();
	static Map<String, Group> groups = new HashMap<>();
	static List<Team> list = new LinkedList<>();
	public static void readTeams () {
		String root = "/Users/Steveisno1/Documents/XJBjava/DrawSimulator/src";
		try {
			FileInputStream fis = new FileInputStream(root + "/1718ChampionsLeagueTeams.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			//teams = (HashMap<String, Team>) ois.readObject();
			list = (LinkedList<Team>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		for(Team t : list) {
			teams.put(t.name, t);
			if(t.pot == 1) {
				pot1.add(t);
			}
			else if(t.pot == 2) {
				pot2.add(t);
			}
			else if(t.pot == 3) {
				pot3.add(t);
			}
			else {
				pot4.add(t);
			}
		}
	}
	
	public static void createTeam (int num) {
		for(int i = 0; i < num; i++) {
			Group g = new Group(String.valueOf((char)('A' + i)));
			groups.put(g.groupNo, g);
		}
	}
	
	public static void drawPot1 () {
		List<String> group = new LinkedList<>();
		for(int i = 0; i < 8; i++) {
			group.add(String.valueOf((char)('A' + i)));
		}
		for(int i = 0; i < 8; i++) {
			int size = pot1.size();
			int index = (int)(Math.random() * size);
			int groupNo = (int)(Math.random() * size);
			String No = group.get(groupNo);
			Team t = pot1.get(index);
			pot1.remove(index);
			group.remove(groupNo);
			groups.get(No).team1 = t;
		}
	}
	
	public static void drawPot2() {
		List<String> group = new LinkedList<>();
		for(int i = 0; i < 8; i++) {
			group.add(String.valueOf((char)('A' + i)));
		}
		for(int i = 0; i < 8; i++) {
			int size = pot2.size();
			int index = (int)(Math.random() * size);
			Team t = pot2.get(index);
			List<String> available = new LinkedList<>();
			for(int j = 0; j < 8; j++) {
				Group g = groups.get(String.valueOf((char)('A' + j)));
				if(g.team2.toString().equals("NA") && !g.team1.area.equals(t.area)) {
					available.add(String.valueOf((char)('A' + j)));
				}
			}
			int groupNo = (int)(Math.random() * available.size());
			String No = available.get(groupNo);
			pot2.remove(index);
			groups.get(No).team2 = t;
		}
	}
	
	public static void drawPot3() {
		List<String> group = new LinkedList<>();
		for(int i = 0; i < 8; i++) {
			group.add(String.valueOf((char)('A' + i)));
		}
		for(int i = 0; i < 8; i++) {
			int size = pot3.size();
			int index = (int)(Math.random() * size);
			Team t = pot3.get(index);
			List<String> available = new LinkedList<>();
			for(int j = 0; j < 8; j++) {
				Group g = groups.get(String.valueOf((char)('A' + j)));
				if(g.team3.toString().equals("NA") && !g.team1.area.equals(t.area) && !g.team2.area.equals(t.area)) {
					available.add(String.valueOf((char)('A' + j)));
				}
			}
			int groupNo = (int)(Math.random() * available.size());
			String No = available.get(groupNo);
			pot3.remove(index);
			groups.get(No).team3 = t;
		}
	}
	
	public static void drawPot4() {
		List<String> group = new LinkedList<>();
		for(int i = 0; i < 8; i++) {
			group.add(String.valueOf((char)('A' + i)));
		}
		for(int i = 0; i < 8; i++) {
			int size = pot4.size();
			int index = (int)(Math.random() * size);
			Team t = pot4.get(index);
			List<String> available = new LinkedList<>();
			for(int j = 0; j < 8; j++) {
				Group g = groups.get(String.valueOf((char)('A' + j)));
				if(g.team4.toString().equals("NA") && !g.team1.area.equals(t.area) && !g.team2.area.equals(t.area) && !g.team3.area.equals(t.area)) {
					available.add(String.valueOf((char)('A' + j)));
				}
			}
			int groupNo = (int)(Math.random() * available.size());
			String No = available.get(groupNo);
			pot4.remove(index);
			groups.get(No).team4 = t;
		}
	}
	
	public static void draw() {
		drawPot1();
		drawPot2();
		drawPot3();
		drawPot4();
	}
	
	public static void print() {
		for(int i = 0; i < 8; i++) {
			Group g = groups.get(String.valueOf((char)('A' + i)));
			System.out.println("Group " + g.groupNo);
			System.out.println(g.team1);
			System.out.println(g.team2);
			System.out.println(g.team3);
			System.out.println(g.team4);
		}
	}
	
	public static void main (String[] args) throws Exception {
		
		int numtry = 1;
		while(numtry != 0) {
			try {
				readTeams();
				createTeam(8);
				draw();
				break;
			} catch (Exception e) {
				continue;
			}
		}
		print();
		Window w = new Window(groups);
	}
}