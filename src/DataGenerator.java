
public interface DataGenerator<T> {
	T generate(int length) throws EmptyException;
}
