package bzh.zomzog.prez.unitEvolution.service.mapper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MongoMapperTest {

    private MongoMapper mapper = new MongoMapperImpl();

    @Test
    void mapNull() {
        assertThat(mapper.map((String) null)).isNull();
    }
}