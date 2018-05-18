package CollectiveIntelligence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Critica {

	public Critico critico;
	public Filme filme;
	public Double nota;

	public Critica(Critico critico, Filme filme, Double nota) {
		this.critico = critico;
		this.filme = filme;
		this.nota = nota;
	}

	public static List<Critica> criticas() {
		List<Critica> criticas = new ArrayList<>();
		Review.avaliacoes().forEach(review -> criticas.addAll(criaLista(review)));
		return criticas;
	}

	private static List<Critica> criaLista(Review review) {
		return review.criticas.stream()
				.map(rates -> {
					Critico critico = new Critico(review.critico);
					return new Critica(critico, rates.filme, rates.nota);
				}).collect(Collectors.toList());
	}

	public Double getNota() {
		return nota;
	}

	public Filme getFilme() {
		return filme;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Critica critica = (Critica) o;
		return filme == critica.filme;
	}

	@Override
	public int hashCode() {

		return Objects.hash(filme);
	}
}
