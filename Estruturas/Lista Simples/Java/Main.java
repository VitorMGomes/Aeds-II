import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Lista list = new Lista();

            // Insert elements
            list.insertEnding(10);
            list.insertEnding(20);
            list.insertEnding(30);
            list.insertBeginning(5);
            list.insert(15, 2);

            // Print the list
            System.out.println("List after inserts:");
            list.printList();

            // Remove elements
            System.out.println("Removed element from beginning: " + list.removeBeginning());
            System.out.println("Removed element from end: " + list.removeEnding());
            System.out.println("Removed element from position 1: " + list.remove(1));

            // Print the list
            System.out.println("List after removals:");
            list.printList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
