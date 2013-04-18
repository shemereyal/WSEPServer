package communication;

import data.Comment;
import data.Forum;
import data.Message;
import data.SubForum;
import data.User;


public class Protocol {

	private User currUser;
	private SubForum subForum;
	private Message message;
	private Forum forum;
	
	
	public String processMessage(String strReq) { //throws IOException {
		//String strReq = in.readLine(); //request.getCommand();
		String whatToDo = "";
		
		String returnString = "";
		
		for(int i = 0; i < strReq.length() && strReq.charAt(i) != ' '; i++) 
			whatToDo = whatToDo + strReq.charAt(i);	//Get the command	
		
		String restToDo = strReq.substring(whatToDo.length()).trim();
		if(whatToDo.equals("ViewSubForum")) {
			returnString = handleViewSubForum(restToDo);
		}
		else if(whatToDo.equals("ViewMessage")) { //ViewMessage 0-99
			returnString = handleViewMessage(restToDo);
		}	
		else if(whatToDo.equals("ViewForum")) { //ViewMessage 0-99
			returnString = handleViewForum(restToDo);
		}			
		else if(whatToDo.equals("LogIn")) { //SignUp UserName Password Name Age
			returnString = handleLogIn(restToDo);
		}			
		else if(whatToDo.equals("LogOut")) { //SignUp UserName Password Name Age
			returnString = handleLogOut();
		}	
		else if(whatToDo.equals("CreateSubForum")) { //SignUp UserName Password Name Age
			returnString = createSubForum(restToDo);
		}			
		
		//ADD MESSAGE
		else if(whatToDo.equals("SignUp")) { //SignUp UserName Password Name Age
			returnString = handleSignUp(restToDo);
		}				
		else if(whatToDo.equals("Back")) { //SignUp UserName Password Name Age
			returnString = handleBack(restToDo);
		}
		else if(whatToDo.equals("Init")) {
			returnString = handleInit(restToDo);			
		}
		else
			returnString = "Unable to process request.\n";
		
		return returnString;
	}


	private String handleInit(String strReq) {
		String output = "";
		String userName = "";
		String password = "";
			int i;
			for(i = 0; i < strReq.length() && strReq.charAt(i) != ' '; i++)
				userName = userName + strReq.charAt(i);
			
			i++;
			for(; i<strReq.length(); i++)
				password = password + strReq.charAt(i);
			User admin = new User(userName, password);
			this.forum = new Forum("Shemer forum", admin);
			output = "Forum created. Admin: " + userName;
			forum.setAdmin(admin);

		
		return output;
	}
	


	private String handleBack(String substring) {
			String output = "";
			if(this.message != null) {
				this.message = null;
				output = subForum.displayContent();
			}
			else if (this.subForum != null)
			{
				this.subForum = null;
				output = forum.displayContent();
			}
			else {
				output = "Can't go back.\n";
			}
			
			
			return output;
	}


	private String handleViewForum(String substring) {
		return forum.displayContent();
	}


	private String handleSignUp(String strReq) {
		String output = "";
		boolean found = false;
		String userName = "";
		String password = "";
		int i;
		for(i = 0; i < strReq.length() && strReq.charAt(i) != ' '; i++)
			userName = userName + strReq.charAt(i);
		
		i++;
		for(; i<strReq.length(); i++)
			password = password + strReq.charAt(i);
		
		for(int j = 0; i < this.forum.getForumUsers().size() && !found; j++) {
			if (this.forum.getForumUsers().elementAt(j).getUserName().equals(userName))
				found = true;
		}
		
		if (!found) {
			this.forum.getForumUsers().add(new User(userName, password));
			output = "Login successful. \nUsername: " + userName + "\n"+ this.forum.displayContent();
		}
		return output;
	}


	private String handleLogOut() {
		String output = "";
		
		if(currUser != null) {
			output = "Logged out from: " + currUser.getUserName(); 
			currUser = null;
		}
		
		else
			output = "Not logged in.\n";
		
		return output;
	}

	private String handleLogIn(String strReq) {
		String output = "";
		boolean found = false;
		String userName = "";
		String password = "";
		User forumUser = null;
		int i;
		for(i = 0; i < strReq.length() && strReq.charAt(i) != ' '; i++)
			userName = userName + strReq.charAt(i);
		
		i++;
		for(; i<strReq.length(); i++)
			password = password + strReq.charAt(i);
		
		for(int j = 0; j<forum.getForumUsers().size() && !found; j++) {
			//Authentication
			forumUser = forum.getForumUsers().elementAt(j);
			if(userName.equals(forumUser.getUserName()) && password.equals(forumUser.getPassword()))
					found = true;			
		}
		
		if(!found) {
			output = "Invalid user name or password.\n";
		}
		else {
			currUser = forumUser;
			output = "Successfully connected as: " + userName + "\n";
		}
		
		return output;
	}
	
	
	private String handleViewMessage(String strReq) {
		int messageNum = Integer.parseInt(strReq); //first two digits are for the subForum number
		boolean found = false;
		String output = "";
		if(subForum != null) {
			if(messageNum < subForum.getMessages().size() && messageNum >= 0) {
				//Go over messages and look for message
				for(int i = 0; i < subForum.getMessages().size() && !found; i++) {
					if(messageNum == subForum.getMessages().elementAt(i).getId() % 100){ //Just the two unique identifiers for message
							found = true;
							this.message = subForum.getMessages().elementAt(i);
					}						
				}
				
				if(found)
						output = this.message.displayContent(); 
				else
					output = "Sorry, bro. Message not found.\n";					
				
			}
			else 
				output = "Sorry bro, number invalid.\n";			
	
		}	
		else {
			output = "Please enter a sub-forum first.\n";
		}
		return output;

	}

	private String handleViewSubForum(String strReq) {
		int subForumNum = Integer.parseInt(strReq);
		boolean found = false;
		String output = "";
		if (subForumNum < forum.getSubForums().size() && subForumNum >= 0) {//Forum number indeed exists
			//Go over subForums and look for number
		
			for(int i = 0; i<forum.getSubForums().size() && !found; i++) {
				if (subForumNum == forum.getSubForums().elementAt(i).getId()) {
					found = true;
					this.subForum = forum.getSubForums().elementAt(i);
				}
			}
			if(found)
				output = subForum.displayContent();
			else
				output = "Sorry, bro. Sub-forum not found.\n";
		}
		else 
			output = "Sorry bro, number invalid.\n";
		
		return output;
	}

	
	
	
	
	
	public String generateForum() {
		return null;
		
	}

	
	public String createSubForum(String strReq) {
			String output = "";
			String name = "";
			String description = "";
			if(currUser.getUserName().equals(forum.getAdmin().getUserName())) {
			int i;
			for(i = 0; i < strReq.length() && strReq.charAt(i) != ' '; i++)
				name = name + strReq.charAt(i);
				
			i++;
			for(; i<strReq.length(); i++)
				description = description + strReq.charAt(i);	
			
			User admin = currUser;
			
			SubForum newSubForum = new SubForum(forum.getSubForums().size(), name, admin);
			
			forum.getSubForums().add(newSubForum);
			output = "Sub Forum: " + name + " with description: " + description + "\n\n\tCreated Succesfully.\n";
			}
			
			else {
				output = "Permission denied. You are not admin. You cannot create forum.\n";
			}
			return output;
	}

	
	public String addMessage(Message message, SubForum forum) {
		return null;
		
	}

	
	public String addComment(int forumId, int messageId, Comment comment) {
		return null;
	}


}
