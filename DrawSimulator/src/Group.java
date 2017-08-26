public class Group {
	Team team1;
	Team team2;
	Team team3;
	Team team4;
	String groupNo;
	public Group(String No) {
		groupNo = No;
		team1 = new Team();
		team2 = new Team();
		team3 = new Team();
		team4 = new Team();
	}
	public Group(String groupNo, Team team1, Team team2, Team team3, Team team4) {
		this.groupNo = groupNo;
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.team4 = team4;
	}
	public String toString() {
		return "Group " + groupNo + "\n" + team1 + "\n" + team2 + "\n" + team3 + "\n" + team4;
	}
}