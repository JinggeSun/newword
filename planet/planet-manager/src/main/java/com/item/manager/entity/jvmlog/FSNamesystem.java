package com.item.manager.entity.jvmlog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("jmxlog_namenode_fsnamesystem")
public class FSNamesystem implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //当前遗失的block数量
    private Double missingBlocks;
    //失去心跳的总数量
    private Double expiredHeartbeats;
    //自上次检查点以来的事务总数
    private Double transactionsSinceLastCheckpoint;
    //自上次编辑日志卷以来的事务总数
    private Double transactionsSinceLastLogRoll;
    //从上一个检查点以来的时间(毫秒)
    private Double lastCheckpointTime;
    //当前数据节点的原始容量(以字节为单位)
    private Double capacityTotal;
    //当前在所有DataNode中使用的容量(以字节为单位)
    private Double capacityUsed;
    //当前剩余容量(字节)
    private Double capacityRemaining;
    //DataNodes用于非DFS目的的当前空间(以字节为单位)
    private Double capacityUsedNonDFS;
    //当前连接数
    private Double totalLoad;
    //可快照目录的当前数量
    private Double snapshottableDirectories;
    //当前文件和目录的数量
    private Double filesTotal;
    //待机NameNode中用于以后处理的挂起的与块相关的消息的当前数量
    private Double pendingDataNodeMessageCount;
    //由于心跳延迟而标记为过期的DataNodes当前数目
    private Double staleDataNodes;
    //创建时间
    private String createTime;



}
