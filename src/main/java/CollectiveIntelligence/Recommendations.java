package CollectiveIntelligence;

import java.util.*;

public class Recommendations {

    private List<Review> criticas = Review.avaliacoes();

    public List<Rank> comparaTodosCom(Reviewer reviewer) {
        Review principal = findBy(reviewer);
        criticas.remove(principal);
        List<Rank> ranking = new ArrayList<>();
        for (Review review : criticas) {
            ranking.add(new Rank(review.critico, similaridadePearson(principal, review)));
        }
        ranking.sort((Comparator.comparingDouble(Rank::getSimilarity).reversed()));

        return ranking;
    }

    public static double euclideanDistanceScore(double nota1, double nota2) {
        return Math.pow(nota1 - nota2, 2);
    }

    public double similaridadeEuclidiana(Review review, Review review1) {
        double somatorioDasSimilaridades = review.criticas.stream().map(rates1 -> {
            Rates rates2 = getRateEqualTo(rates1, review1);

            if (rates2 != null) {
                return euclideanDistanceScore(rates1.nota, rates2.nota);

            }
            return 0;
        }).mapToDouble(Number::doubleValue).sum();
        return 1 / (1 + Math.sqrt(somatorioDasSimilaridades));
    }


    public double similaridadePearson(Review pessoa1, Review pessoa2) {
        long n = encontraNumeroDeAvaliacoesEmComum(pessoa1, pessoa2);
        double totalNotasP1 = obterSomaDasNotasEmComum(pessoa1, pessoa2);
        double totalNotasP2 = obterSomaDasNotasEmComum(pessoa2, pessoa1);
        double totalDosQuadradosP1 = obterSomaDosQuadradosEmComum(pessoa1, pessoa2);
        double totalDosQuadradosP2 = obterSomaDosQuadradosEmComum(pessoa2, pessoa1);
        double totalDosProdutos = obterSomaDosProdutosEmComum(pessoa1, pessoa2);
//        double pearsonScore = totalDosProdutos - (totalNotasP1 * totalNotasP2 / n);
//        double den = Math.sqrt(((totalDosQuadradosP1 - Math.pow(totalNotasP1, 2)) / n) * ((totalDosQuadradosP2 - Math.pow(totalNotasP2, 2)) / n));
        double pearsonScore = (n * totalDosProdutos) - (1.0 * totalNotasP1 * totalNotasP2);
        double den1 = Math.sqrt((n * totalDosQuadradosP1) - Math.pow(totalNotasP1, 2));
        double den2 = Math.sqrt((n * totalDosQuadradosP2) - Math.pow(totalNotasP2, 2));
        double den = 1.0 * den1 * den2;
        if (den == 0) return 0;
        pearsonScore = pearsonScore / den;
        return pearsonScore;
    }

    private double obterSomaDasNotasEmComum(Review pessoa, Review outraPessoa) {
        return pessoa.criticas.stream()
                .map(rate -> getRateEqualTo(rate, outraPessoa) == null ? 0.0 : rate.nota)
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    private double obterSomaDosQuadradosEmComum(Review pessoa, Review outraPessoa) {
        return pessoa.criticas.stream()
                .map(rate -> getRateEqualTo(rate, outraPessoa) == null ? 0.0 : Math.pow(rate.nota, 2))
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    private double obterSomaDosProdutosEmComum(Review pessoa, Review outraPessoa) {
        return pessoa.criticas.stream()
                .map(rate -> {
                    Rates rate2 = getRateEqualTo(rate, outraPessoa);
                    if (rate2 == null) return 0.0;
                    else
                        return rate.nota * rate2.nota;
                })
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    private long encontraNumeroDeAvaliacoesEmComum(Review pessoa1, Review pessoa2) {
        return pessoa1.criticas.stream()
                .filter(rate -> getRateEqualTo(rate, pessoa2) != null)
                .count();
    }

    private Rates getRateEqualTo(Rates rate, Review pessoa2) {
        return pessoa2.criticas.stream()
                .filter(rates2 -> rate.filme == rates2.filme)
                .findFirst()
                .orElse(null);
    }

    private Review findBy(Reviewer reviewer) {
        return criticas.stream()
                .filter(review -> review.critico == reviewer)
                .findFirst()
                .orElseGet(null);
    }

}
