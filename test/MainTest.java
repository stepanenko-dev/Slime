import com.a.stepanenko.slime.Main;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.fest.assertions.core.Condition;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Anatoliy on 11.05.2015.
 */
public class MainTest {
    private Appender appender;
    private List<LoggingEvent> loggingEvents;
    private Main main;

    @Before
    public void createMain() {
        main = new Main();
    }
    @Before
    public void addTestAppender(){
        Logger logger = Logger.getLogger(Main.class);
        loggingEvents = new ArrayList<>();
        appender = new AppenderSkeleton() {
            @Override
            protected void append(LoggingEvent loggingEvent) {
                loggingEvents.add(loggingEvent);
            }

            @Override
            public void close() {

            }

            @Override
            public boolean requiresLayout() {
                return false;
            }
        };
        logger.addAppender(appender);
    }
    @Test
    public void testMain(){
        assertNotNull(main);
        main.main(null);
        assertThat(loggingEvents).hasSize(2);
    }

    @Test
    public void testHelp(){
        main.main(new String[]{"-h"});
        assertThat(loggingEvents).hasSize(3);
    }
}
