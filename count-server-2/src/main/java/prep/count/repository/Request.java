package prep.count.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
    private long id;
	
	@Column(name = "REQUEST_ID")
	private long requestId;
	
	@Column(name = "COUNT")
	private int count = 1;

	public Request() {
	}

	public Request(long requestId) {
		this.requestId = requestId;
	}
	
	public Request(long requestId, int count) {
		this.requestId = requestId;
		this.count = count;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
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
