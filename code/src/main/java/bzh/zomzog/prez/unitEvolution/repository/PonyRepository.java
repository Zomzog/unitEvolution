package bzh.zomzog.prez.unitEvolution.repository;

import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyType;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PonyRepository extends CrudRepository<Pony, ObjectId> {
    List<Pony> findAllByType(PonyType unicorns);
}
