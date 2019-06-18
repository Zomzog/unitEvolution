package bzh.zomzog.prez.unitEvolution.domain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LegacyTest {
    @Test
    public void test() {
        List<PonyDto> myList = new ArrayList<>();
        PonyDto pinkiePie = PonyDto.newBuilder().name("Pinkie Pie").build();
        myList.add(pinkiePie);
        PonyDto rainbowDash = PonyDto.newBuilder().name("Rainbow Dash").build();
        myList.add(rainbowDash);

        assertThat(myList).containsExactlyInAnyOrder(pinkiePie, rainbowDash);
    }

    @Test
    public void equals() {
        PonyDto rainbowDash = PonyDto.newBuilder().name("Rainbow Dash").build();
        PonyDto pinkiePie = PonyDto.newBuilder().name("Pinkie Pie").build();
        assertThat(rainbowDash).isEqualToIgnoringGivenFields(pinkiePie, "name");
    }
}
