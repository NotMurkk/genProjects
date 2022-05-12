

import com.genspark.dependencyInjection.data.Student;
public class Main {
    public static void main(String[] args) {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
            Student student = (Student) applicationContext.getBean("student");
            System.out.println(student);
        }
}
