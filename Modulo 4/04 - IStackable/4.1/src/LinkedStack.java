import java.util.LinkedList;

public class LinkedStack implements IStackable{
	private LinkedList<Integer> listaEncadeada = new LinkedList<Integer>();
	
	@Override
	public int size() {
		return listaEncadeada.size();
	}

	@Override
	public void push(int v) {
		listaEncadeada.addFirst(v);
	}

	@Override
	public int pop() {
		return (int) listaEncadeada.removeFirst();
	}

}
