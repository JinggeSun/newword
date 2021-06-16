package com.item.manager.entity.jvmlog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("jmxlog_namenode_jvmmetrics")
public class Jvmmetrics {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //	JVM 当前已经使用的 NonHeapMemory 的大小
    private Double memNonHeapUsedM;
    //	JVM 配置的 NonHeapMemory 的大小
    private Double memNonHeapMaxM;
    //	JVM 当前已经使用的 HeapMemory 的大小
    private Double memHeapUsedM;
    //	JVM 配置的 HeapMemory 的大小
    private Double memHeapMaxM;
    //	JVM 运行时的可以使用的最大的内存的大小
    private Double memMaxM;
    //	GC次数
    private Double gcCount;
    //	当前线程的处于 NEW 状态下的线程数量
    private Double threadsNew;
    //	当前线程的处于 RUNNABLE 状态下的线程数量
    private Double threadsRunnable;
    //	当前线程的处于 BLOCKED 状态下的线程数量
    private Double threadsBlocked;
    //	当前线程的处于 WAITING 状态下的线程数量
    private Double threadsWaiting;
    //	当前线程的处于 TIMED_WAITING 状态下的线程数量
    private Double threadsTimedWaiting;
    //	当前线程的处于 TERMINATED 状态下的线程数量
    private Double threadsTerminated;
    //	固定时间间隔内的 Fatal 的数量
    private Double logFatal;
    //	固定时间间隔内的 Error 的数量
    private Double logError;
    //	固定时间间隔内的 Warn 的数量
    private Double logWarn;
    //	固定时间间隔内的 Info 的数量
    private Double logInfo;
    //创建时间
    private String createTime;
}
