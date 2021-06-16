package com.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NamenodeJvmmetrics {

    private Double memNonHeapUsedM;//	JVM 当前已经使用的 NonHeapMemory 的大小
    private Double memNonHeapMaxM;//	JVM 配置的 NonHeapMemory 的大小
    private Double memHeapUsedM;//	JVM 当前已经使用的 HeapMemory 的大小
    private Double memHeapMaxM;//	JVM 配置的 HeapMemory 的大小
    private Double memMaxM;//	JVM 运行时的可以使用的最大的内存的大小
    private Double gcCount;//	GC次数
    private Double threadsNew;//	当前线程的处于 NEW 状态下的线程数量
    private Double threadsRunnable;//	当前线程的处于 RUNNABLE 状态下的线程数量
    private Double threadsBlocked;//	当前线程的处于 BLOCKED 状态下的线程数量
    private Double threadsWaiting;//	当前线程的处于 WAITING 状态下的线程数量
    private Double threadsTimedWaiting;//	当前线程的处于 TIMED_WAITING 状态下的线程数量
    private Double threadsTerminated;//	当前线程的处于 TERMINATED 状态下的线程数量
    private Double logFatal;//	固定时间间隔内的 Fatal 的数量
    private Double logError;//	固定时间间隔内的 Error 的数量
    private Double logWarn;//	固定时间间隔内的 Warn 的数量
    private Double logInfo;//	固定时间间隔内的 Info 的数量
    private String createTime;//创建时间
}
