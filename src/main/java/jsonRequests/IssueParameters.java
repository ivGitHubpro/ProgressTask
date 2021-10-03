package jsonRequests;

public class IssueParameters {
    private String title;
    private String body;
    private String state;
    
    public void setTitle(String title) {
    	 this.title = title;
    }
    
    public String getTitle() {
   	 return title;
   }
    
    public void setBody(String body) {
   	 this.body = body;
   }
   
   public String getBody() {
  	 return body;
  }
   
   public void setState(String state) {
	   	 this.state = state;
	   }
	   
	   public String getState() {
	  	 return state;
	  }
}
