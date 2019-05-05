package bzh.zomzog.prez.unitEvolution.service;


import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import bzh.zomzog.prez.unitEvolution.repository.PonyRepository;
import bzh.zomzog.prez.unitEvolution.service.mapper.PonyMapper;
import bzh.zomzog.prez.unitEvolution.service.mapper.PonyMapperImpl;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Unicorns;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PonyServiceTest {

    @InjectMocks
    private PonyService service;

    @Mock
    private PonyRepository repository;

    @Spy
    private PonyMapper mapper = new PonyMapperImpl();

    @Test
    public void listAll() {
        // GIVEN
        Pony pony = Pony.newBuilder()
            .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
            .name("Rarity")
            .type(Unicorns)
            .build();

        when(repository.findAllByType(Unicorns)).thenReturn(Collections.singletonList(pony));

        // WHEN
        List<PonyDto> result = service.listAll(Unicorns);

        verify(repository, times(1)).findAllByType(Unicorns);
        // THEN
        assertEquals(1, result.size());
        assertEquals("5cceb7e452d0e307dd8e7576", result.get(0).getId());
        assertEquals("Rarity", result.get(0).getName());
        assertEquals(Unicorns, result.get(0).getType());
    }
}