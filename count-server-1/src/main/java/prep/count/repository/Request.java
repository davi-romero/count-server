package prep.count.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST")
public class Request {
	
	@Id
	@Column(name = "ID")
    private long id;
	
	@Column(name = "COUNT")
	private int count;

	public Request() {		
	}
	
	public Request(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		count++;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", count=" + count + "]";
	}
	
}
