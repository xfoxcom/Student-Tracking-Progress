package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        String enter = "";
        while (!enter.equals("exit")) {
            enter = scr.nextLine();
            if (enter.matches("\\s+")) {
                System.out.println("no input");
            } else {
                switch (enter) {
                    case "add students":
                        student.addStudent(student.base, student.email);
                        break;
                    case "back":
                        System.out.println("Enter 'exit' to exit the program.");
                        break;
                    case "exit":
                        System.out.println("Bye!");
                        break;
                    case "":
                        System.out.println("no input");
                        break;
                    case "list": student.listAll(); break;
                    case "add points": progress.addPoints(); break;
                    case "find": progress.findID(); break;
                    case "statistics": progress.statistics(student.base); break;
                    case "notify": student.notifyStudent(); break;
                    default:
                        System.out.println("Unknown command!");
                }
            }
        }
    }
}
