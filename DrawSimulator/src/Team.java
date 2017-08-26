import java.io.Serializable;

import javax.swing.ImageIcon;

public class Team implements Serializable {
	String name;
	String area;
	int pot;
	ImageIcon icon;
	public Team() {
		name = "NA";
		area = "NA";
		pot = -1;
		icon = null;
	}
	public Team(String name, String area, int pot, String path) {
		this.name = name;
		this.area = area;
		this.pot = pot;
		this.icon = new ImageIcon(path);
	}
	public String toString() {
		return name;
	}
}