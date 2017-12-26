package sun.org.mozilla.javascript.inter.scriptable;

import org.junit.Test;
import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.Scriptable;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ScriptableTests {

    @Test
    public void evaluateStringTest() {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            String str = "function add(params){var result={};result.value=param.a+param.b;}add({3,2})";
            Object result = cx.evaluateString(scope, str, null, 1, null);
            System.out.println(str + "=" + Context.toNumber(result));
        } finally {
            Context.exit();
        }
    }

    public static void main(String[] args) throws IOException {         // Obtain an interpreter or "ScriptEngine" to run the script.
        ScriptEngineManager scriptManager = new ScriptEngineManager();
        ScriptEngine js = scriptManager.getEngineByExtension("js");          // The script file we are going to run
        String filename = null;          // A Bindings object is a symbol table for or namespace for the
        // script engine. It associates names and values and makes
        // them available to the script.
        Bindings bindings = js.createBindings();          // Process the arguments. They may include any number of
        // -Dname=value arguments, which define variables for the script.
        // Any argument that does not begin with -D is taken as a filename
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-D")) {
                int pos = arg.indexOf('=');
                if (pos == -1) usage();
                String name = arg.substring(2, pos);
                String value = arg.substring(pos + 1);
                // Note that all the variables we define are strings.
                // Scripts can convert them to other types if necessary.
                // We could also pass a java.lang.Number, a java.lang.Boolean
                // or any Java object or null.
                bindings.put(name, value);
            } else {
                if (filename != null)
                    usage(); // only one file please
                filename = arg;
            }
        }         // Make sure we got a file out of the arguments.
        if (filename == null) usage();
        // Add one more binding using a special reserved variable name
        // to tell the script engine the name of the file it will be executing.
        // This allows it to provide better error messages.
        bindings.put(ScriptEngine.FILENAME, filename);          // Get a stream to read the script.
        Reader in = new FileReader(filename);
        try {             // Evaluate the script using the bindings and get its result.
            Object result = js.eval(in, bindings);             // Display the result.
            System.out.println(result);
        } catch (ScriptException ex) {
            // Or display an error message.
            System.out.println(ex);
        }
    }

    static void usage() {
        System.err.println("Usage: java RunScript [-Dname=value...] script.js");
        System.exit(1);
    }

}