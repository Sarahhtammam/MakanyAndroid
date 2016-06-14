package SimpleModels;

public class SimpleMessage {

	private String senderMail;
	private String reciverMail;
	private String content;
	
	public SimpleMessage(String senderMail, String reciverMail, String content) {
		super();
		this.senderMail = senderMail;
		this.reciverMail = reciverMail;
		this.content = content;
	}
	
	public String getSenderMail() {
		return senderMail;
	}
	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}
	public String getReciverMail() {
		return reciverMail;
	}
	public void setReciverMail(String reciverMail) {
		this.reciverMail = reciverMail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
