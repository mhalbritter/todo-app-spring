package todoapp.storage.datajdbc;

import java.util.List;

import org.jspecify.annotations.Nullable;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Moritz Halbritter
 */
@Repository
interface TodoEntryEntityRepository extends ListCrudRepository<TodoEntryEntity, Long> {
    @Query("SELECT MAX(t.id) FROM todo t")
    @Nullable
    Long findMaxId();

    List<TodoEntryEntity> findAllByPriority(String priority);
}
