package bzh.zomzog.prez.unitEvolution.service.mapper;

import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PonyMapper {

    public abstract List<PonyDto> map(List<Pony> ponies);
    public abstract Pony map(PonyDto pony);
    public abstract PonyDto map(Pony pony);

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
