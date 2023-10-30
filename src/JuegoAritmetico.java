import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JuegoAritmetico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("¡Bienvenido al Juego Aritmético!");

        int totalPreguntas = 10;
        int preguntasCorrectas = 0;
        int preguntasIncorrectas = 0;

        List<String> historialPreguntas = new ArrayList<>();

        for (int i = 0; i < totalPreguntas; i++) {
            int nivelDificultad = elegirNivelDificultad();

            int numero1 = generarNumeroAleatorio(nivelDificultad);
            int numero2 = generarNumeroAleatorio(nivelDificultad);

            int tipoOperacion = elegirTipoOperacion();

            System.out.print("¿Cuánto es " + numero1);

            switch (tipoOperacion) {
                case 1:
                    System.out.print(" + ");
                    break;
                case 2:
                    System.out.print(" - ");
                    break;
                case 3:
                    System.out.print(" * ");
                    break;
                case 4:
                    System.out.print(" / ");
                    break;
            }

            System.out.print(numero2 + "? ");

            long tiempoInicio = System.currentTimeMillis();

            double respuestaUsuario = scanner.nextDouble();
            double respuestaCorrecta = calcularRespuestaCorrecta(numero1, numero2, tipoOperacion);

            long tiempoFin = System.currentTimeMillis();
            long tiempoTotal = tiempoFin - tiempoInicio;

            historialPreguntas.add("Pregunta: " + numero1 + " " + obtenerSimboloOperacion(tipoOperacion) + " " + numero2 +
                    " Respuesta del estudiante: " + respuestaUsuario + " Respuesta correcta: " + respuestaCorrecta +
                    " Tiempo de respuesta: " + tiempoTotal + " ms");

            if (Math.abs(respuestaUsuario - respuestaCorrecta) < 0.0001) {
                System.out.println(obtenerComentarioPositivo());
                preguntasCorrectas++;
            } else {
                System.out.println("Respuesta incorrecta. La respuesta correcta es: " + respuestaCorrecta);
                preguntasIncorrectas++;
            }
        }

        // Calcular el porcentaje de respuestas correctas
        double porcentajeCorrectas = (double) preguntasCorrectas / totalPreguntas * 100;

        System.out.println("¡Juego completado!");
        System.out.println("Felicidades, has respondido correctamente a " + preguntasCorrectas + " preguntas de " + totalPreguntas + ".");
        System.out.println("Porcentaje de respuestas correctas: " + porcentajeCorrectas + "%");

        // Mostrar historial de preguntas
        System.out.println("\nHistorial de preguntas y respuestas:");
        for (String pregunta : historialPreguntas) {
            System.out.println(pregunta);
        }

        scanner.close();
    }

    private static int elegirNivelDificultad() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige el nivel de dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Medio");
        System.out.println("3. Difícil");
        return scanner.nextInt();
    }

    private static int generarNumeroAleatorio(int nivelDificultad) {
        Random random = new Random();
        int rango = 10; // Rango predeterminado para nivel fácil
        if (nivelDificultad == 2) {
            rango = 50; // Rango para nivel medio
        } else if (nivelDificultad == 3) {
            rango = 100; // Rango para nivel difícil
        }
        return random.nextInt(rango) + 1;
    }

    private static int elegirTipoOperacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige el tipo de operación:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicación");
        System.out.println("4. División");
        return scanner.nextInt();
    }

    private static double calcularRespuestaCorrecta(int numero1, int numero2, int tipoOperacion) {
        switch (tipoOperacion) {
            case 1:
                return numero1 + numero2;
            case 2:
                return numero1 - numero2;
            case 3:
                return numero1 * numero2;
            case 4:
                return (double) numero1 / numero2;
            default:
                return 0;
        }
    }

    private static String obtenerSimboloOperacion(int tipoOperacion) {
        switch (tipoOperacion) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "*";
            case 4:
                return "/";
            default:
                return "";
        }
    }

    private static String obtenerComentarioPositivo() {
        String[] comentarios = {"¡Muy bien!", "¡Excelente!", "¡Buen trabajo!", "¡Sigue así!"};
        Random random = new Random();
        int indice = random.nextInt(comentarios.length);
        return comentarios[indice];
    }
}
