package data;
import java.util.Vector;


public class Message {
	private User authorUser;
	private int id;
	private String title;
	private String content;
	private Vector<Comment> comments;
	
	public Message(User authorUser, int id, String title, String content) {
		this.setAuthorUser(authorUser);
		this.setId(id);
		this.setTitle(title);
		this.setContent(content);
	}
	
	public void addComment(Comment comment) {
		
		comments.add(comment);
	}
	
	public void DeleteComment(int id) {
		for(int i = 0; i < comments.size(); i++) {
			if(comments.elementAt(i).getId() == id) {
				comments.remove(i); 
				break;
			}
				
		}
	}

	public User getAuthorUser() {
		return authorUser;
	}

	public void setAuthorUser(User authorUser) {
		this.authorUser = authorUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String displayContent() {
		String output = "By: " + authorUser.getName() + "\nTitle: " +  this.getTitle() + "\n";
		for (int i = 0; i<comments.size(); i++)
			output = output + "\t" + comments.elementAt(i).getAuthorUser().getName() + ": " + comments.elementAt(i).getContent() + "\n";
		
		return output;
	}
}
