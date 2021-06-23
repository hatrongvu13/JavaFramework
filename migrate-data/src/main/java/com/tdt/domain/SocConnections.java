package com.tdt.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SOC_CONNECTIONS")
public class SocConnections {
    private long connectionId;
    private long senderId;
    private long receiverId;
    private Boolean status;
    private Timestamp updatedDate;

    @Id
    @Column(name = "CONNECTION_ID", nullable = false)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    @Basic
    @Column(name = "SENDER_ID", nullable = false)
    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "RECEIVER_ID", nullable = false)
    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    @Basic
    @Column(name = "STATUS", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "UPDATED_DATE", nullable = false)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocConnections that = (SocConnections) o;
        return connectionId == that.connectionId && status == that.status && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionId, status, updatedDate);
    }
}
