package api.payload;

public class Reqres {
	
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	String job;

	int id;
	public int getId() {
		return id;
	}
	public int setId(int id) {
		this.id = id;
		return id;
	}
}
