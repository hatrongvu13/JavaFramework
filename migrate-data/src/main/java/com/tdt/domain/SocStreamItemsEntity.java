package com.tdt.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SOC_STREAM_ITEMS")
public class SocStreamItemsEntity {
    private long streamItemId;
    private long ownerId;
    private int streamType;
    private long activityId;
    private long updatedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STREAM_ITEM_ID", nullable = false)
    public long getStreamItemId() {
        return streamItemId;
    }

    public void setStreamItemId(long streamItemId) {
        this.streamItemId = streamItemId;
    }

    @Basic
    @Column(name = "OWNER_ID", nullable = false)
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "STREAM_TYPE", nullable = false)
    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    @Basic
    @Column(name = "ACTIVITY_ID", nullable = false)
    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "UPDATED_DATE", nullable = false)
    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocStreamItemsEntity that = (SocStreamItemsEntity) o;
        return streamItemId == that.streamItemId && ownerId == that.ownerId && streamType == that.streamType && updatedDate == that.updatedDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamItemId, ownerId, streamType, updatedDate);
    }
}
