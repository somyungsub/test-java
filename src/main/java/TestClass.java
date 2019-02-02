public class TestClass {

    private String name;
    private int age;

    static {
        System.out.println("static inner");
    }

    public TestClass() {
        System.out.println("constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void test() {
        System.out.println("testMethod");
    }
}
