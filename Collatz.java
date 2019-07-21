import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Collatz
{
    public static void main(String args[]) {
        int n;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        boolean validCommand = command.equals("stop") || command.equals("iterate") || command.equals("block") || command.equals("factorise");

        if (!validCommand) {
            System.out.println("Command not valid");
            return;
        }
        
        else {
            System.out.print("n = ");
            n = Integer.parseInt(scanner.nextLine());
            
            if (command.equals("iterate"))
                iterate(n, true);
            else if (command.equals("block"))
                block(n);
            else if (command.equals("factorise"))
                factorise(n);
        }

        scanner.close();
    }
    
    private static int iterate(int n, boolean print) {
        if (print)
            System.out.print("Iteration: " + n + " -> ");
        
        if (isEven(n))
            n = n/2;
        else
            n = 3*n+1;
        
        if (print)
            System.out.print(n + "\n");

        return n;
    }
    
    private static int block(int n) {
        System.out.print("Block: " + n + " -> ");

        do {
            n = iterate(n, false);
        }
        while (isEven(n));
        
        System.out.print(n + "\n");

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