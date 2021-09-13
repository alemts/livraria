package br.com.alura.livraria.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.livraria.dao.AutorDao;
import br.com.alura.livraria.factory.ConnectionFactory;
import br.com.alura.livraria.modelo.Autor;

@WebServlet("/AutorServlet")
public class AutorServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private AutorDao dao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutorServlet()
    {
        this.dao = new AutorDao(new ConnectionFactory().getConnection());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("listagemAutores", dao.listar());
        request.getRequestDispatcher("WEB-INF/pages/cadastro-autor.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Autor autor = coletaParametrosAutor(request);

        dao.cadastrar(autor);

        response.sendRedirect("AutorServlet");
    }

    private Autor coletaParametrosAutor(HttpServletRequest request)
    {

        String nome = getParameterString(request, "nome");
        String email = getParameterString(request, "email");
        LocalDate dataNascimento = getParameterDate(request, "dataNascimento");
        String miniCurriculo = getParameterString(request, "miniCurriculo");

        Autor autor = new Autor(nome, email, dataNascimento, miniCurriculo);

        return autor;
    }

    private String getParameterString(HttpServletRequest request, String nomeCampo)
    {
        String param = request.getParameter(nomeCampo);
        if ( param == null || param.length() == 0 ) return "";

        return param;
    }
    
    private LocalDate getParameterDate(HttpServletRequest request, String nomeCampo)
    {
        String param = request.getParameter(nomeCampo);
        if ( param == null || param.length() == 0 ) return (LocalDate.now());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(param, formato);
        
        return data;
    }
}
