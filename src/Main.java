import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {
	public static void main(String[] args) {
		
		int num = 10;
		
		
		MyNumber myNumber;
		
		myNumber = () -> 12.33;
		
		System.out.println(myNumber.getValue());
		
		MyNumber randomNumber = () -> Math.random() * 21;
		
		System.out.println("Losowa: "+randomNumber.getValue());
		System.out.println("Losowa: "+randomNumber.getValue());
		
		MyNumber staticValuePI = () -> 3.14;
		System.out.println("PI: "+staticValuePI.getValue());
		
		NumericTest isEven = (a) -> a%2==0;
		System.out.println("Czy parzyste jest 112? "+isEven.test(112));
		System.out.println("Czy parzyste jest 5? "+isEven.test(5));
		
		NumericTest fromRange = (a) -> a>=10 && a <= 100;
		System.out.println("Czy 55 jest z przedziału 10-100? "+fromRange.test(55));
		System.out.println("Czy -11 jest z przedziału 10-100? "+fromRange.test(-11));
		
		NumericFunc sum = (tab) -> {
			int s = 0;
			for(int a : tab) {
				s+=a;
			}
			return s;
		};
		
		System.out.println("Suma liczba 2+2 = "+sum.getValue(new int[]{2,2}));
		System.out.println("Suma liczba 5+2 = "+sum.getValue(new int[]{5,2}));
		
		String head = "-------------\nDzień dobry,\n\n";
		String footer = "\n\nPozdrawiam,\nMarian Kowalski.\n-------------";
		SomeFunc<String> email = (message) -> head+message+footer;
		System.out.println(email.func("piszę do Państwa w sprawie..."));
		
		
		SomeFunc<Integer> reverse = (value) -> -value;
		System.out.println("Odwrotność -5, to "+reverse.func(-5));
		System.out.println("Odwrotność 22, to "+reverse.func(22));
		System.out.println("Odwrotność 8123, to "+reverse.func(8123));
		
		
		runNumericTest((a) -> a%2==0, 4);
		runNumericTest((a) -> a%3==0, -1);
		
		DataGenerator<Character[]> charsTable = (length) -> {
			if(length <= 0) {
				throw new EmptyException();
			}
			Character[] tab = new Character[length];
			for (int i = 0; i < length; i++) {
				tab[i] = (char)(i+30);
			}
			return tab;
		};
		
		Character[] chars;
		try {
			chars = charsTable.generate(22);
		} catch (EmptyException e) {
			chars = null;
		}
		
		for (Character character : chars) {
			System.out.println(character);
		}
		
		try {
			chars = charsTable.generate(-22);
		} catch (EmptyException e) {
			System.out.println(e.getMessage());
			chars = null;
		}
		
		if(chars == null) {
			System.out.println("Table is empty.");
		}
		
		MyNumber m = () -> {
			return num;
		};
		
		UnaryOperator<Integer> decrement = (n) -> n-1;
		System.out.println("Dekrementacja 5-- = "+decrement.apply(5));
		
		BinaryOperator<Integer> sumFunc = (a, b) -> a+b;
		System.out.println("Suma 3+6 = "+sumFunc.apply(3, 6));
		
		Consumer<String> przywitaj = (name) -> System.out.println("Witaj "+name+".");
		przywitaj.accept("Marian");
		przywitaj.accept("Kazik");

		List<Osoba> osoby = new ArrayList<>();
		osoby.add(new Osoba("Marcin", "Kwiatkowski", 22));
		osoby.add(new Osoba("Kasia", "Lewińska", 55));
		osoby.add(new Osoba("Filip", "Królikowski", 81));
		
		Supplier<Osoba> wylosujOsobe = () -> {
			int rand = (int) (Math.random() * ( osoby.size() - 0 ));
			return osoby.get(rand);
		};
		System.out.println("Wylosowana osoba, to "+wylosujOsobe.get());
		
		
		Function<String, Integer> numberOfChar = (n) -> {
			return n.length();
		};

		System.out.println("Długość słowa: "+numberOfChar.apply("Szymon"));
		System.out.println("Długość słowa: "+numberOfChar.apply("Jan"));
		
		Predicate<String> isInLenght = (n) -> {
			return n.length() < 20;
		};
		
		System.out.println("Czy słowo jest odpowiedniej długości? "+isInLenght.test("MASdfaskdfjlasdkflasdflksadfkasdf"));
		System.out.println("Czy słowo jest odpowiedniej długości? "+isInLenght.test("MASdfaskdfjlas"));
		
	}

	private static void runNumericTest(NumericTest numericTest, int i) {
		System.out.println(numericTest.test(i));
	}
}
