package tracker;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class student {
    public progress progress;
    public String name;
    public String surname;
    public String address;
    public String id;
    public boolean javaNotified = false;
    public boolean dsaNotified = false;
    public boolean dbNotified = false;
    public boolean springNotified = false;
    public static ArrayList<student> base = new ArrayList<>();
    public static ArrayList<String> email = new ArrayList<>();
    public static Deque<String> ids = new ArrayDeque<>();

    public student(String name, String surname, String address, String id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public int getJP() {
        return progress.JAVA;
    }
    public int getDSA() {
        return progress.DSA;
    }
    public int getDB() {
        return progress.DataBases;
    }
    public int getSpring() {
        return progress.Spring;
    }
    public String getId() {
        return id;
    }

    public static void addStudent(ArrayList<student> base, ArrayList<String> emails) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter student credentials or 'back' to return:");
        String line = "";

        while (!line.equals("back")) {
            String name, surname, email;
            line = scr.nextLine();

            if (!line.equals("back")) {
                if (line.matches("\\w+|\\s*") | !line.matches("([\\w\\W]+)\\s([\\Wa-zA-Z\\s'-]+)\\s([a-z@.A-Z0-9]+)")) {
                    System.out.println("Incorrect credentials.");
                } else {

                    Matcher matcherName = Pattern.compile("(^\\b[a-zA-Z-']{2,}\\b)\\s").matcher(line);
                    Matcher matcherSurname = Pattern.compile("\\s(\\b[a-zA-Z'\\-\\s]{2,}\\b)\\s").matcher(line);
                    Matcher matcherEmail = Pattern.compile("\\s([\\w.]+@+\\w+\\.\\w+)").matcher(line);

                    Pattern pattern = Pattern.compile("\\W\\W");
                    Matcher matcherTwoS;
                    Matcher matcherTwoInSurname;


                    if (matcherName.find()) {
                        matcherTwoS = pattern.matcher(matcherName.group());
                        if (matcherTwoS.find()) {
                            System.out.println("Incorrect first name");
                            continue;
                        } else
                            name = matcherName.group();
                    } else {
                        System.out.println("Incorrect first name");
                        continue;
                    }
                    if (matcherSurname.find()) {
                        matcherTwoInSurname = pattern.matcher(matcherSurname.group());
                        if (matcherTwoInSurname.find()) {
                            System.out.println("Incorrect last name");
                            continue;
                        } else
                            surname = matcherSurname.group();
                    } else {
                        System.out.println("Incorrect last name");
                        continue;
                    }
                    if (matcherEmail.find()) {
                        email = matcherEmail.group();
                        if (emails.contains(email)) {
                            System.out.println("This email is already taken.");
                            continue;
                        }
                    } else {
                        System.out.println("Incorrect email");
                        continue;
                    }

                    String id;
                    if (ids.isEmpty()) {
                        id = "100";
                    } else
                        id = String.valueOf(Integer.parseInt(ids.peekLast()) + 1);

                    ids.offerLast(id);

                    if (!name.equals("") & !surname.equals("") & !email.equals("")) {
                        student student = new student(name, surname, email, id);
                        base.add(student);
                        student.progress = new progress(0, 0, 0, 0);


                        emails.add(email);


                        System.out.println("The student has been added.");
                    }
                }
            }
        }
        System.out.println("Total " + base.size() + " students have been added.");

    }

    public static void listAll() {
        if (ids.isEmpty()) {
            System.out.println("No students found");
        } else {

            System.out.println("Students: ");
        for (String id : ids) {
            System.out.println(id);
        }
    }
}

    public static void notifyStudent() {
        int total = 0;
        for (student student : base) {
            int k = 0;
            if (student.progress.JAVA == tracker.progress.maxJavaPoints) {
                if (!student.javaNotified) {
                    System.out.println("To:" + student.address + "\nRe: Your Learning Progress\nHello, " + student.name + student.surname + "! You have accomplished our Java course!");
                    k++;
                }
                student.javaNotified = true;
            }
            if (student.progress.DSA == tracker.progress.maxDSAPoints) {
                if (!student.dsaNotified) {
                    System.out.println("To:" + student.address + "\nRe: Your Learning Progress\nHello, " + student.name + student.surname + "! You have accomplished our DSA course!");
                    k++;
                }
                student.dsaNotified = true;
            }
            if (student.progress.DataBases == tracker.progress.maxDatabasesPoints) {
                if (student.dbNotified) {
                    System.out.println("To:" + student.address + "\nRe: Your Learning Progress\nHello, " + student.name + student.surname + "! You have accomplished our Databases course!");
                    k++;
                }
                student.dbNotified = true;
            }
            if (student.progress.Spring == tracker.progress.maxSpringPoints) {
                if (student.springNotified) {
                    System.out.println("To:" + student.address + "\nRe: Your Learning Progress\nHello, " + student.name + student.surname + "! You have accomplished our Spring course!");
                    k++;
                }
                student.springNotified = true;
            }
            if (k>0) {
                total++;
            }
        }
        System.out.println("Total "+total+" students have been notified.");
    }
}
