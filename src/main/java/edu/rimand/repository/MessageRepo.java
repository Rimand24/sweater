package edu.rimand.repository;

import edu.rimand.domain.Dto.MessageDto;
import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    @Query("select new edu.rimand.domain.Dto.MessageDto(" +
            " m, " +
            " count (ml), " +
            " sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            " from Message m left join m.likes ml group by m"
    )
    Page<MessageDto> findAll(Pageable pageable, @Param("user") User user);

    @Query("select new edu.rimand.domain.Dto.MessageDto(" +
            " m, " +
            " count (ml), " +
            " sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            " from Message m left join m.likes ml where m.tag = :tag group by m"
    )
    Page<MessageDto> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query("select new edu.rimand.domain.Dto.MessageDto(" +
            " m, " +
            " count (ml), " +
            " sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            " from Message m left join m.likes ml where m.author = :author group by m"
    )
    Page<MessageDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);
}