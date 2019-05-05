package bzh.zomzog.prez.unitEvolution.service.mapper;

import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Earth;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class PonyMapperTest {

    @InjectMocks
    private PonyMapperImpl ponyMapper;

    @Spy
    private MongoMapper mongoMapper;

    @Test
    public void mapPonyList() {
        Pony pony = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Big McIntosh")
                .type(Earth)
                .build();

        List<PonyDto> result = ponyMapper.map(Arrays.asList(pony, pony, pony));

        assertEquals(3, result.size());
        result.forEach(it -> {
                    assertEquals("5cceb7e452d0e307dd8e7576", it.getId());
                    assertEquals("Big McIntosh", it.getName());
                    assertEquals(Earth, it.getType());
                }
        );
    }

    @Test
    public void mapPonyDto() {
        PonyDto ponyDto = PonyDto.newBuilder()
                .id("5cceb7e452d0e307dd8e7576")
                .name("Big McIntosh")
                .type(Earth)
                .build();

        Pony result = ponyMapper.map(ponyDto);

        assertEquals(new ObjectId("5cceb7e452d0e307dd8e7576"), result.getId());
        assertEquals("Big McIntosh", result.getName());
        assertEquals(Earth, result.getType());
    }

    @Test
    public void mapPony() {
        Pony pony = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Big McIntosh")
                .type(Earth)
                .build();

        PonyDto result = ponyMapper.map(pony);

        assertEquals("5cceb7e452d0e307dd8e7576", result.getId());
        assertEquals("Big McIntosh", result.getName());
        assertEquals(Earth, result.getType());
    }
}