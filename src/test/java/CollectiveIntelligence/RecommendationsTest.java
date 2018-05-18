package CollectiveIntelligence;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RecommendationsTest {

	private List<Review> criticas;
	private Recommendations recommendations;

	@Before
	public void setUp() throws Exception {
		criticas = Review.avaliacoes();
		recommendations = new Recommendations();
	}


	@Test
	public void deveComparaComTodos() {
		List<Rank> rank = recommendations.comparaTodosCom(Reviewer.Toby);
		assertEquals(0.9912407071619302,rank.get(0).similarity,0.0000001);
	}

	@Test
	public void euclideanDistanceScore() {
	}

	@Test
	public void similaridadeEuclidiana() {
	}

	@Test
	public void similaridadePearson() {
	}
}