package com.hutech.demo.entity;
import com.hutech.demo.validator.annotation.ValidatorCategoryId;
import com.hutech.demo.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50)
    @NotEmpty(message = "Title must not be null")
    @Size(max = 50, message = "Title must be less then 50 characters")
    private String title;

    @Column(name = "author", length = 50)
    @Size(max = 50, message = "Title must be less then 50 characters")
    private String author;

    @Column(name = "price")
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ValidatorCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ValidUserId
    private User user;
}

