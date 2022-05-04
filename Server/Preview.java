import java.util.*;

public class Preview{
  public static void main(String[] args) {

    DO oss = new DO();

    Map<Integer , DO > map = new HashMap<>();

    map.put(1, oss);

    System.out.println(map.get(1).name);
    System.out.println(oss.name);

    map.remove(1);

    System.out.println(map.get(1).name);
    System.out.println(oss.name);



  }
}

class DO{
  public String name = "어";


}