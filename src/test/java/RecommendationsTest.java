import CollectiveIntelligence.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RecommendationsTest {

    private List<Review> criticas = Review.avaliacoes();


    @Test
    public void testaSimilaridadeEuclidiana() {
        double similaridadeEuclidiana = 0;
        Recommendations recommendations = new Recommendations();
        Review rose = findBy(Reviewer.Rose);
        Review seymour = findBy(Reviewer.Seymour);
        if (rose != null && seymour != null)
            similaridadeEuclidiana = recommendations.similaridadeEuclidiana(rose, seymour);
        assertEquals(0.294298055086, similaridadeEuclidiana, 0.000000000001);
    }

    @Test
    public void testaSimilaridadeComPearson() {
        double similaridadePearson = 0;
        Recommendations recommendations = new Recommendations();
        Review rose = findBy(Reviewer.Rose);
        Review seymour = findBy(Reviewer.Seymour);
        if (rose != null && seymour != null) similaridadePearson = recommendations.similaridadePearson(rose, seymour);
        assertEquals(0.396059017191, similaridadePearson, 0.000000000001);
    }

    @Test
    public void rankingBaseadoEmUsuarioComPearson() {
        Recommendations recommendations = new Recommendations();
        List<Rank> ranking = recommendations.comparaTodosCom(Reviewer.Rose);
        assertEquals(0.9912407071619302, ranking.get(0).similarity, 0.000000000001);
    }

    private Review findBy(Reviewer reviewer) {
        return criticas.stream()
                .filter(review -> review.critico == reviewer)
                .findFirst()
                .orElseGet(null);
    }

}


