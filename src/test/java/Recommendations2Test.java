import CollectiveIntelligence.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Recommendations2Test {

	private List<Critica> criticas;
	private Recommendations2 recommendations2;

	@Before
	public void setUp() throws Exception {
		criticas = Critica.criticas();
		recommendations2 = new Recommendations2();

	}

	@Test
	public void testaSimilaridadeEuclidiana() {
		Critico rose = new Critico(Reviewer.Rose);
		Critico seymour = new Critico(Reviewer.Seymour);
		rose.criticas = encontraCriticasDe(rose);
		seymour.criticas = encontraCriticasDe(seymour);

		double similaridadeEuclidiana = recommendations2.similaridadeEuclidiana(rose, seymour);

		assertEquals(0.294298055086, similaridadeEuclidiana, 0.000000000001);
	}

	private Set<Critica> encontraCriticasDe(Critico critico) {
		return criticas.stream()
				.filter(critica -> critica.critico.equals(critico))
				.collect(Collectors.toSet());
	}

	@Test
	public void testaSimilaridadeComPearson() {
		Critico rose = new Critico(Reviewer.Rose);
		Critico seymour = new Critico(Reviewer.Seymour);
		rose.criticas = encontraCriticasDe(rose);
		seymour.criticas = encontraCriticasDe(seymour);


		double similaridadePearson = recommendations2.similaridadePearson(rose, seymour);

		assertEquals(0.396059017191, similaridadePearson, 0.000000000001);
	}

	@Test
	public void rankingBaseadoEmUsuarioComPearson() {
		Critico critico = new Critico(Reviewer.Toby);
		critico.criticas = encontraCriticasDe(critico);

		List<Rank2> ranking = recommendations2.comparaTodosCom(critico, todosOsCriticos());
		assertEquals(0.9912407071619302, ranking.get(0).similarity, 0.000000000001);
		assertEquals(0.9244734516419048, ranking.get(1).similarity, 0.000000000001);
		assertEquals(0.8934051474415647, ranking.get(2).similarity, 0.000000000001);
	}

	@Test
	public void deveTer3EmComum() {
		Critico toby = new Critico(Reviewer.Toby);
		toby.criticas = encontraCriticasDe(toby);

		Critico rose = new Critico(Reviewer.Rose);
		rose.criticas = encontraCriticasDe(rose);

		toby.criticas.retainAll(rose.criticas);
		assertEquals(3, toby.criticas.size());

		rose.criticas.retainAll(toby.criticas);
		assertEquals(3, rose.criticas.size());
	}

	@Test
	public void semEfeitoColateral() {
		Critico toby = new Critico(Reviewer.Toby);
		toby.criticas = encontraCriticasDe(toby);

		Critico rose = new Critico(Reviewer.Rose);
		rose.criticas = encontraCriticasDe(rose);

		Set<Critica> criticasT = new HashSet<>(toby.criticas);
		Set<Critica> criticasR = new HashSet<>(rose.criticas);

		criticasT.retainAll(criticasR);
		criticasR.retainAll(criticasT);

		assertEquals(3, criticasR.size());
		assertEquals(3, criticasT.size());

		assertEquals(3, toby.criticas.size());
		assertEquals(6, rose.criticas.size());
	}

	@Test
	public void deveRecomendarFilmes() {
		Critico critico = new Critico(Reviewer.Toby);
		critico.criticas = encontraCriticasDe(critico);

		List<RankPorFilme> ranking = recommendations2.encontraRecomendacoes(critico, todosOsCriticos());

		assertEquals(3.3477895267131013, ranking.get(0).similaridade, 0.000000000001);
		assertEquals(2.8325499182641614, ranking.get(1).similaridade, 0.000000000001);
		assertEquals(2.5309807037655645, ranking.get(2).similaridade, 0.000000000001);
	}

	private List<Critico> todosOsCriticos() {
		return new ArrayList<Critico>() {
			private static final long serialVersionUID = 6158524868409753618L;

			{
				add(criaCritico(Reviewer.LaSalle));
				add(criaCritico(Reviewer.Matthews));
				add(criaCritico(Reviewer.Phillips));
				add(criaCritico(Reviewer.Puig));
				add(criaCritico(Reviewer.Rose));
				add(criaCritico(Reviewer.Seymour));
				add(criaCritico(Reviewer.Toby));
			}
		};
	}

	private Critico criaCritico(Reviewer critico) {
		Critico temp = new Critico(critico);
		return new Critico(critico, encontraCriticasDe(temp));
	}
}


