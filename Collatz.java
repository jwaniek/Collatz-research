import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

/**
 * BLOCK/ITERATE NEEDS FIXING
 */

public class Collatz
{
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int l = Integer.parseInt(scanner.nextLine());
        int[] facts = factorise(l);

        System.out.println("Block: " + l + " -> " + block(l));

        scanner.close();
    }
    
    private static int iterate(int n) {
        if (isEven(n))
            n = n/2;
        else
            n = 3*n+1;
        
        return n;
    }
    
    private static int block(int n) {
        do {
            n = iterate(n);
        }
        while (isEven(n));

        return n;
    }

    private static boolean isEven(int n) {
        return (n == n/2 + n/2);
    }

    private static boolean divides(int a, int n) {
        return (n == a * (n/a));
    }
    
    private static boolean isPrime(int n) {
        if (n == 1)
            return false;
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (divides(i, n))
                return false;
        }

        return true;
    }

    private static int[] factorise(int n) {
        int[] nFact = new int[n/2-1];
        
        if (isPrime(n)) {
            System.out.println(n + " = " + n + " (prime)");
            return nFact;
        }

        Queue<Integer> factors = new LinkedList<Integer>();

        for (int i = 2; i <= n/2; i++) {
            if (divides(i, n) && isPrime(i))
                factors.add(i);
        }

        while (!factors.isEmpty()) {
            int f = factors.remove();
            int count = 1;
            while (divides((int) Math.pow(f, ++count), n));
            nFact[f-2] = --count;
        }

        System.out.print(n + " =");
        boolean times = false;
        for (int i = 0; i < nFact.length; i++)
            if (nFact[i] != 0 && times)
                System.out.print(" * " + (i+2) + "^" + nFact[i]);
            else if (nFact[i] != 0) {
                System.out.print(" " + (i+2) + "^" + nFact[i]);
                times = true;
            }
        System.out.print("\n");

        return nFact;
    }
}