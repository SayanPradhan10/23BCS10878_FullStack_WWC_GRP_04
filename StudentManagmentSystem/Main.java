import java.util.*;

class Student {
    private String id, name;
    protected int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() { return id; }
    public int getMarks() { return marks; }

    public String getRole() {
        return "Undergrad";
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + getMarks() + ") -> " + getRole();
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getRole() {
        return "Grad (" + area + ")";
    }
}


class HonourGraduateStudent extends Student {
    private int bonusMarks;

    public HonourGraduateStudent(String id, String name, int marks, int bonusMarks) {
        super(id, name, marks);
        this.bonusMarks = bonusMarks;
    }

    @Override
    public int getMarks() {
        return marks + bonusMarks;
    }

    @Override
    public String getRole() {
        return "Honour Grad (Bonus: " + bonusMarks + ")";
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) { data.put(id, obj); }
    public T find(String id) { return data.get(id); }
    public void delete(String id) { data.remove(id); }
}

class Main {


    public static Student getTopper(List<Student> list) {
        Student top = null;
        int max = -1;
        for (Student s : list) {
            if (s.getMarks() > max) {
                max = s.getMarks();
                top = s;
            }
        }
        return top;
    }

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "Ayush", 88));
        list.add(new Student("S2", "Akash", 89));
        list.add(new Student("S3", "Tarak", 78));
        list.add(new GraduateStudent("S4", "Arman", 68, "CSE"));
        list.add(new GraduateStudent("S5", "Ansh", 88, "MBA"));

        list.add(new HonourGraduateStudent("S6", "Rahul", 85, 10));

        Repository<Student> repo = new Repository<>();

        for (Student s : list) {
            repo.save(s.getId(), s);
        }

        System.out.println("ALL STUDENTS:");
        list.forEach(System.out::println);


        Student topper = getTopper(list);
        System.out.println("\nTOPPER:");
        System.out.println(topper);
    }
}
