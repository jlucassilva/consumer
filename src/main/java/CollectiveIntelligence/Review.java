package CollectiveIntelligence;

import java.util.ArrayList;
import java.util.List;

public class Review {
    public Reviewer critico;
    public List<Rates> criticas = new ArrayList<>();

    public Review(Reviewer critico, List<Rates> criticas) {
        this.critico = critico;
        this.criticas = criticas;
    }

    public static List<Review> avaliacoes() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(Reviewer.Rose, new ArrayList<Rates>() {{
            add(new Rates(Filme.LADY_WATER, 2.5));
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 3.5));
            add(new Rates(Filme.JUST_MY_LUCK, 3.0));
            add(new Rates(Filme.SUPERMAN_RETURNS, 3.5));
            add(new Rates(Filme.YOU_ME_DUPREE, 2.5));
            add(new Rates(Filme.NIGHT_LISTENER, 3.0));
        }}));
        reviews.add(new Review(Reviewer.Seymour, new ArrayList<Rates>() {{
            add(new Rates(Filme.LADY_WATER, 3.0));
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 3.5));
            add(new Rates(Filme.JUST_MY_LUCK, 1.5));
            add(new Rates(Filme.SUPERMAN_RETURNS, 5.0));
            add(new Rates(Filme.YOU_ME_DUPREE, 3.5));
            add(new Rates(Filme.NIGHT_LISTENER, 3.0));
        }}));
        reviews.add(new Review(Reviewer.Phillips, new ArrayList<Rates>() {{
            add(new Rates(Filme.LADY_WATER, 2.5));
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 3.0));
            add(new Rates(Filme.SUPERMAN_RETURNS, 3.5));
            add(new Rates(Filme.NIGHT_LISTENER, 4.0));
        }}));
        reviews.add(new Review(Reviewer.Puig, new ArrayList<Rates>() {{
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 3.5));
            add(new Rates(Filme.JUST_MY_LUCK, 3.0));
            add(new Rates(Filme.SUPERMAN_RETURNS, 4.0));
            add(new Rates(Filme.YOU_ME_DUPREE, 2.5));
            add(new Rates(Filme.NIGHT_LISTENER, 4.5));
        }}));
        reviews.add(new Review(Reviewer.LaSalle, new ArrayList<Rates>() {{
            add(new Rates(Filme.LADY_WATER, 3.0));
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 4.0));
            add(new Rates(Filme.JUST_MY_LUCK, 2.0));
            add(new Rates(Filme.SUPERMAN_RETURNS, 3.0));
            add(new Rates(Filme.YOU_ME_DUPREE, 2.0));
            add(new Rates(Filme.NIGHT_LISTENER, 3.0));
        }}));
        reviews.add(new Review(Reviewer.Matthews, new ArrayList<Rates>() {{
            add(new Rates(Filme.LADY_WATER, 3.0));
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 4.0));
            add(new Rates(Filme.SUPERMAN_RETURNS, 5.0));
            add(new Rates(Filme.YOU_ME_DUPREE, 3.5));
            add(new Rates(Filme.NIGHT_LISTENER, 3.0));
        }}));
        reviews.add(new Review(Reviewer.Toby, new ArrayList<Rates>() {{
            add(new Rates(Filme.SNAKES_ON_A_PLANE, 4.5));
            add(new Rates(Filme.SUPERMAN_RETURNS, 4.0));
            add(new Rates(Filme.YOU_ME_DUPREE, 1.0));
        }}));

        return reviews;
    }

}
