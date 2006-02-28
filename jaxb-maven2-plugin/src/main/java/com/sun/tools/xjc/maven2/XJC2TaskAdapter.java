package com.sun.tools.xjc.maven2;

import com.sun.tools.xjc.XJC2Task;
import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.Project;

/**
 * Inherit all the Ant XJC2Task behaviour and compensate for the
 * missing Ant project and Maven logging facility.
 */
public class XJC2TaskAdapter extends XJC2Task
{
    /** The logging system. */
    Log log;

    /**
     * Constructor
     * @param log The logging system to use.
     */
    public XJC2TaskAdapter(Log log)
    {
        // Logger used when overloading XJC2Task log methods
        this.log = log;

        // Create an empty project for the base XJC2Task
        setProject(new Project());
    }

    /**
     *
     * @param string
     */
 /*   public void log(String string)
    {
        log.info(string);
    }  */

    /**
     * Overloaded to route logging mesages from XJC2Task to the Maven logger.
     * @param message message to log.
     * @param logType message log level.
     */
    public void log(String message, int logType)
    {
        switch(logType)
        {
            case Project.MSG_WARN :
                log.warn(message);
                break;

            case Project.MSG_ERR :
                log.error(message);
                break;

            case Project.MSG_DEBUG :
                log.debug(message);
                break;

            case Project.MSG_VERBOSE :
                // fall to default
            case Project.MSG_INFO :
                // fall to default
            default:
                log.info(message);
        }
    }
}
