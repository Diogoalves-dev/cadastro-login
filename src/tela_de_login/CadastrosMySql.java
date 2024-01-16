package tela_de_login;

import java.sql.PreparedStatement;
import java.sql.Connection;
import ConexaoMysql.Conexao;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author diogo
 */
public class CadastrosMySql {
    private Connection conect;
    private Conexao conexao;
    
    public CadastrosMySql(){
        this.conexao = new Conexao();
        conect = this.conexao.getConexao();
    }
    
    public void Inserir(Users user){
        String sql = "INSERT INTO cadastros (nome,email,senha) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1,user.getNome());
            stmt.setString(2,user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.execute();
            
            System.out.println("Sucesso ao inserir os dados");
        }catch(Exception e){
            System.out.println("Erro ao inserir os dados: ERRO " + e.getMessage());
            
            UIManager.put("OptionPane.okButtonText", "Tentar Novamente");
            JOptionPane.showMessageDialog(null,
                    "esse email ja pertence a outro usuário",
                    "ERRO",
                    0);
        }
    }
    
    public Users getCadastro(String email){
        String sql = "SELECT * FROM cadastros WHERE email LIKE ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            
            Users user = new Users();
            rs.next();
            
            user.setId(rs.getInt("id"));
            user.setNome(rs.getString("nome"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            return user;
           
        }catch(Exception e){
            System.out.println("erro ao buscar os dados. ERRO: " + e.getMessage());
            return null;
        }
    }
    
    public void Editar(Users user){
        String sql = "UPDATE cadastros SET nome = ?,email = ?,senha = ? WHERE id = ?";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1,user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3,user.getSenha());
            stmt.setInt(4,user.getId());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar os dados " + e.getMessage());
        }
    }
    
    public void Deletar(String email){
        String sql = "DELETE FROM cadastros WHERE email LIKE ? ";
        
        try{
            
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.execute();
            
        }catch(Exception e){
            System.out.println("Erro ao deletar dados. ERRO: " + e.getMessage());
        }
        
    
    }
}
