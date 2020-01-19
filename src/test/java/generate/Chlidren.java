package generate;

import java.util.Date;

public class Chlidren extends Parent {
    public static void main(String[] args) {
        Chlidren c = new Chlidren();
        String d = (new Date().toString());
        if(c instanceof Chlidren){
            System.out.println(c);
            System.out.println(c.hashCode());
            System.out.println(c.getClass());
            System.out.println(d);
        }

    }

    @Override
    public String toString() {
        return "children";
    }
}
