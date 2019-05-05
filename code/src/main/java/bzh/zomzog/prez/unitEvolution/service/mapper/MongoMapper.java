package bzh.zomzog.prez.unitEvolution.service.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public abstract class MongoMapper {

    String map(ObjectId objectId) {
        return objectId.toString();
    }

    ObjectId map(String id) {
        if (null == id) {
            return null;
        }
        return new ObjectId(id);
    }
}
