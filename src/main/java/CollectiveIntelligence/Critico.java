package CollectiveIntelligence;

import java.util.Objects;
import java.util.Set;

public class Critico {
	public Reviewer critico;
	public Set<Critica> criticas;

	public Critico(Reviewer critico) {
		this.critico = critico;
	}

	public Critico(Reviewer critico, Set<Critica> criticas) {
		this.critico = critico;
		this.criticas = criticas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Critico critico1 = (Critico) o;
		return critico == critico1.critico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(critico);
	}
}
