
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;   
import java.util.TreeMap;  
import java.util.Iterator;  
import java.util.Comparator;
import java.util.Arrays;

public class Taller4 {

    static String[] getNombresDeCasas(int size) {
        String[] response = new String[size];
        Random random = new Random();

        String[] prefijos = { "la", "una" };
        String[] mid = { "cueva", "casa", "mansión" };
        String[] suf = {
                "de la montaña",
                "del bosque",
                "del pantano",
                "del desierto",
                "de Drácula",
                "de la bruja",
                "del vampiro",
        };

        for (int i = 0; i < size; i++) {
            int randPref = random.nextInt(prefijos.length);
            int randMid = random.nextInt(mid.length);
            int randSuf = random.nextInt(suf.length);
            response[i] = prefijos[randPref] + " " + mid[randMid] + " " + suf[randSuf];
        }
        return response;
    }

    static int[] getPrices(int size) {
        Scanner sc = new Scanner(System.in);
        int[] response = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            System.out.println("Ingrese el precio de la casa:");
            response[i] = sc.nextInt();
        }
        return response;
    }


    static int[] getPeso(int size) {
        int[] response = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            response[i] = random.nextInt(5);
        }
        return response;
    }

    public static void main(String[] args) {
        // Asociadas
        String[] nombresDeCasas = getNombresDeCasas(10); //obtener 10 nombres aleatoriamente
        int[] prices = getPrices(10); //obtener 10 precios aleatorios

        // Aleatoria
        String[] nombreBarrios = new String[5];
        Scanner sc = new Scanner(System.in);
        for(int p = 0; p < 5; p++){
            System.out.println("Ingrese el nombre del barrio");
            String barrio = sc.next();
            nombreBarrios[p] = barrio;
        }

        HashMap<String, Integer> ventas = new HashMap<String, Integer>(); //
        
        for (String barrio : nombreBarrios) {
            ventas.put(barrio, 0);
        }

        int userEntry = 0;
        Random rnd = new Random();
        Scanner scner = new Scanner(System.in);


        HashMap<String, Integer> peso = new HashMap<String, Integer>(); //
        
        for (String barrio : nombreBarrios) {
            peso.put(barrio, rnd.nextInt(1,3));
        }

        // 0 para descartar
        // 1 para comprar
        // 2 para finalizar


        System.out.println("¡Hola te doy la bienvenida a Tinder House!");
        System.out.println("El juego consiste en hacer match con cada casa que te guste");
        System.out.println("presiona 0 para descartar, 1 para comprar y 2 para terminar el juego");
        do {
            // Aqui va el juego
            int randCasa = rnd.nextInt(nombresDeCasas.length);
            int randBarrio = rnd.nextInt(nombreBarrios.length);
            int randPrice = rnd.nextInt(prices.length);
            int preciopeso = peso.get(nombreBarrios[randBarrio])*prices[randPrice];
            System.out.println("------*-------");
            System.out.println(nombresDeCasas[randCasa]);
            System.out.println(nombreBarrios[randBarrio]);
            System.out.println(preciopeso);
            System.out.println("------*-------");
            userEntry = scner.nextInt();
            if (userEntry == 1) {
                int actualVenta = ventas.get(nombreBarrios[randBarrio]);
                int newVenta = actualVenta + prices[randPrice];
                ventas.replace(nombreBarrios[randBarrio], newVenta);
            }
        } while (userEntry != 2);

        System.out.println("Reporte de ventas:");

        for (String barrio: ventas.keySet()){
            System.out.println(barrio+ " vendió "+ ventas.get(barrio));
        }    

        System.out.println("Barrios ordenados por ventas: ");

        Object[] a = ventas.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
            });
        for (Object e : a) {
            System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "+ ((Map.Entry<String, Integer>) e).getValue());
        }
            
    }
}
