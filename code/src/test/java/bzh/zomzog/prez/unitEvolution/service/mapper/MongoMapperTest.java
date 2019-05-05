package bzh.zomzog.prez.unitEvolution.service.mapper;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MongoMapperTest {

    private MongoMapper mapper = new MongoMapperImpl();

    @Test
    public void mapNull() {
        assertNull(mapper.map((String) null));
    }
}