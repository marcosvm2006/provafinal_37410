package com.fiec.provafinal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sapato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // ID único gerado automaticamente

    private String nome; // Nome do produto

    private double preco; // Preço do produto

    private String imagem; // URL ou caminho da imagem do produto

    private int tamanho; // Tamanho (ex.: para calçados)

    private String marca; // Marca do produto

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString(); // Gera um ID único
        }
    }
}