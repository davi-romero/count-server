package prep.count.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

	@Query("select coalesce(sum(r.count), 0) from Request r where r.id = ?1")
	int getCount(long requestId);
	
}
