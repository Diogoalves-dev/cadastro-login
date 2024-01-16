
package ConexaoMysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    public Connection getConexao(){
        try{
            Connection conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/login?Timezone=UTC",
                    "root",
                    "@Dev2004");
            System.out.println("Connection: stable");    
            return conexao;
        }catch(Exception e){
            System.out.println("Connection: unstable\nERRO: " + e.getMessage());
            return null;
        }
    }
}
