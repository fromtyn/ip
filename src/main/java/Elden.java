import java.util.Scanner;

public class Elden {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Elden");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        while(true) {
            String input = in.nextLine().trim();

            if (input.equals("bye")) {
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
