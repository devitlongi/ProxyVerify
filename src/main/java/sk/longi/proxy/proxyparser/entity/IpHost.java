package sk.longi.proxy.proxyparser.entity;


import java.time.LocalDateTime;


public class IpHost {
    private String ip;
    private String port;
    private String Country;
    private String anonimityLevel;
    private Integer uptimeL;
    private Integer uptimeD;
    private LocalDateTime lastUpdate;
    private Integer responseTime;
    private Boolean veryfy = null;

    public Boolean getVeryfy() {
        return veryfy;
    }

    public void setVeryfy(Boolean veryfy) {
        this.veryfy = veryfy;
    }

    public IpHost() {
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

    public java.lang.String getAnonimityLevel() {
        return anonimityLevel;
    }

    public void setAnonimityLevel(java.lang.String anonimityLevel) {
        this.anonimityLevel = anonimityLevel;
    }

    public Integer getUptimeL() {
        return uptimeL;
    }

    public void setUptimeL(Integer uptimeL) {
        this.uptimeL = uptimeL;
    }

    public Integer getUptimeD() {
        return uptimeD;
    }

    public void setUptimeD(Integer uptimeD) {
        this.uptimeD = uptimeD;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }



    @Override
    public String toString() {
        return "Proxy{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", Country='" + Country + '\'' +
                ", anonimityLevel='" + anonimityLevel + '\'' +
                ", uptimeL=" + uptimeL +
                ", uptimeD=" + uptimeD +
                ", lastUpdate=" + lastUpdate +
                ", veryfy=" + veryfy +
                ", responseTime=" + responseTime +
                '}';
    }
}
