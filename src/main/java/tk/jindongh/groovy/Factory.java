package tk.jindongh.groovy;

public interface Factory {
    String getName();
    void connect(String jdbcUrl);
}
