package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class progress {
    public static ArrayList<Integer> doneTasks = new ArrayList<>();
    public static final int maxJavaPoints = 600;
    public static final int maxDSAPoints = 400;
    public static final int maxDatabasesPoints = 480;
    public static final int maxSpringPoints = 550;
    public int JAVA;
    public int DSA;
    public int DataBases;
    public int Spring;

    public progress(int JAVA, int DSA, int Databases, int Spring) {
        this.JAVA = JAVA;
        this.DSA = DSA;
        this.DataBases = Databases;
        this.Spring = Spring;
    }

    public static void addPoints() {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter an id and points or 'back' to return: ");

        while (true) {
            String enter1 = scr.nextLine();
            if (enter1.equals("back")) {
                break;
            }
            if (!enter1.matches("\\w+\\s\\d+\\s\\d+\\s\\d+\\s\\d+")) {
                System.out.println("Incorrect points format.");
            } else {
                String[] enter = enter1.split("\\s+");
                int java = Integer.parseInt(enter[1]);
                int dsa = Integer.parseInt(enter[2]);
                int db = Integer.parseInt(enter[3]);
                int spring = Integer.parseInt(enter[4]);
                if (doneTasks.isEmpty()) {
                    doneTasks.add(0);
                    doneTasks.add(0);
                    doneTasks.add(0);
                    doneTasks.add(0);
                }
                if (java > 0 ) {
                    doneTasks.set(0, doneTasks.get(0) + 1);
                }
                if (dsa > 0) {
                    doneTasks.set(1, doneTasks.get(1) + 1);
                }
                if (db > 0) {
                    doneTasks.set(2, doneTasks.get(2) + 1);
                }
                if (spring > 0 ) {
                    doneTasks.set(3, doneTasks.get(3) + 1);
                }

                if (java < 0 | dsa < 0 | db < 0 | spring < 0) {
                    System.out.println("Incorrect points format.");
                } else {

                    if (!student.ids.contains(enter[0])) {
                        System.out.println("No student is found for id=" + enter[0] + ".");
                    } else {

                        for (student s : student.base) {
                            if (s.id.equals(enter[0])) {
                                s.progress.JAVA = s.progress.JAVA + java;
                                s.progress.DSA = s.progress.DSA + dsa;
                                s.progress.DataBases = s.progress.DataBases + db;
                                s.progress.Spring = s.progress.Spring + spring;
                            }
                        }
                        System.out.println("Points updated.");
                    }
                }
            }
        }
    }

    public static void findID() {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter an id or 'back' to return:");
        String enter;
        while (true) {
            enter = scr.nextLine();
            if (enter.equals("back")) {
                break;
            }
            String id = enter;
            if (!student.ids.contains(id)) {
                System.out.printf("No student is found for id=%s\n", id);
            } else {

                for (student student : student.base) {
                    if (student.id.equals(id)) {
                        System.out.println(id + " points: " + "Java=" + student.progress.JAVA + "; " + "DSA=" + student.progress.DSA + "; "
                                + "Databases=" + student.progress.DataBases + "; " + "Spring=" + student.progress.Spring);
                    }
                }
            }
        }
    }

    public static void statistics(ArrayList<student> base) {
        Scanner scr = new Scanner(System.in);
        String enter;
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println("Most popular: " + mostPop(base));
        System.out.println("Least popular: " + leastPop(base));
        System.out.println("Highest activity: " + highActivity(base));
        System.out.println("Lowest activity: " + lowActivity(base));
        System.out.println("Easiest course: " + easyCourse(base));
        System.out.println("Hardest course: " + hardCourse(base));
        while (true) {
            enter = scr.nextLine();
            if (enter.equals("back")) {
                break;
            }
            switch (enter) {
                case "Java":
                    System.out.println("Java\nid    points  completed");
                    base.sort(Comparator.comparing(student::getJP).reversed().thenComparing(student::getId));
                    for (student student : base) {
                        double value = (student.progress.JAVA*1.0/maxJavaPoints)*100;
                        BigDecimal decimal = new BigDecimal(value).setScale(1, RoundingMode.HALF_UP);
                        if (value > 0)
                        System.out.println(student.id + "   " + student.progress.JAVA + "      " + decimal + "%");
                    } break;
                    case "DSA":
                        System.out.println("DSA\nid    points  completed");
                        base.sort(Comparator.comparing(student::getDSA).reversed().thenComparing(student::getId));
                        for (student student : base) {
                            double value = (student.progress.DSA*1.0/maxDSAPoints)*100;
                            BigDecimal decimal = new BigDecimal(value).setScale(1, RoundingMode.HALF_UP);
                            if (value > 0)
                                System.out.println(student.id + "   " + student.progress.DSA + "      " + decimal + "%");
                        } break;
                        case "Databases":
                            System.out.println("Databases\nid    points  completed");
                            base.sort(Comparator.comparing(student::getDB).reversed().thenComparing(student::getId));
                            for (student student : base) {
                                double value = (student.progress.DataBases*1.0/maxDatabasesPoints)*100;
                                BigDecimal decimal = new BigDecimal(value).setScale(1, RoundingMode.HALF_UP);
                                if (value > 0)
                                    System.out.println(student.id + "   " + student.progress.DataBases + "       " + decimal + "%");
                            } break;
                            case "Spring":
                                System.out.println("Spring\nid    points  completed");
                                base.sort(Comparator.comparing(student::getSpring).reversed().thenComparing(student::getId));
                                for (student student : base) {
                                    double value = (student.progress.Spring*1.0/maxSpringPoints)*100;
                                    BigDecimal decimal = new BigDecimal(value).setScale(1, RoundingMode.HALF_UP);
                                    if (value > 0)
                                        System.out.println(student.id + "   " + student.progress.Spring + "      " + decimal + "%");
                                } break;
                                default:
                    System.out.println("Unknown course.");
            }
        }
    }
    public static String mostPop(ArrayList<student> base) {
        int java = 0, dsa = 0, db = 0, spring = 0;
        ArrayList<Activity> activities = new ArrayList<>();
        if (base.isEmpty()) {
            return "n/a";
        }
        for (student student : base) {
            if (student.progress.JAVA > 0) { java++; }
            if (student.progress.DSA > 0) { dsa++; }
            if (student.progress.DataBases > 0) { db++; }
            if (student.progress.Spring > 0) { spring++; }
        }
        activities.add(new Activity("Java", java));
        activities.add(new Activity("DSA", dsa));
        activities.add(new Activity("Databases", db));
        activities.add(new Activity("Spring", spring));
        activities.sort(Comparator.comparing(Activity::getPoints).reversed());
        if (activities.get(0).points == activities.get(1).points & activities.get(1).points == activities.get(2).points & activities.get(2).points == activities.get(3).points) {
            return activities.get(0).course + ", " + activities.get(1).course + ", " + activities.get(2).course + ", " + activities.get(3).course;
        } else
        if (activities.get(0).points == activities.get(1).points & activities.get(1).points == activities.get(2).points) {
            return activities.get(0).course + ", " + activities.get(1).course + ", " + activities.get(2).course;
        } else
            if (activities.get(0).points == activities.get(1).points) {
                return activities.get(0).course + ", " + activities.get(1).course;
            } else
                return activities.get(0).course;
    }
    public static String leastPop(ArrayList<student> base) {
        int java = 0, dsa = 0, db = 0, spring = 0;
        ArrayList<Activity> activities = new ArrayList<>();
        if (base.isEmpty()) {
            return "n/a";
        }
        for (student student : base) {
            if (student.progress.JAVA > 0) java++;
            if (student.progress.DSA > 0) dsa++;
            if (student.progress.DataBases > 0) db++;
            if (student.progress.Spring > 0) spring++;
        }
        activities.add(new Activity("Java", java));
        activities.add(new Activity("DSA", dsa));
        activities.add(new Activity("Databases", db));
        activities.add(new Activity("Spring", spring));
        activities.sort(Comparator.comparing(Activity::getPoints));
        if (activities.get(0).points == activities.get(1).points & activities.get(1).points == activities.get(2).points & activities.get(2).points == activities.get(3).points) {
            return "n/a";
        } else return activities.get(0).course;
    }
    public static String highActivity(ArrayList<student> base) {
        if (base.isEmpty()) {
            return "n/a";
        }
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Java", doneTasks.get(0)));
        activities.add(new Activity("DSA", doneTasks.get(1)));
        activities.add(new Activity("Databases", doneTasks.get(2)));
        activities.add(new Activity("Spring", doneTasks.get(3)));
        activities.sort(Comparator.comparing(Activity::getPoints).reversed());
        if (activities.get(0).points == activities.get(1).points & activities.get(1).points == activities.get(2).points & activities.get(2).points == activities.get(3).points) {
            return activities.get(0).course + ", " + activities.get(1).course + ", " + activities.get(2).course + ", " + activities.get(3).course;
        } else
        if (activities.get(0).points == activities.get(1).points & activities.get(1).points == activities.get(2).points) {
            return activities.get(0).course + ", " + activities.get(1).course + ", " + activities.get(2).course;
        } else
        if (activities.get(0).points == activities.get(1).points) {
            return activities.get(0).course + ", " + activities.get(1).course;
        } else
            return activities.get(0).course;
    }
    public static String lowActivity(ArrayList<student> base) {
        if (base.isEmpty()) {
            return "n/a";
        }
        List<Activity> activities = new ArrayList<>();
       /* for (student student : base) {
            java+=student.progress.JAVA;
            dsa+=student.progress.DSA;
            db+=student.progress.DataBases;
            spring+=student.progress.Spring;
        }*/
        activities.add(new Activity("Java", doneTasks.get(0)));
        activities.add(new Activity("DSA", doneTasks.get(1)));
        activities.add(new Activity("Databases", doneTasks.get(2)));
        activities.add(new Activity("Spring", doneTasks.get(3)));
        activities.sort(Comparator.comparing(Activity::getPoints));
        if (activities.get(0).points == activities.get(1).points & activities.get(1).points == activities.get(2).points & activities.get(2).points == activities.get(3).points) {
            return "n/a";
        } else return activities.get(0).course;
    }
    public static String easyCourse(ArrayList<student> base) {
        int java = 0, dsa = 0, db = 0, spring = 0;
        if (base.isEmpty()) {
            return "n/a";
        }
        List<Activity> list = new ArrayList<>();
        for (student student : base) {
            java+=student.progress.JAVA;
            dsa+=student.progress.DSA;
            db+=student.progress.DataBases;
            spring+=student.progress.Spring;
        }
        list.add(new Activity("Java", java/ doneTasks.get(0)));
        list.add(new Activity("DSA", dsa/ doneTasks.get(1)));
        list.add(new Activity("Databases", db/ doneTasks.get(2)));
        list.add(new Activity("Spring", spring/ doneTasks.get(3)));
        list.sort(Comparator.comparing(Activity::getPoints).reversed());

        return list.get(0).course;
    }
    public static String hardCourse(ArrayList<student> base) {
        if (base.isEmpty()) {
            return "n/a";
        }
        int java = 0, dsa = 0, db = 0, spring = 0;
        List<Activity> list = new ArrayList<>();
       /* for (student student : base) {
            if (student.progress.JAVA > java) { java = student.progress.JAVA; }
            if (student.progress.DSA > dsa) { dsa = student.progress.DSA; }
            if (student.progress.DataBases > db) { db = student.progress.DataBases; }
            if (student.progress.Spring > spring) { spring = student.progress.Spring; }
        }*/
        for (student student : base) {
            java+=student.progress.JAVA;
            dsa+=student.progress.DSA;
            db+=student.progress.DataBases;
            spring+=student.progress.Spring;
        }
        if (java!=0)
        list.add(new Activity("Java", java/ doneTasks.get(0)));
        if (dsa!=0)
        list.add(new Activity("DSA", dsa/ doneTasks.get(1)));
        if (db!=0)
        list.add(new Activity("Databases", db/ doneTasks.get(2)));
        if (spring!=0)
        list.add(new Activity("Spring", spring/ doneTasks.get(3)));
        list.sort(Comparator.comparing(Activity::getPoints));

        return list.get(0).course;
    }
}
