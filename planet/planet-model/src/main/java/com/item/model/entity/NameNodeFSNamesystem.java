package com.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameNodeFSNamesystem {

    private Double missingBlocks;//当前遗失的block数量
    private Double expiredHeartbeats;//失去心跳的总数量
    private Double transactionsSinceLastCheckpoint;//自上次检查点以来的事务总数
    private Double transactionsSinceLastLogRoll;//自上次编辑日志卷以来的事务总数
    private Double lastCheckpointTime;//从上一个检查点以来的时间(毫秒)
    private Double capacityTotal;//当前数据节点的原始容量(以字节为单位)
    private Double capacityUsed;//当前在所有DataNode中使用的容量(以字节为单位)
    private Double capacityRemaining;//当前剩余容量(字节)
    private Double capacityUsedNonDFS;//DataNodes用于非DFS目的的当前空间(以字节为单位)
    private Double totalLoad;//当前连接数
    private Double snapshottableDirectories;//可快照目录的当前数量
    private Double filesTotal;//当前文件和目录的数量
    private Double pendingDataNodeMessageCount;//待机NameNode中用于以后处理的挂起的与块相关的消息的当前数量
    private Double staleDataNodes;//由于心跳延迟而标记为过期的DataNodes当前数目
    private String createTime;//创建时间



}
