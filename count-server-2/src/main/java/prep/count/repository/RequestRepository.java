package prep.count.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

	@Query("select coalesce(sum(r.count), 0) from Request r "
			+ "where r.requestId = ?1")
	int countByRequestId(long requestId);
	
	List<Request> findByRequestId(long requestId, Pageable pageable);
	
	@Modifying
	@Query("delete from Request r where r.id in ?1")
	void delete(List<Long> ids);
	
}
