package uvg.ed.gt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Association<String, String>> dictionary = new BinaryTree<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("diccionario.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                if (st.countTokens() != 2) {
                    // Si la línea no tiene el formato esperado, la ignoramos y pasamos a la siguiente línea
                    System.out.println("Formato incorrecto en la línea: " + line);
                    continue;
                }
                String key = st.nextToken().trim().substring(1);
                String value = st.nextToken().trim().substring(0);
                dictionary.insert(new Association<>(key.toLowerCase(), value.toLowerCase()));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Diccionario en In-Order:");
        dictionary.inorder();

        System.out.println("\n\nTraducción del texto:");

        try {
            BufferedReader br = new BufferedReader(new FileReader("texto.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    String word = st.nextToken().toLowerCase();
                    boolean translated = false;
                    for (Association<String, String> association : dictionary.inOrderTraversal()) {
                        if (word.contains(association.getKey())) {
                            System.out.print(association.getValue() + " ");
                            translated = true;
                            break;
                        }
                    }
                    if (!translated) {
                        System.out.print("*" + word + "* ");
                    }
                }
                System.out.println();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


