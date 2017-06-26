package tk.jindongh.groovy;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import groovy.lang.GroovyClassLoader;
/**
 * Hello world!
 *
 */
public class App {
    private GroovyClassLoader classLoader;
    private Map<String, Factory> connectors;
    public App() {
        classLoader = new GroovyClassLoader();
        connectors = new HashMap<>();
    }
    public void loadFactorys() throws Exception {
        List<String> groovyCodes = Arrays.asList(
                "import tk.jindongh.groovy.Factory\n" + 
                "class FactoryA implements Factory {\n" +
                "  String getName() {\"FactoryA\"} \n" +
                "  void connect(String jdbcUrl) {new Util().work();println \"A connected to \" + jdbcUrl;} \n" +
                "}\n"+
                "class Util {\n" +
                " void work() { println \"Util worked\" }\n" +
                "}\n",

                "import tk.jindongh.groovy.Factory\n" + 
                "class FactoryB implements Factory {\n" +
                "  String getName() {\"FactoryB\"} \n" +
                "  void connect(String jdbcUrl) {println \"B connected to \" + jdbcUrl;} \n" +
                "}"
                );
        for (String groovyCode : groovyCodes) {
            Class connectorClass = classLoader.parseClass(groovyCode);
            Factory connector = (Factory)connectorClass.newInstance();
            connectors.put(connector.getName(), connector);
        }
    }
    public void runFactorys() {
        for (Factory connector : connectors.values()) {
            System.out.println(connector.getName() + " is connecting");
            connector.connect(connector.getName());
        }
    }
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.loadFactorys();
        app.runFactorys();
    }
}
