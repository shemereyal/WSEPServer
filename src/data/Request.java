package data;

public class Request {
	
	private String command; //This should do for now, before we explore the adventurous GUI world
	
	public Request(String command) {
		this.setCommand(command);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
