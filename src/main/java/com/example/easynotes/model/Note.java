package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data // Generates getters for all fields, a useful toString method, and hashCode and equals implementations.
@Entity // Definisce la classe come classe java di persistenza.
@Table(name = "notes") // Fornisce i dettagli della tabella fisica che mappa questa entità.
@EntityListeners(AuditingEntityListener.class) // Aggiunge un AuditingEntityListener al Domain Model.
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) // excludes createdAt and updatedAt from the serialization process.
public class Note implements Serializable { // JSON is the default serialization type

    @Id // Defines the primary key.
    @GeneratedValue(strategy = GenerationType.AUTO) // definire la strategia di generazione della chiave primaria.
    private Long id;

    @NotBlank // not empty and not null
    private String title;

    @NotBlank
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP) /*serve a dichiarare l’utilizzo delle classi Java java.util.Date e
                                        java.util.Calendar quando si devono convertire i valori dei campi nella tabella per il loro
                                        utilizzo nelle nostre classi*/
    @CreatedDate /*impostare i campi con data e ora correnti al momento della creazione di un record*/
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate /*impostare i campi con data e ora correnti al momento dell’aggiornamento di un record*/
    private Date updatedDate;

}
