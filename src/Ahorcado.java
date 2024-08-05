import java.util.Random;
import java.util.Scanner;
import java.io.Console;

public class Ahorcado {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        boolean jugarOtraPartida = true;
        
        while (jugarOtraPartida) {
            limpiarPantalla();
            iniciarJuego(scanner, console);

            // Preguntar si desea jugar otra partida
            System.out.print("¿Desea jugar otra partida? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (!respuesta.equals("s")) {
                jugarOtraPartida = false;
                System.out.println(Colores.GREEN + "\nGracias por elegir nuestro software\n" + Colores.WHITE);
            }
        }

        scanner.close();
    }

    public static void iniciarJuego(Scanner scanner, Console console) throws InterruptedException {
        // Utilizar las constantes de colores desde la clase Colores
        String mensaje = Colores.MAGENTA + "READY PLAYER ONE\n" + Colores.WHITE;
        StringBuilder mensajeConEspacios = new StringBuilder();
        // Añadir 5 espacios antes del mensaje
        mensajeConEspacios.append("\n").append(" ".repeat(10)).append(mensaje);
        System.out.println(mensajeConEspacios.toString());
        Thread.sleep(2000);

        // Array de palabras para ser adivinadas
        String[] palabras = {
            "abandonados","adaptacion","agricultura","alimentaria","ambulatorio","aniversario","barricadas","biblioteca","caracteres",
            "climatizado","concentrado","consecuente","contundente","desafiante","desinfectar","efectividad","empresario","enfermedad","entregador",
            "examenes","familiaridad","generadores","informacion","instrucciones","matrimonio","medicamento","modernidad","observatorio","participante",
            "pesticidas","preocupante","prolongado","recurrencia","recomendado","responsable","sentimientos","subordinado","transformar","tolerancia",
            "voluntario"
        };

        // Seleccionar una palabra al azar
        Random rand = new Random();
        String palabraElegida = palabras[rand.nextInt(palabras.length)].toUpperCase(); // Convertir la palabra a mayúsculas
        
        // Mostrar la longitud de la palabra
        int longitud = palabraElegida.length();
        System.out.println("Longitud de la palabra: " + Colores.MAGENTA + longitud + Colores.WHITE);
        
        // Reemplazar cada caracter con un guion bajo
        StringBuilder palabraOculta = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            palabraOculta.append("_");
        }
        
        System.out.println("Palabra oculta: " + Colores.MAGENTA + palabraOculta.toString() + Colores.WHITE + "\n");

        Thread.sleep(1000);

        int intentosMaximos = 5;
        int intentos = 0;
        boolean palabraAdivinada = false;

        char[] letrasAdivinadas = new char[palabraElegida.length()];

        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
        }

        while (intentos < intentosMaximos && !palabraAdivinada) {

            System.out.print(Colores.CYAN + "PLAYER ONE: " + Colores.WHITE + "Ingrese una letra para adivinar la palabra secreta: ");
            String letraIngresada = scanner.nextLine().trim().toUpperCase(); // Convertir la letra ingresada a mayúscula

            // Validar la entrada
            if (letraIngresada.length() != 1 || !letraIngresada.matches("[A-Z]")) {
                System.out.println(Colores.RED + "Error: Por favor, ingrese solo una letra." + Colores.WHITE);
                continue; // Volver a solicitar la entrada
            }

            char letra = letraIngresada.charAt(0);
            boolean letraCorrecta = false;

            for (int i = 0; i < palabraElegida.length(); i++) {
                if (palabraElegida.charAt(i) == letra) {
                    letrasAdivinadas[i] = letra;
                    letraCorrecta = true;
                }
            }
            
            int intentosRestantes = intentosMaximos - intentos;
            
            if (!letraCorrecta) {
                limpiarPantalla();
                intentos++;
                System.out.println(Colores.RED + "Letra incorrecta." + Colores.WHITE + " Le quedan " + (intentosMaximos - intentos) + " intentos");
            } else {
                limpiarPantalla();
                System.out.println(Colores.GREEN + "Letra correcta! " + Colores.WHITE + "Le quedan " + intentosRestantes + " intentos");
            }

            System.out.println("Palabra actual: " + Colores.MAGENTA + String.valueOf(letrasAdivinadas) + Colores.WHITE + "\n");

            if (String.valueOf(letrasAdivinadas).equals(palabraElegida)) {
                palabraAdivinada = true;
                System.out.println(Colores.GREEN + "PLAYER ONE WINS! " + Colores.WHITE + "La palabra secreta es: " + Colores.GREEN + palabraElegida + Colores.WHITE + "\n");
            }

            // Mensaje final si se exceden los intentos
            if (intentos >= intentosMaximos) {
                System.out.println(Colores.RED + "GAME OVER." + Colores.WHITE);
                System.out.println("La palabra secreta era: " + Colores.MAGENTA + palabraElegida + Colores.WHITE + "\n");
            }

        }
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Clase interna estática para los colores
    public static class Colores {
        public static final String GREEN = "\u001B[32m";
        public static final String WHITE = "\u001B[0m";
        public static final String BLUE = "\u001B[34m";
        public static final String CYAN = "\u001B[36m";
        public static final String RED = "\u001B[31m";
        public static final String MAGENTA = "\u001B[35m";
        public static final String YELLOW = "\033[0;33m";
        public static final String RESET = "\u001B[0m";
    }
}
