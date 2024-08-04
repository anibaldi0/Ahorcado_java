import java.util.Random;
import java.util.Scanner;
import java.io.Console;

public class Ahorcado {
    public static void main(String[] args) throws Exception {

        limpiarPantalla();
        
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        // Cambio de color
        String GREEN = "\u001B[32m";
        String WHITE = "\u001B[0m";
        String BLUE = "\u001B[34m";
        String CYAN = "\u001B[36m";
        String RED = "\u001B[31m";
        String MAGENTA = "\u001B[35m";
        String YELLOW = "\033[0;33m";
        String RESET = "\u001B[0m";

        // Array de palabras para ser adivinadas
        String[] palabras = {"abandonados","adaptacion","agricultura","alimentaria","ambulatorio","aniversario","barricadas","biblioteca","caracteres",
            "climatizado","concentrado","consecuente","contundente","desafiante","desinfectar","efectividad","empresario","enfermedad","entregador",
            "examenes","familiaridad","generadores","informacion","instrucciones","matrimonio","medicamento","modernidad","observatorio","participante",
            "pesticidas","preocupante","prolongado","recurrencia","recomendado","responsable","sentimientos","subordinado","transformar","tolerancia",
            "voluntario"
        };

        String mensaje = MAGENTA + "READY PLAYER ONE\n" + WHITE;
        StringBuilder mensajeConEspacios = new StringBuilder();
        // Añadir 5 espacios antes del mensaje
        mensajeConEspacios.append("\n").append(" ".repeat(10)).append(mensaje);
        System.out.println(mensajeConEspacios.toString());
        Thread.sleep(2000);

        // Seleccionar una palabra al azar
        Random rand = new Random();
        String palabraElegida = palabras[rand.nextInt(palabras.length)];
        
        // Mostrar la longitud de la palabra
        int longitud = palabraElegida.length();
        System.out.println("Longitud de la palabra: " + MAGENTA + longitud + WHITE);
        
        // Reemplazar cada caracter con un guion bajo
        StringBuilder palabraOculta = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            palabraOculta.append("_");
        }
        
        System.out.println("Palabra oculta: " + MAGENTA + palabraOculta.toString() + WHITE + "\n");

        Thread.sleep(1000);

        int intentosMaximos = 5;
        int intentos = 0;
        boolean palabraAdivinada = false;

        char[] letrasAdivinadas = new char[palabraElegida.length()];

        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
        }

        while (intentos < intentosMaximos && !palabraAdivinada) {

            System.out.print(CYAN + "PLAYER ONE: " + WHITE + "Ingrese una letra para adivinar la palabra secreta: ");
            String letraIngresada = scanner.nextLine().trim();

            // Validar la entrada
            if (letraIngresada.length() != 1 || !letraIngresada.matches("[a-zA-Z]")) {
                System.out.println(RED + "Error: Por favor, ingrese solo una letra (A-Z o a-z)." + WHITE);
                continue; // Volver a solicitar la entrada
            }

            char letra = Character.toLowerCase(letraIngresada.charAt(0));
            boolean letraCorrecta = false;

            for (int i = 0; i < palabraElegida.length(); i++) {
                if (palabraElegida.charAt(i) == letra) {
                    letrasAdivinadas[i] = letra;
                    letraCorrecta = true;
                }
            }

            if (!letraCorrecta) {
                limpiarPantalla();
                intentos++;
                int intentosRestantes = intentosMaximos - intentos;
                System.out.println("Letra incorrecta. Le quedan " + intentosRestantes + " intentos");
            } else {
                limpiarPantalla();
                System.out.println("Letra correcta!");
            }

            System.out.println("Palabra actual: " + MAGENTA + String.valueOf(letrasAdivinadas) + WHITE + "\n");

            if (String.valueOf(letrasAdivinadas).equals(palabraElegida)) {
                palabraAdivinada = true;
                System.out.println(GREEN + "PLAYER ONE WINS! " + WHITE + "La palabra secreta es: " + GREEN + palabraElegida + WHITE + "\n");
            }
            // Mensaje final si se exceden los intentos
            if (intentos >= intentosMaximos) {
                System.out.println(RED + "GAME OVER." + WHITE);
                System.out.println("La palabra secreta era: " + MAGENTA + palabraElegida + WHITE + "\n");
            }

        }
        scanner.close();

    }
    public static void limpiarPantalla(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
