package todoapp.storage.datajdbc;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Moritz Halbritter
 */
@Repository
interface TodoEntryEntityRepository extends ListCrudRepository<TodoEntryEntity, Long> {
    @Query("SELECT MAX(t.id) FROM todo t")
    @Nullable
    Long findMaxId();
}
