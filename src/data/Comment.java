package data;

public class Comment {
	private String content;
	private int id;
	private User authorUser;
	
	
	public Comment(String content, int id, User authorUser) {
		this.setContent(content);
		this.setId(id);
		this.setAuthorUser(authorUser);
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getAuthorUser() {
		return authorUser;
	}


	public void setAuthorUser(User authorUser) {
		this.authorUser = authorUser;
	}
}
