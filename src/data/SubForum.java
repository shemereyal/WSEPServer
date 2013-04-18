package data;
import java.util.Vector;


public class SubForum {
	private int id;
	private String name;
	private Vector<User> forumUsers;
	private Vector<Message> messages;
	private User admin;
	
	public SubForum(int id, String name, User admin) {
		this.id = id;
		this.name = name;
		this.forumUsers = new Vector<User>();
		this.messages = new Vector<Message>();
		this.admin = admin;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<User> getForumUsers() {
		return forumUsers;
	}
	public void setForumUsers(Vector<User> forumUsers) {
		this.forumUsers = forumUsers;
	}
	public Vector<Message> getMessages() {
		return messages;
	}
	public void setMessages(Vector<Message> messages) {
		this.messages = messages;
	}
	public String displayContent() {
		String content = name + ":\n";
		
		for (int i = 0; i<getMessages().size(); i++)
			content =  content + "\t" + i + ") "+ getMessages().elementAt(i).getTitle() + "\n";

		
		return content;
		
		
	}
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	
}
