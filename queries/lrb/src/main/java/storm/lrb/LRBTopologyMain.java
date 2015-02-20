package storm.lrb;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.utils.Utils;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.lrb.bolt.TollNotificationBolt;
import storm.lrb.spout.DataDriverSpout;
import storm.lrb.tools.CommandLineParser;
import storm.lrb.tools.Helper;
import storm.lrb.tools.StopWatch;

//import storm.lrb.spout.SocketClientSpout;
/**
 * This topology is equivalent to LrbXD with the difference, that "xway" and
 * "dir" are used in fieldsgrouping (storm feature) of the
 * {@link TollNotificationBolt} as opposed to LrbXD where one field is made out
 * of both fields two.
 */
public class LRBTopologyMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(LRBTopologyMain.class);

    public static void main(String[] args) throws Exception {

        CommandLineParser cmd = new CommandLineParser();

        LOGGER.debug("host: " + cmd.getHost() + " port: " + cmd.getPort());
        LOGGER.debug("Submit to cluster: " + cmd.isSubmit());
        if (cmd.getOffset() != 0) {
            LOGGER.debug("using offset: " + cmd.getOffset());
        }
        int executors = cmd.getExecutors();
        if (cmd.getXways() > 1) {
            executors = (cmd.getXways() / 2) * cmd.getExecutors();
        }
        int tasks = executors * cmd.getTasks();
        main0(cmd.getOffset(),
                executors, cmd.getXways(), cmd.getHost(), cmd.getPort(), cmd.getHistFile(),
                tasks, cmd.getFields(), cmd.isSubmit(), cmd.isDebug(), cmd.getWorkers(), cmd.getNameext());
    }

    /**
     * Encapsulation of creation of the cluster after parameter parsing.
     * @param offset
     * @param executors
     * @param xways
     * @param host
     * @param port
     * @param histFile
     * @param tasks
     * @param fields
     * @param submit
     * @param stormConfigDebug
     * @param workers
     * @param nameext
     * @throws AlreadyAliveException
     * @throws InvalidTopologyException
     * @throws AuthorizationException 
     */
    public static void main0(int offset,
            int executors,
            int xways,
            String host,
            int port,
            String histFile,
            int tasks,
            List<String> fields,
            boolean submit,
            boolean stormConfigDebug,
            int workers,
            String nameext
    ) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException, FileNotFoundException {
        StopWatch stormTimer = new StopWatch(offset);
        String topologyNamePrefix = nameext + "_lrbNormal_" + Helper.readable(fields) + "_L" + xways + "_"
                + workers + "W_T" + tasks + "_" + executors + "E_O" + offset;
        LRBTopology lRBTopology = new LRBTopology(nameext, 
                fields, 
                xways, 
                workers, 
                tasks, 
                executors, 
                offset, 
                new DataDriverSpout(Thread.currentThread().getContextClassLoader().getResource("datafile20seconds.dat").getFile()), 
                stormTimer, 
                submit, 
                histFile, topologyNamePrefix);
        StormTopology topology = lRBTopology.getStormTopology();
        Config conf = lRBTopology.getStormConfig();
        conf.setDebug(stormConfigDebug);
        
        Locale newLocale = new Locale("en", "US");
        LOGGER.debug(String.format("setting locale to %s", newLocale));
        Locale.setDefault(newLocale); //why??
        
        LOGGER.debug("starting cluster: " + "stormlrb" + topologyNamePrefix);
        if (submit) {

            conf.setNumWorkers(workers);
            conf.setNumAckers(workers);

            StormSubmitter.submitTopology(topologyNamePrefix, conf, topology);

        } else {

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(TopologyControl.TOPOLOGY_NAME, conf, topology);

            Utils.sleep(2100000);
            cluster.killTopology(TopologyControl.TOPOLOGY_NAME);
            cluster.shutdown();
        }
    }
}