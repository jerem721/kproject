import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DirectionalChanges  directionalChanges;


        if (args.length < 3)
            System.out.println("./DirectionChanges <inputFile> <outputFile directory> r% [r%]");
        else
        {
            directionalChanges = new DirectionalChanges(args[0], args[1], Arrays.copyOfRange(args, 2, args.length));
            directionalChanges.start();
            directionalChanges.finish();
        }
    }
}
