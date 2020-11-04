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
	private int count = 0;

	public Request() {
	}

	public Request(long id) {
		this.id = id;
	}
	
	public Request(long id, int count) {
		this.id = id;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		increment(1);
	}
	
	public void increment(int count) {
		this.count += count;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", count=" + count + "]";
	}
	
}
