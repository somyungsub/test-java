import java.lang.annotation.Annotation;

@GetFields
public class Target implements Annotation {
  @GetFields
  int IntIn = 102;
  @GetFields
  String str = "abc";

  @Override
  public Class<? extends Annotation> annotationType() {
    try {
      return (Class<? extends Annotation>) Class.forName(Target.class.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  //  @Override
//  public Class<? extends Annotation> annotationType() {
//    return null;
//  }
}
