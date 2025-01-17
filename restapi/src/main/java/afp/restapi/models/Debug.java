package afp.restapi.models;

public class Debug {
    private static boolean debug = true;

    public static void log(String str){
        if (debug) System.out.println(str);
    }
}
