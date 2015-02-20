package storm.lrb.tools;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * helper class to ease parsing of arguments
 *
 */
public class CommandLineParser {

    @Parameter
    private final List<String> parameters = new ArrayList<String>();

    @Parameter(names = {"-p", "-port"}, description = "SocketPort to connect to (default: 15000)")
    private Integer port = 15000;

    @Parameter(names = {"-x", "-xway"}, description = "How many xways to process")
    private Integer xways = 1;

    @Parameter(names = {"-o", "-offset"}, description = "Offset to start simulation with")
    private Integer offset = 0;

    @Parameter(names = {"-f", "-file"}, description = "Simulation file to use")
    private String file = null;

    @Parameter(names = "-h", description = "SocketHost to connect to (default: localhost)")
    private String host = "localhost";

    @Parameter(names = "-submit", description = "Submit to Cluster")
    private boolean submit = false;

    @Parameter(names = {"-hist", "-histfile"}, description = "History File to consume")
    private String histFile = "";

    @Parameter(names = {"-worker", "-w"}, description = "Number of workers")
    private int workers = 1;

    @Parameter(names = {"-executors", "-e"}, description = "Number of executors")
    private int executors = 1;

    @Parameter(names = {"-tasks", "-t"}, description = "Number of tasks")
    private int tasks = 3;

    @Parameter(names = {"-debug", "-d"}, description = "Set debug mode")
    private boolean debug = false;

    @Parameter(names = "-fields", variableArity = true)
    private List<String> fields = Arrays.asList("xway", "dir");

    @Parameter(names = {"-n", "-name"}, description = "prefix for topology name")
    private String nameext = "";

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    protected void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the xways
     */
    public Integer getXways() {
        return xways;
    }

    /**
     * @param xways the xways to set
     */
    protected void setXways(Integer xways) {
        this.xways = xways;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    protected void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    protected void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    protected void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the submit
     */
    public boolean isSubmit() {
        return submit;
    }

    /**
     * @param submit the submit to set
     */
    protected void setSubmit(boolean submit) {
        this.submit = submit;
    }

    /**
     * @return the histFile
     */
    public String getHistFile() {
        return histFile;
    }

    /**
     * @param histFile the histFile to set
     */
    protected void setHistFile(String histFile) {
        this.histFile = histFile;
    }

    /**
     * @return the workers
     */
    public int getWorkers() {
        return workers;
    }

    /**
     * @param workers the workers to set
     */
    protected void setWorkers(int workers) {
        this.workers = workers;
    }

    /**
     * @return the executors
     */
    public int getExecutors() {
        return executors;
    }

    /**
     * @param executors the executors to set
     */
    protected void setExecutors(int executors) {
        this.executors = executors;
    }

    /**
     * @return the tasks
     */
    public int getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    protected void setTasks(int tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    protected void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the fields
     */
    public List<String> getFields() {
        return Collections.unmodifiableList(fields);
    }

    /**
     * @param fields the fields to set
     */
    protected void setFields(List<String> fields) {
        this.fields = fields;
    }

    /**
     * @return the nameext
     */
    public String getNameext() {
        return nameext;
    }

    /**
     * @param nameext the nameext to set
     */
    protected void setNameext(String nameext) {
        this.nameext = nameext;
    }

}
