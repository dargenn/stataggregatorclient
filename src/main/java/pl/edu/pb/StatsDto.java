package pl.edu.pb;

public class StatsDto {
    private String hostname;
    private double cpuUsage;
    private double memoryUsed;
    private double memoryTotal;

    public StatsDto() {
    }

    public StatsDto(String hostname, double cpuUsage, double memoryUsed, double memoryTotal) {
        this.hostname = hostname;
        this.cpuUsage = cpuUsage;
        this.memoryUsed = memoryUsed;
        this.memoryTotal = memoryTotal;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(double memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public double getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(double memoryTotal) {
        this.memoryTotal = memoryTotal;
    }
}
