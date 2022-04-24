package nkong.util;

public class UrlUtil {


    public static void main(String[] args) {
        UrlUtil urlUtil = new UrlUtil();
        urlUtil.parseUrl();
    }


    public void parseUrl() {
        String jdbcUrl = "jdbc:derby://localhost:1527/netld?useSSL=true";
        int pos, pos1, pos2;
        String connUri,driverName,params,host,database,port;

        pos1 = jdbcUrl.indexOf(':', 5);
        System.out.println("pos1: " + pos1);
        if (jdbcUrl == null || !jdbcUrl.startsWith("jdbc:")
                || (pos1 = jdbcUrl.indexOf(':', 5)) == -1)
            throw new IllegalArgumentException("Invalid JDBC url.");

        driverName = jdbcUrl.substring(5, pos1);
        System.out.println(driverName);
        if ((pos2 = jdbcUrl.indexOf(';', pos1)) == -1) {
            connUri = jdbcUrl.substring(pos1 + 1);
        } else {
            connUri = jdbcUrl.substring(pos1 + 1, pos2);
            params = jdbcUrl.substring(pos2 + 1);
        }

        if (connUri.startsWith("//")) {
            if ((pos = connUri.indexOf('/', 2)) != -1) {
                host = connUri.substring(2, pos);
                database = connUri.substring(pos + 1);

                if ((pos = host.indexOf(':')) != -1) {
                    port = host.substring(pos + 1);
                    host = host.substring(0, pos);
                }
            }
        } else {
            database = connUri;
        }
    }

}
