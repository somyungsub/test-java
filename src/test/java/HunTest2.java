import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class HunTest2 {

  @Test
  public void test() {
    try {
      Class<Target> clazz = (Class<Target>) Class.forName(Target.class.getName());
      Object obj = clazz.getConstructor(null).newInstance();
      for (Field field : clazz.getDeclaredFields()) {
        System.out.println("field.getDeclaredAnnotation(Target.class) = " + field.getDeclaredAnnotation(Target.class));
        if (field.isAnnotationPresent(Target.class)) {
          System.out.println("field = " + field.get(obj));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
