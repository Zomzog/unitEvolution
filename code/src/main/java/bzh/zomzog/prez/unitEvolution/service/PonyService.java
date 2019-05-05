package bzh.zomzog.prez.unitEvolution.service;

import bzh.zomzog.prez.unitEvolution.TODO;
import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import bzh.zomzog.prez.unitEvolution.domain.PonyType;
import bzh.zomzog.prez.unitEvolution.repository.PonyRepository;
import bzh.zomzog.prez.unitEvolution.service.mapper.PonyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PonyService {

    @Autowired
    private PonyRepository repository;

    @Autowired
    private PonyMapper mapper;

    public List<PonyDto> listAll(PonyType type) {
        List<Pony> allByType = repository.findAllByType(type);
        return mapper.map(allByType);
    }

    public Optional<PonyDto> getById(String id) {
        throw new TODO();
    }

    public PonyDto save(PonyDto pony) {
        throw new TODO();
    }
}
