package data;
import java.util.Vector;


public class Forum {
	private String name;
	private Vector<SubForum> subForums;
	private Vector<User> forumUsers;
	private User admin;
	
	public Forum (String name, User admin) {
		subForums = new Vector<SubForum>();
		forumUsers = new Vector<User>();
		this.name = name;
		this.admin = admin;
		
	}

	public Vector<SubForum> getSubForums() {
		return subForums;
	}

	public void setSubForums(Vector<SubForum> subForums) {
		this.subForums = subForums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public Vector<User> getForumUsers() {
		return forumUsers;
	}

	public void setForumUsers(Vector<User> forumUsers) {
		this.forumUsers = forumUsers;
	}
	
	public void addForumUser(User user) {
		this.forumUsers.add(user);
	}
	
	public String displayContent() {
		String content = name + ":\n";
		
		for (int i = 0; i<getSubForums().size(); i++)
			content =  content + "\t" + i + ") "+ getSubForums().elementAt(i).getName() + "\n";

		
		return content;
	}
	
	
}
