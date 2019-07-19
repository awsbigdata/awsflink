package awsflink.com.awssupport.flink.kinesis;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kinesis.FlinkKinesisConsumer;
import org.apache.flink.streaming.connectors.kinesis.config.ConsumerConfigConstants;

import java.util.Properties;

/**
 * This is an example on how to consume data from Kinesis.
 */
public class ConsumeFromKinesis {

    public static void main(String[] args) throws Exception {
        ParameterTool pt = ParameterTool.fromArgs(args);

        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
        see.setParallelism(1);

        Properties kinesisConsumerConfig = new Properties();
        kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_REGION, pt.getRequired("region"));
        //kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_ACCESS_KEY_ID, pt.getRequired("accesskey"));
        //kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_SECRET_ACCESS_KEY, pt.getRequired("secretkey"));

        DataStream<String> kinesis = see.addSource(new FlinkKinesisConsumer<>(
                "testflink",
                new SimpleStringSchema(),
                kinesisConsumerConfig));

        kinesis.print();

        see.execute();
    }

}
