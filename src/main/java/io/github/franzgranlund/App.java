package io.github.franzgranlund;

public class App
{
    private static Either<String, Integer> compute(Integer i) {
        return i > 5 ? Either.left("I'm sorry Dave, I'm afraid I can't do that") : Either.right(i);
    }

    static class Cat {
        final String value = "cat";
    }
    static class Bike {
        final String value = "bike";
    }

    public static void main( String[] args )
    {
        // Prints 'anEither is a bike'
        Either<Cat, Bike> anEither = Either.right(new Bike());
        if (anEither.isRight()) {
            System.out.println("anEither is a " + anEither.getRight().value);
        }

        // Prints 'Computing successful' and sets foldResult to true
        Boolean foldResult = compute(4).fold(lhv -> {
            System.out.println(lhv);
            return false;
        }, rhv -> {
            System.out.println("Computing successful");
            return true;
        });

        // Prints 'biMapResult is a cat'
        Either<Cat, Bike> biMapResult = compute(6).bimap(lhv -> new Cat(), rhv -> new Bike());
        if (biMapResult.isLeft()) {
            System.out.println("biMapResult is a " + biMapResult.getLeft().value);
        }

        // Creates an Left 'Bike' Either since the cond-predicate is false
        // Prints 'condResult is a bike'
        Either<Bike, Cat> condResult = Either.cond(false, new Cat(), new Bike());
        if (condResult.isLeft()) {
            System.out.println("condResult is a " + condResult.getLeft().value);
        }

        // Prints 'I'm sorry Dave, I'm afraid I can't do that'
        compute(6).consume(System.out::println, rhv -> System.out.println("Batman"));
    }
}
