import java.util.ArrayList;

abstract class Animal {
  public abstract void animalSound();
}

class Pig extends Animal {

	@Override
	public void animalSound() {
		System.out.println("The pig says: wee wee");
	}
}

class Dog extends Animal {

	@Override
	public void animalSound() {
		System.out.println("The dog says: bow wow");
	}
}

class Main {
  public static void main(String[] args) {
  	ArrayList <Animal> animal = new ArrayList<Animal>();
  	animal.add(new Dog());
  	animal.add(new Pig());
  	animal.add(new Pig());
  	animal.add(new Dog());
  	
  	for (Animal a: animal) {
  		a.animalSound();
  	}
        
  }
}