package bzh.zomzog.prez.unitEvolution.service.mapper;

import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, uses = MongoMapper.class)
public abstract class PonyMapper {

    public abstract List<PonyDto> map(List<Pony> ponies);

    public abstract Pony map(PonyDto pony);

    public abstract PonyDto map(Pony pony);
}
