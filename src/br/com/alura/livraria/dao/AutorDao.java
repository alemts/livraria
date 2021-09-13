package br.com.alura.livraria.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.livraria.modelo.Autor;

public class AutorDao {

    private Connection con;
    
    public AutorDao(Connection con) {
        this.con = con;
    }

    public void cadastrar(Autor t)
    {
        try {
            String sql = "INSERT INTO Autor " + 
                         "            (Nome, Email, DataNascimento, MiniCurriculo) " +
                         "     VALUES (?, ?, ?, ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
            int i=1;
            ps.setString(i++, t.getNome());
            ps.setString(i++, t.getEmail());
            ps.setDate  (i++, Date.valueOf(t.getDataNascimento()));
            ps.setString(i++, t.getMiniCurriculo());            

            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Autor> listar() 
    {
        try {
            String sql = "SELECT * FROM Autor ";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Autor> listagemAutores = new ArrayList<>();
            
            while (rs.next()) {
                Autor autor = new Autor( );
                
                autor.setNome(rs.getString("Nome"));
                autor.setEmail(rs.getString("Email"));
                autor.setDataNascimento(rs.getDate("DataNascimento").toLocalDate());
                autor.setMiniCurriculo(rs.getString("MiniCurriculo"));
                
                listagemAutores.add(autor);
            }
            return listagemAutores;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
