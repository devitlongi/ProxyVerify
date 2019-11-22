package sk.longi.proxy.proxyparser.entity;


import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RequestScoped
@Entity
@Table
public class ProxyFull {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @NotNull
    private String ip;

    @Column(name="PORT1")
    @NotNull
    private String port;

    @Column
    private String Country;

    @Column
    private String anonymity_Level;

    @Column
    private Integer uptime_L;

    @Column
    private Integer uptime_D;

    @Column
    private LocalDateTime last_Update;

    @Column
    private Integer response_Time;

    @Column
    private Boolean verify = false;

    @Column
    public Boolean getVerify() {
        return verify;
    }


    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    public ProxyFull() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getIp() {
        return ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    public java.lang.String getPort() {
        return port;
    }

    public void setPort(java.lang.String port) {
        this.port = port;
    }

    public java.lang.String getCountry() {
        return Country;
    }

    public void setCountry(java.lang.String country) {
        Country = country;
    }

    public java.lang.String getAnonymity_Level() {
        return anonymity_Level;
    }

    public void setAnonymity_Level(java.lang.String anonymityLevel) {
        this.anonymity_Level = anonymityLevel;
    }

    public Integer getUptime_L() {
        return uptime_L;
    }

    public void setUptime_L(Integer uptimeL) {
        this.uptime_L = uptimeL;
    }

    public Integer getUptime_D() {
        return uptime_D;
    }

    public void setUptime_D(Integer uptimeD) {
        this.uptime_D = uptimeD;
    }

    public LocalDateTime getLast_Update() {
        return last_Update;
    }

    public void setLast_Update(LocalDateTime lastUpdate) {
        this.last_Update = lastUpdate;
    }

    public Integer getResponse_Time() {
        return response_Time;
    }

    public void setResponse_Time(Integer responseTime) {
        this.response_Time = responseTime;
    }



    @Override
    public String toString() {
        return "Proxy{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", Country='" + Country + '\'' +
                ", anonimityLevel='" + anonymity_Level + '\'' +
                ", uptimeL=" + uptime_L +
                ", uptimeD=" + uptime_D +
                ", lastUpdate=" + last_Update +
                ", veryfy=" + verify +
                ", responseTime=" + response_Time +
                '}';
    }
}
