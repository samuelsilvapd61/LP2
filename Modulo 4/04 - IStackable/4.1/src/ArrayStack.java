import java.util.ArrayList;

public class ArrayStack implements IStackable{
	private ArrayList<Integer> listaArray = new ArrayList<Integer>();
	
	@Override
	public int size() {
		return listaArray.size();
	}

	@Override
	public void push(int v) {
		listaArray.add(v);
	}

	@Override
	public int pop() {
		return (int) listaArray.remove(listaArray.size()-1);
	}

}
