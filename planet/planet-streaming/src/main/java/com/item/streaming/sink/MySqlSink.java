package com.item.streaming.sink;

import com.item.model.common.JdbcConst;
import com.item.model.entity.NameNodeFSNamesystem;
import com.item.model.entity.NameNodeMsg;
import com.item.model.entity.NamenodeJvmmetrics;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * @author zcm
 */
public class MySqlSink extends RichSinkFunction<NameNodeMsg> {

    private Connection connection;
    private PreparedStatement ps;


    /**
     * 打开数据库连接
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = getConnection();
        if (connection == null){
            System.out.println("connect is error");
        }
    }

    @Override
    public void invoke(NameNodeMsg value, Context context) {
        if (value != null){
            System.out.println("sink:" + value.toString());

            if (value.getNamenodeJvmmetrics() != null){
                execJvmmetrics(value.getNamenodeJvmmetrics());
            }
            if (value.getNameNodeFSNamesystem() != null){
                execFSNamesystem(value.getNameNodeFSNamesystem());
            }
        }
    }


    public void execJvmmetrics(NamenodeJvmmetrics jvm) {
        System.out.println("sink ------");

        String sql = "INSERT INTO jmxlog_namenode_jvmmetrics " +
                "(memNonHeapUsedM,memNonHeapMaxM,memHeapUsedM,memHeapMaxM,memMaxM,gcCount,threadsNew,threadsRunnable,threadsBlocked,threadsWaiting,threadsTimedWaiting," +
                "threadsTerminated,logFatal,logError,logWarn,logInfo,createTime) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            ps = this.connection.prepareStatement(sql);
            //设置参数
            ps.setDouble(1,jvm.getMemNonHeapUsedM());
            ps.setDouble(2,jvm.getMemNonHeapMaxM());
            ps.setDouble(3,jvm.getMemHeapUsedM());
            ps.setDouble(4,jvm.getMemHeapMaxM());
            ps.setDouble(5,jvm.getMemMaxM());
            ps.setDouble(6,jvm.getGcCount());
            ps.setDouble(7,jvm.getThreadsNew());
            ps.setDouble(8,jvm.getThreadsRunnable());
            ps.setDouble(9,jvm.getThreadsBlocked());
            ps.setDouble(10,jvm.getThreadsWaiting());
            ps.setDouble(11,jvm.getThreadsTimedWaiting());
            ps.setDouble(12,jvm.getThreadsTerminated());
            ps.setDouble(13,jvm.getLogFatal());
            ps.setDouble(14,jvm.getLogError());
            ps.setDouble(15,jvm.getLogInfo());
            ps.setDouble(16,jvm.getLogWarn());
            if (jvm.getCreateTime() == null){
                ps.setTimestamp(17, Timestamp.valueOf(LocalDateTime.now()));
            }else {
                ps.setTimestamp(17, Timestamp.valueOf(LocalDateTime.parse(jvm.getCreateTime())));
            }
            ps.executeLargeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void execFSNamesystem(NameNodeFSNamesystem fnName)  {
        System.out.println("sink ++++++");

        String sql = "INSERT INTO jmxlog_namenode_fsnamesystem (missingBlocks,expiredHeartbeats,transactionsSinceLastCheckpoint,transactionsSinceLastLogRoll,lastCheckpointTime,capacityRemaining," +
                "capacityTotal,capacityUsed,capacityUsedNonDFS,totalLoad,snapshottableDirectories,filesTotal,pendingDataNodeMessageCount," +
                "staleDataNodes,createTime) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = this.connection.prepareStatement(sql);
            //设置参数
            ps.setDouble(1,fnName.getMissingBlocks());
            ps.setDouble(2,fnName.getExpiredHeartbeats());
            ps.setDouble(3,fnName.getTransactionsSinceLastCheckpoint());
            ps.setDouble(4,fnName.getTransactionsSinceLastLogRoll());
            ps.setDouble(5,fnName.getLastCheckpointTime());
            ps.setDouble(6,fnName.getCapacityRemaining());
            ps.setDouble(7,fnName.getCapacityTotal());
            ps.setDouble(8,fnName.getCapacityUsed());
            ps.setDouble(9,fnName.getCapacityUsedNonDFS());
            ps.setDouble(10,fnName.getTotalLoad());
            ps.setDouble(11,fnName.getSnapshottableDirectories());
            ps.setDouble(12,fnName.getFilesTotal());
            ps.setDouble(13,fnName.getPendingDataNodeMessageCount());
            ps.setDouble(14,fnName.getStaleDataNodes());
            if (fnName.getCreateTime() == null){
                ps.setTimestamp(15, Timestamp.valueOf(LocalDateTime.now()));
            }else {
                ps.setTimestamp(15, Timestamp.valueOf(LocalDateTime.parse(fnName.getCreateTime())));
            }
            ps.executeLargeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws Exception {
        super.close();
        //关闭连接和释放资源
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    /**
     * 使用jdbc连接数据库
     * @return
     */
    private static Connection getConnection(){
        Connection connection  = null;
        try {
            Class.forName(JdbcConst.DRIVER);
            connection = DriverManager.getConnection(JdbcConst.JDBC_URL,JdbcConst.USER_NAME,JdbcConst.PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}
