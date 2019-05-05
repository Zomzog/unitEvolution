package bzh.zomzog.prez.unitEvolution.service.mapper;

import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import org.assertj.core.api.Condition;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Earth;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PonyMapperTest {

    @InjectMocks
    private PonyMapperImpl ponyMapper;

    @Spy
    private MongoMapper mongoMapper;

    @Test
    void mapPonyList() {
        Pony pony = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Big McIntosh")
                .type(Earth)
                .build();

        List<PonyDto> result = ponyMapper.map(Arrays.asList(pony, pony, pony));


        PonyDto expected = PonyDto.newBuilder()
                .id("5cceb7e452d0e307dd8e7576")
                .name("Big McIntosh")
                .type(Earth)
                .build();


        assertThat(result).areExactly(3, new Condition<>(expected::equals, ""));
    }

    @Test
    void mapPonyDto() {
        PonyDto ponyDto = PonyDto.newBuilder()
                .id("5cceb7e452d0e307dd8e7576")
                .name("Big McIntosh")
                .type(Earth)
                .build();

        Pony result = ponyMapper.map(ponyDto);

        Pony expected = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Big McIntosh")
                .type(Earth)
                .build();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void mapPony() {
        Pony pony = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Big McIntosh")
                .type(Earth)
                .build();

        PonyDto result = ponyMapper.map(pony);

        PonyDto expected = PonyDto.newBuilder()
                .id("5cceb7e452d0e307dd8e7576")
                .name("Big McIntosh")
                .type(Earth)
                .build();

        assertThat(result).isEqualTo(expected);
    }
}