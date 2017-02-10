import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileReader;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.Invocable;
import javax.script.Bindings;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.api.scripting.ScriptUtils;

public class Minim {

    public static void main(String args[]) {

        /*
          Read stringified API Eelement from file given as an argument.
        */
        String source = null;

        if (0 < args.length) {
            try {
                source = new String(Files.readAllBytes(Paths.get(args[0])));
            } catch (IOException e) {
                System.err.println("Could not read :" + args[0]);
                System.exit(1);
            }
        } else {
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(1);
        }

        try {

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(new FileReader("bundle.js"));
            Invocable invocable = (Invocable) engine;
            ScriptObjectMirror result = (ScriptObjectMirror) invocable.invokeFunction("loadAPI", source);
            ScriptObjectMirror annotations = (ScriptObjectMirror) result.get("annotations");
            ScriptObjectMirror values = (ScriptObjectMirror) annotations.get("content");
            // System.out.println(invocable.invokeMethod(ans, "length"));
            for (int i = 0; i < (int)values.get("length"); ++i) {
                System.out.println("annotation (" + (i + 1) + "): " + invocable.invokeMethod(values.getSlot(i), "toValue"));
            }


        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } catch (ScriptException e) {
            System.err.println(e);
            System.exit(1);
        } catch (NoSuchMethodException e) {
            System.err.println(e);
            System.exit(1);
        }

    }

}

