package sun.org.mozilla.javascript.inter.scriptable;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * java使用js引擎例子
 * 
 * @author yunshouhu
 *
 */
public class RunScriptTests {

	@Test
	public void test脚本预编译() throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();

		ScriptEngine engine = manager.getEngineByName("js");

		engine.eval("function add (a, b) {c = a + b; return c; }");
		Invocable jsInvoke = (Invocable) engine;

		Object result1 = jsInvoke.invokeFunction("add", new Object[] { 10, 5 });
		System.out.println("result1="+result1);

		Adder adder = jsInvoke.getInterface(Adder.class);
		int result2 = adder.add(10, 5);
		System.out.println("result2="+result2);
	}

	private static void test向js脚本引擎传递变量() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		engine.put("a", 1);
		engine.put("b", 5);

		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		Object a = bindings.get("a");
		Object b = bindings.get("b");
		System.out.println("a = " + a);
		System.out.println("b = " + b);

		Object result;
		try {
			result = engine.eval("c = a + b;");
			System.out.println("a + b = " + result);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}

	private static void testjs() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String script = "print ('www.oschina.net')";
		try {
			engine.eval(script);
			// var pi_value=Math.PI;
			// var sqrt_value=Math.sqrt(15);
			// engine.eval("print Math.PI ");
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}

	private static void test在脚本中调用Java对象和方法() {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine jsEngine;
		jsEngine = manager.getEngineByExtension("js");
		try {
			jsEngine.eval("importPackage(javax.swing);"
					+ "var optionPane =JOptionPane.showMessageDialog(null, 'Hello!');");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	private static void testfile() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			// 乱码
			// FileReader reader = new FileReader("src/my.js");

			InputStreamReader reader = new InputStreamReader(
					new FileInputStream("src/my.js"), "UTF-8");
			System.out.println(reader.getEncoding());
			engine.eval(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testList() {
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factoryList = manager.getEngineFactories();
		System.out.println(factoryList.size());
		for (ScriptEngineFactory factory : factoryList) {
			System.out.println(factory.getEngineName() + "="
					+ factory.getLanguageName());
		}
	}
}