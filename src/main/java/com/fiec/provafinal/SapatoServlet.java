package com.fiec.provafinal;


import com.fiec.provafinal.models.Sapato;
import com.fiec.provafinal.models.SapatoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/sapatos/*")
public class SapatoServlet extends HttpServlet {

    private SapatoRepositorio sapatoRepositorio;

    public SapatoServlet(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("minhaUnidadeDePersistencia");
        EntityManager em = emf.createEntityManager();
        sapatoRepositorio = new SapatoRepositorio(em);
    }
    // Create  /produtos
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String imagem = request.getParameter("imagem");
        int tamanho = Integer.parseInt(request.getParameter("tamanho"));
        String marca = request.getParameter("marca");

        Sapato sapato = Sapato.builder()
                .nome(nome)
                .preco(preco)
                .imagem(imagem)
                .tamanho(tamanho)
                .marca(marca)
                .build();

        sapatoRepositorio.criar(sapato);


        response.setContentType("text/html");
        response.getWriter().println("Produto Salvo");
    }
    // Read  /produtos
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        List<Sapato> sapatos=sapatoRepositorio.ler();
        response.getWriter().println(sapatos.stream().map(Sapato::toString).collect(Collectors.toList()));

    }
    // Update   /produtos/:id
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getId(request);
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String imagem = request.getParameter("imagem");
        int tamanho = Integer.parseInt(request.getParameter("tamanho"));
        String marca = request.getParameter("marca");

        Sapato sapato = Sapato.builder()
                .nome(nome)
                .preco(preco)
                .imagem(imagem)
                .tamanho(tamanho)
                .marca(marca)
                .build();
        sapatoRepositorio.atualiza(sapato, id);

        response.setContentType("text/html");
        response.getWriter().println("Sapato Atualizado");

    }
    // Delete  /produtos/:id
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getId(request);
        sapatoRepositorio.deleta(id);
        response.setContentType("text/html");
        response.getWriter().println("Sapato Deletado");
    }

    private static String getId(HttpServletRequest req){
        String path = req.getPathInfo();
        String[] paths = path.split("/");
        String id = paths[paths.length - 1];
        return id;
    }

}